package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.GlobalWebhookDTO;
import com.brodygaudel.demo.entity.User;
import com.brodygaudel.demo.entity.settings.GlobalWebhook;
import com.brodygaudel.demo.exception.settings.GlobalWebhookNotFoundException;
import com.brodygaudel.demo.repository.UserRepository;
import com.brodygaudel.demo.repository.settings.GlobalWebhookRepository;
import com.brodygaudel.demo.service.settings.GlobalWebhookService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GlobalWebhookServiceImpl implements GlobalWebhookService {

    private final GlobalWebhookRepository globalWebhookRepository;
    private final UserRepository userRepository;
    private final Mappers mappers;

    public GlobalWebhookServiceImpl(GlobalWebhookRepository globalWebhookRepository, UserRepository userRepository, Mappers mappers) {
        this.globalWebhookRepository = globalWebhookRepository;
        this.userRepository = userRepository;
        this.mappers = mappers;
    }

    @Override
    public GlobalWebhookDTO saveGlobalWebhook(GlobalWebhookDTO globalWebhookDTO) {
        log.info("In saveGlobalWebhook()");
        GlobalWebhook globalWebhook = mappers.fromGlobalWebhookDTO(globalWebhookDTO);
        if (globalWebhookDTO.userId() != null) {
            User user = userRepository.findById(globalWebhookDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            globalWebhook.setUser(user);
        }
        GlobalWebhook saved = globalWebhookRepository.save(globalWebhook);
        log.info("GlobalWebhook saved with id: {}", saved.getId());
        return mappers.fromGlobalWebhook(saved);
    }

    @Override
    public GlobalWebhookDTO updateGlobalWebhook(GlobalWebhookDTO globalWebhookDTO) throws GlobalWebhookNotFoundException {
        log.info("In updateGlobalWebhook()");
        GlobalWebhook globalWebhook = globalWebhookRepository.findById(globalWebhookDTO.id())
                .orElseThrow(() -> new GlobalWebhookNotFoundException("GlobalWebhook not found"));
        globalWebhook.setEvent(GlobalWebhook.Event.valueOf(globalWebhookDTO.event()));
        globalWebhook.setDataTypeEnabled(globalWebhookDTO.dataTypeEnabled());
        globalWebhook.setDestination(GlobalWebhook.Destination.valueOf(globalWebhookDTO.destination()));
        globalWebhook.setEmail(globalWebhookDTO.email());
        globalWebhook.setTargetUrl(globalWebhookDTO.targetUrl());
        globalWebhook.setCreatedBy(globalWebhookDTO.createdBy());
        globalWebhook.setCompany(globalWebhookDTO.company());
        globalWebhook.setCreatedAt(globalWebhookDTO.createdAt());
        globalWebhook.setUpdatedAt(globalWebhookDTO.updatedAt());
        if (globalWebhookDTO.userId() != null) {
            User user = userRepository.findById(globalWebhookDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            globalWebhook.setUser(user);
        } else {
            globalWebhook.setUser(null);
        }
        GlobalWebhook updated = globalWebhookRepository.save(globalWebhook);
        log.info("GlobalWebhook updated");
        return mappers.fromGlobalWebhook(updated);
    }

    @Override
    public GlobalWebhookDTO findGlobalWebhookById(Long id) throws GlobalWebhookNotFoundException {
        log.info("In findGlobalWebhookById()");
        GlobalWebhook globalWebhook = globalWebhookRepository.findById(id)
                .orElseThrow(() -> new GlobalWebhookNotFoundException("GlobalWebhook not found"));
        log.info("GlobalWebhook found with id: {}", globalWebhook.getId());
        return mappers.fromGlobalWebhook(globalWebhook);
    }

    @Override
    public List<GlobalWebhookDTO> findAllGlobalWebhooks() {
        log.info("In findAllGlobalWebhooks()");
        List<GlobalWebhook> globalWebhooks = globalWebhookRepository.findAll();
        log.info("All global webhooks found");
        return mappers.fromListOfGlobalWebhooks(globalWebhooks);
    }

   @Override
public List<GlobalWebhookDTO> searchGlobalWebhooks(String keyword, int page, int size) {
    log.info("In searchGlobalWebhooks()");
    Page<GlobalWebhook> globalWebhookPage = globalWebhookRepository.search("%" + keyword + "%", PageRequest.of(page, size));
    log.info("{} global webhooks found", globalWebhookPage.getContent().size());
    return mappers.fromListOfGlobalWebhooks(globalWebhookPage.getContent());
}

    @Override
    public void deleteGlobalWebhookById(Long id) {
        log.info("In deleteGlobalWebhookById()");
        globalWebhookRepository.deleteById(id);
        log.info("GlobalWebhook deleted");
    }

    @Override
    public void deleteAllGlobalWebhooks() {
        log.info("In deleteAllGlobalWebhooks()");
        globalWebhookRepository.deleteAll();
        log.info("All global webhooks deleted");
    }
}