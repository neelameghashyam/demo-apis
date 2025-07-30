package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.InactivityTimeoutDTO;
import com.brodygaudel.demo.entity.User;
import com.brodygaudel.demo.entity.settings.InactivityTimeout;
import com.brodygaudel.demo.exception.settings.InactivityTimeoutNotFoundException;
import com.brodygaudel.demo.repository.UserRepository;
import com.brodygaudel.demo.repository.settings.InactivityTimeoutRepository;
import com.brodygaudel.demo.service.settings.InactivityTimeoutService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InactivityTimeoutServiceImpl implements InactivityTimeoutService {

    private final InactivityTimeoutRepository inactivityTimeoutRepository;
    private final UserRepository userRepository;
    private final Mappers mappers;

    public InactivityTimeoutServiceImpl(InactivityTimeoutRepository inactivityTimeoutRepository, UserRepository userRepository, Mappers mappers) {
        this.inactivityTimeoutRepository = inactivityTimeoutRepository;
        this.userRepository = userRepository;
        this.mappers = mappers;
    }

    @Override
    public InactivityTimeoutDTO saveInactivityTimeout(InactivityTimeoutDTO inactivityTimeoutDTO) {
        log.info("In saveInactivityTimeout()");
        InactivityTimeout inactivityTimeout = mappers.fromInactivityTimeoutDTO(inactivityTimeoutDTO);
        if (inactivityTimeoutDTO.userId() != null) {
            User user = userRepository.findById(inactivityTimeoutDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            inactivityTimeout.setUser(user);
        }
        InactivityTimeout saved = inactivityTimeoutRepository.save(inactivityTimeout);
        log.info("InactivityTimeout saved with id: {}", saved.getId());
        return mappers.fromInactivityTimeout(saved);
    }

    @Override
    public InactivityTimeoutDTO updateInactivityTimeout(InactivityTimeoutDTO inactivityTimeoutDTO) throws InactivityTimeoutNotFoundException {
        log.info("In updateInactivityTimeout()");
        InactivityTimeout inactivityTimeout = inactivityTimeoutRepository.findById(inactivityTimeoutDTO.id())
                .orElseThrow(() -> new InactivityTimeoutNotFoundException("InactivityTimeout not found"));
        inactivityTimeout.setAgentNotRespondingEnabled(inactivityTimeoutDTO.agentNotRespondingEnabled());
        inactivityTimeout.setAgentNotRespondingMinutes(inactivityTimeoutDTO.agentNotRespondingMinutes());
        inactivityTimeout.setAgentNotRespondingSeconds(inactivityTimeoutDTO.agentNotRespondingSeconds());
        inactivityTimeout.setArchiveChatEnabled(inactivityTimeoutDTO.archiveChatEnabled());
        inactivityTimeout.setArchiveChatMinutes(inactivityTimeoutDTO.archiveChatMinutes());
        inactivityTimeout.setArchiveChatSeconds(inactivityTimeoutDTO.archiveChatSeconds());
        inactivityTimeout.setVisitorInactivityMinutes(inactivityTimeoutDTO.visitorInactivityMinutes());
        inactivityTimeout.setVisitorInactivitySeconds(inactivityTimeoutDTO.visitorInactivitySeconds());
        inactivityTimeout.setTimeoutMessage(inactivityTimeoutDTO.timeoutMessage());
        inactivityTimeout.setCreatedAt(inactivityTimeoutDTO.createdAt());
        inactivityTimeout.setUpdatedAt(inactivityTimeoutDTO.updatedAt());
        if (inactivityTimeoutDTO.userId() != null) {
            User user = userRepository.findById(inactivityTimeoutDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            inactivityTimeout.setUser(user);
        } else {
            inactivityTimeout.setUser(null);
        }
        InactivityTimeout updated = inactivityTimeoutRepository.save(inactivityTimeout);
        log.info("InactivityTimeout updated");
        return mappers.fromInactivityTimeout(updated);
    }

    @Override
    public InactivityTimeoutDTO findInactivityTimeoutById(Long id) throws InactivityTimeoutNotFoundException {
        log.info("In findInactivityTimeoutById()");
        InactivityTimeout inactivityTimeout = inactivityTimeoutRepository.findById(id)
                .orElseThrow(() -> new InactivityTimeoutNotFoundException("InactivityTimeout not found"));
        log.info("InactivityTimeout found with id: {}", inactivityTimeout.getId());
        return mappers.fromInactivityTimeout(inactivityTimeout);
    }

    @Override
    public List<InactivityTimeoutDTO> findAllInactivityTimeouts() {
        log.info("In findAllInactivityTimeouts()");
        List<InactivityTimeout> inactivityTimeouts = inactivityTimeoutRepository.findAll();
        log.info("All inactivity timeouts found");
        return mappers.fromListOfInactivityTimeouts(inactivityTimeouts);
    }

    @Override
    public List<InactivityTimeoutDTO> searchInactivityTimeouts(String keyword, int page, int size) {
        log.info("In searchInactivityTimeouts()");
        Page<InactivityTimeout> inactivityTimeoutPage = inactivityTimeoutRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} inactivity timeouts found", inactivityTimeoutPage.getContent().size());
        return mappers.fromListOfInactivityTimeouts(inactivityTimeoutPage.getContent());
    }

    @Override
    public void deleteInactivityTimeoutById(Long id) {
        log.info("In deleteInactivityTimeoutById()");
        inactivityTimeoutRepository.deleteById(id);
        log.info("InactivityTimeout deleted");
    }

    @Override
    public void deleteAllInactivityTimeouts() {
        log.info("In deleteAllInactivityTimeouts()");
        inactivityTimeoutRepository.deleteAll();
        log.info("All inactivity timeouts deleted");
    }
}