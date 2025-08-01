package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.WebhookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WebhookService {
    WebhookDTO save(WebhookDTO webhookDTO);
    WebhookDTO update(WebhookDTO webhookDTO);
    WebhookDTO findById(Long id);
    List<WebhookDTO> findAll();
    Page<WebhookDTO> search(String keyword, Pageable pageable);
    void deleteById(Long id);
    void deleteAll();
}