package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.WebhookDTO;
import com.brodygaudel.demo.exception.settings.WebhookNotFoundException;

import java.util.List;

public interface WebhookService {
    WebhookDTO saveWebhook(WebhookDTO webhookDTO);
    WebhookDTO updateWebhook(WebhookDTO webhookDTO) throws WebhookNotFoundException;
    WebhookDTO findWebhookById(Long id) throws WebhookNotFoundException;
    List<WebhookDTO> findAllWebhooks();
    List<WebhookDTO> searchWebhooks(String keyword, int page, int size);
    void deleteWebhookById(Long id);
    void deleteAllWebhooks();
}