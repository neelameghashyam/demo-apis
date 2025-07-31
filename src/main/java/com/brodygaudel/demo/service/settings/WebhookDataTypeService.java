package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.WebhookDataTypeDTO;
import com.brodygaudel.demo.exception.settings.WebhookDataTypeNotFoundException;

import java.util.List;

public interface WebhookDataTypeService {
    WebhookDataTypeDTO saveWebhookDataType(WebhookDataTypeDTO webhookDataTypeDTO);
    WebhookDataTypeDTO updateWebhookDataType(WebhookDataTypeDTO webhookDataTypeDTO) throws WebhookDataTypeNotFoundException;
    WebhookDataTypeDTO findWebhookDataTypeById(Long id) throws WebhookDataTypeNotFoundException;
    List<WebhookDataTypeDTO> findAllWebhookDataTypes();
    List<WebhookDataTypeDTO> searchWebhookDataTypes(String keyword, int page, int size);
    void deleteWebhookDataTypeById(Long id);
    void deleteAllWebhookDataTypes();
}