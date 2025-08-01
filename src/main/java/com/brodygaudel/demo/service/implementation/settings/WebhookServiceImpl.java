package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.WebhookDTO;
import com.brodygaudel.demo.entity.settings.Webhook;
import com.brodygaudel.demo.exception.settings.WebhookNotFoundException;
import com.brodygaudel.demo.repository.settings.WebhookRepository;
import com.brodygaudel.demo.service.settings.WebhookService;
import com.brodygaudel.demo.util.Mappers;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebhookServiceImpl implements WebhookService {

    private final WebhookRepository webhookRepository;
    private final Mappers mappers;

    @Override
    public WebhookDTO save(WebhookDTO webhookDTO) {
        Webhook webhook = mappers.fromWebhookDTO(webhookDTO);
        webhook = webhookRepository.save(webhook);
        return mappers.fromWebhook(webhook);
    }

    @Override
    public WebhookDTO update(WebhookDTO webhookDTO) {
        if (!webhookRepository.existsById(webhookDTO.id())) {
            throw new WebhookNotFoundException("Webhook not found with id: " + webhookDTO.id());
        }
        Webhook webhook = mappers.fromWebhookDTO(webhookDTO);
        webhook = webhookRepository.save(webhook);
        return mappers.fromWebhook(webhook);
    }

    @Override
    public WebhookDTO findById(Long id) {
        Webhook webhook = webhookRepository.findById(id)
                .orElseThrow(() -> new WebhookNotFoundException("Webhook not found with id: " + id));
        return mappers.fromWebhook(webhook);
    }

    @Override
    public List<WebhookDTO> findAll() {
        return mappers.fromListOfWebhooks(webhookRepository.findAll());
    }

    @Override
    public Page<WebhookDTO> search(String keyword, Pageable pageable) {
        return webhookRepository.search(keyword, pageable)
                .map(mappers::fromWebhook);
    }

    @Override
    public void deleteById(Long id) {
        if (!webhookRepository.existsById(id)) {
            throw new WebhookNotFoundException("Webhook not found with id: " + id);
        }
        webhookRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        webhookRepository.deleteAll();
    }
}