package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.SmartResponseDTO;
import com.brodygaudel.demo.entity.User;
import com.brodygaudel.demo.entity.settings.SmartResponse;
import com.brodygaudel.demo.entity.settings.SmartResponseShortcut;
import com.brodygaudel.demo.entity.settings.SmartResponseWebsite;
import com.brodygaudel.demo.exception.settings.SmartResponseNotFoundException;
import com.brodygaudel.demo.repository.UserRepository;
import com.brodygaudel.demo.repository.settings.SmartResponseRepository;
import com.brodygaudel.demo.repository.settings.SmartResponseShortcutRepository;
import com.brodygaudel.demo.repository.settings.SmartResponseWebsiteRepository;
import com.brodygaudel.demo.service.settings.SmartResponseService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SmartResponseServiceImpl implements SmartResponseService {

    private final SmartResponseRepository smartResponseRepository;
    private final UserRepository userRepository;
    private final SmartResponseShortcutRepository shortcutRepository;
    private final SmartResponseWebsiteRepository websiteRepository;
    private final Mappers mappers;

    public SmartResponseServiceImpl(SmartResponseRepository smartResponseRepository, UserRepository userRepository,
                                    SmartResponseShortcutRepository shortcutRepository, SmartResponseWebsiteRepository websiteRepository,
                                    Mappers mappers) {
        this.smartResponseRepository = smartResponseRepository;
        this.userRepository = userRepository;
        this.shortcutRepository = shortcutRepository;
        this.websiteRepository = websiteRepository;
        this.mappers = mappers;
    }

    @Override
    public SmartResponseDTO saveSmartResponse(SmartResponseDTO smartResponseDTO) {
        log.info("In saveSmartResponse()");
        SmartResponse smartResponse = mappers.fromSmartResponseDTO(smartResponseDTO);
        if (smartResponseDTO.userId() != null) {
            User user = userRepository.findById(smartResponseDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            smartResponse.setUser(user);
        }
        if (smartResponseDTO.shortcuts() != null) {
            List<SmartResponseShortcut> shortcuts = smartResponseDTO.shortcuts().stream()
                    .map(s -> SmartResponseShortcut.builder().smartResponse(smartResponse).shortcut(s).build())
                    .toList();
            smartResponse.setShortcuts(shortcuts);
        }
        if (smartResponseDTO.websites() != null) {
            List<SmartResponseWebsite> websites = smartResponseDTO.websites().stream()
                    .map(w -> SmartResponseWebsite.builder().smartResponse(smartResponse).website(w).build())
                    .toList();
            smartResponse.setWebsites(websites);
        }
        SmartResponse saved = smartResponseRepository.save(smartResponse);
        log.info("SmartResponse saved with id: {}", saved.getId());
        return mappers.fromSmartResponse(saved);
    }

    @Override
    public SmartResponseDTO updateSmartResponse(SmartResponseDTO smartResponseDTO) throws SmartResponseNotFoundException {
        log.info("In updateSmartResponse()");
        SmartResponse smartResponse = smartResponseRepository.findById(smartResponseDTO.id())
                .orElseThrow(() -> new SmartResponseNotFoundException("SmartResponse not found"));
        smartResponse.setResponse(smartResponseDTO.response());
        smartResponse.setCreatedBy(smartResponseDTO.createdBy());
        smartResponse.setCompany(smartResponseDTO.company());
        smartResponse.setCreatedAt(smartResponseDTO.createdAt());
        smartResponse.setUpdatedAt(smartResponseDTO.updatedAt());
        if (smartResponseDTO.userId() != null) {
            User user = userRepository.findById(smartResponseDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            smartResponse.setUser(user);
        } else {
            smartResponse.setUser(null);
        }
        smartResponse.getShortcuts().clear();
        if (smartResponseDTO.shortcuts() != null) {
            List<SmartResponseShortcut> shortcuts = smartResponseDTO.shortcuts().stream()
                    .map(s -> SmartResponseShortcut.builder().smartResponse(smartResponse).shortcut(s).build())
                    .toList();
            smartResponse.getShortcuts().addAll(shortcuts);
        }
        smartResponse.getWebsites().clear();
        if (smartResponseDTO.websites() != null) {
            List<SmartResponseWebsite> websites = smartResponseDTO.websites().stream()
                    .map(w -> SmartResponseWebsite.builder().smartResponse(smartResponse).website(w).build())
                    .toList();
            smartResponse.getWebsites().addAll(websites);
        }
        SmartResponse updated = smartResponseRepository.save(smartResponse);
        log.info("SmartResponse updated");
        return mappers.fromSmartResponse(updated);
    }

    @Override
    public SmartResponseDTO findSmartResponseById(Long id) throws SmartResponseNotFoundException {
        log.info("In findSmartResponseById()");
        SmartResponse smartResponse = smartResponseRepository.findById(id)
                .orElseThrow(() -> new SmartResponseNotFoundException("SmartResponse not found"));
        log.info("SmartResponse found with id: {}", smartResponse.getId());
        return mappers.fromSmartResponse(smartResponse);
    }

    @Override
    public List<SmartResponseDTO> findAllSmartResponses() {
        log.info("In findAllSmartResponses()");
        List<SmartResponse> smartResponses = smartResponseRepository.findAll();
        log.info("All smart responses found");
        return mappers.fromListOfSmartResponses(smartResponses);
    }

    @Override
    public List<SmartResponseDTO> searchSmartResponses(String keyword, int page, int size) {
        log.info("In searchSmartResponses()");
        Page<SmartResponse> smartResponsePage = smartResponseRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} smart responses found", smartResponsePage.getContent().size());
        return mappers.fromListOfSmartResponses(smartResponsePage.getContent());
    }

    @Override
    public void deleteSmartResponseById(Long id) {
        log.info("In deleteSmartResponseById()");
        smartResponseRepository.deleteById(id);
        log.info("SmartResponse deleted");
    }

    @Override
    public void deleteAllSmartResponses() {
        log.info("In deleteAllSmartResponses()");
        smartResponseRepository.deleteAll();
        log.info("All smart responses deleted");
    }
}