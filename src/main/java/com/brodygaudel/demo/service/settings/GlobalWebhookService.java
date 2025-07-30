package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.GlobalWebhookDTO;
import com.brodygaudel.demo.exception.settings.GlobalWebhookNotFoundException;

import java.util.List;

public interface GlobalWebhookService {
    GlobalWebhookDTO saveGlobalWebhook(GlobalWebhookDTO globalWebhookDTO);
    GlobalWebhookDTO updateGlobalWebhook(GlobalWebhookDTO globalWebhookDTO) throws GlobalWebhookNotFoundException;
    GlobalWebhookDTO findGlobalWebhookById(Long id) throws GlobalWebhookNotFoundException;
    List<GlobalWebhookDTO> findAllGlobalWebhooks();
    List<GlobalWebhookDTO> searchGlobalWebhooks(String keyword, int page, int size);
    void deleteGlobalWebhookById(Long id);
    void deleteAllGlobalWebhooks();
}