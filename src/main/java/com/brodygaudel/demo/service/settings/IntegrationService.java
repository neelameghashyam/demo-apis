package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.IntegrationDTO;
import com.brodygaudel.demo.exception.settings.IntegrationNotFoundException;

import java.util.List;

public interface IntegrationService {
    IntegrationDTO saveIntegration(IntegrationDTO integrationDTO);
    IntegrationDTO updateIntegration(IntegrationDTO integrationDTO) throws IntegrationNotFoundException;
    IntegrationDTO findIntegrationById(Long id) throws IntegrationNotFoundException;
    List<IntegrationDTO> findAllIntegrations();
    List<IntegrationDTO> searchIntegrations(String keyword, int page, int size);
    void deleteIntegrationById(Long id);
    void deleteAllIntegrations();
}