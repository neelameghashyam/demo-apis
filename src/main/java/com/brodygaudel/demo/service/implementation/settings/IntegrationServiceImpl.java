package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.IntegrationDTO;
import com.brodygaudel.demo.entity.User;
import com.brodygaudel.demo.entity.settings.Integration;
import com.brodygaudel.demo.exception.settings.IntegrationNotFoundException;
import com.brodygaudel.demo.repository.UserRepository;
import com.brodygaudel.demo.repository.settings.IntegrationRepository;
import com.brodygaudel.demo.service.settings.IntegrationService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class IntegrationServiceImpl implements IntegrationService {

    private final IntegrationRepository integrationRepository;
    private final UserRepository userRepository;
    private final Mappers mappers;

    public IntegrationServiceImpl(IntegrationRepository integrationRepository, UserRepository userRepository, Mappers mappers) {
        this.integrationRepository = integrationRepository;
        this.userRepository = userRepository;
        this.mappers = mappers;
    }

    @Override
    public IntegrationDTO saveIntegration(IntegrationDTO integrationDTO) {
        log.info("In saveIntegration()");
        Integration integration = mappers.fromIntegrationDTO(integrationDTO);
        if (integrationDTO.userId() != null) {
            User user = userRepository.findById(integrationDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            integration.setUser(user);
        }
        Integration saved = integrationRepository.save(integration);
        log.info("Integration saved with id: {}", saved.getId());
        return mappers.fromIntegration(saved);
    }

    @Override
    public IntegrationDTO updateIntegration(IntegrationDTO integrationDTO) throws IntegrationNotFoundException {
        log.info("In updateIntegration()");
        Integration integration = integrationRepository.findById(integrationDTO.id())
                .orElseThrow(() -> new IntegrationNotFoundException("Integration not found"));
        integration.setService(Integration.Service.valueOf(integrationDTO.service()));
        integration.setWebsite(integrationDTO.website());
        integration.setApiKey(integrationDTO.apiKey());
        integration.setIsConfigured(integrationDTO.isConfigured());
        integration.setCreatedAt(integrationDTO.createdAt());
        integration.setUpdatedAt(integrationDTO.updatedAt());
        if (integrationDTO.userId() != null) {
            User user = userRepository.findById(integrationDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            integration.setUser(user);
        } else {
            integration.setUser(null);
        }
        Integration updated = integrationRepository.save(integration);
        log.info("Integration updated");
        return mappers.fromIntegration(updated);
    }

    @Override
    public IntegrationDTO findIntegrationById(Long id) throws IntegrationNotFoundException {
        log.info("In findIntegrationById()");
        Integration integration = integrationRepository.findById(id)
                .orElseThrow(() -> new IntegrationNotFoundException("Integration not found"));
        log.info("Integration found with id: {}", integration.getId());
        return mappers.fromIntegration(integration);
    }

    @Override
    public List<IntegrationDTO> findAllIntegrations() {
        log.info("In findAllIntegrations()");
        List<Integration> integrations = integrationRepository.findAll();
        log.info("All integrations found");
        return mappers.fromListOfIntegrations(integrations);
    }

    @Override
    public List<IntegrationDTO> searchIntegrations(String keyword, int page, int size) {
        log.info("In searchIntegrations()");
        Page<Integration> integrationPage = integrationRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} integrations found", integrationPage.getContent().size());
        return mappers.fromListOfIntegrations(integrationPage.getContent());
    }

    @Override
    public void deleteIntegrationById(Long id) {
        log.info("In deleteIntegrationById()");
        integrationRepository.deleteById(id);
        log.info("Integration deleted");
    }

    @Override
    public void deleteAllIntegrations() {
        log.info("In deleteAllIntegrations()");
        integrationRepository.deleteAll();
        log.info("All integrations deleted");
    }
}