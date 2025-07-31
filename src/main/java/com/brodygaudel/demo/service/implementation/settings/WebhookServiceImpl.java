package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.WebhookDTO;
import com.brodygaudel.demo.entity.User;
import com.brodygaudel.demo.entity.settings.Webhook;
import com.brodygaudel.demo.entity.settings.WebhookDataType;
import com.brodygaudel.demo.exception.settings.WebhookNotFoundException;
import com.brodygaudel.demo.repository.UserRepository;
import com.brodygaudel.demo.repository.settings.WebhookDataTypeRepository;
import com.brodygaudel.demo.repository.settings.WebhookRepository;
import com.brodygaudel.demo.service.settings.WebhookService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WebhookServiceImpl implements WebhookService {

    private final WebhookRepository webhookRepository;
    private final WebhookDataTypeRepository webhookDataTypeRepository;
    private final UserRepository userRepository;
    private final Mappers mappers;

    public WebhookServiceImpl(WebhookRepository webhookRepository, WebhookDataTypeRepository webhookDataTypeRepository, UserRepository userRepository, Mappers mappers) {
        this.webhookRepository = webhookRepository;
        this.webhookDataTypeRepository = webhookDataTypeRepository;
        this.userRepository = userRepository;
        this.mappers = mappers;
    }

    @Override
    public WebhookDTO saveWebhook(WebhookDTO webhookDTO) {
        log.info("In saveWebhook()");
        Webhook webhook = mappers.fromWebhookDTO(webhookDTO);
        if (webhookDTO.userId() != null) {
            User user = userRepository.findById(webhookDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            webhook.setUser(user);
        }
        Webhook saved = webhookRepository.save(webhook);
        if (webhookDTO.dataTypes() != null) {
            for (String dataType : webhookDTO.dataTypes()) {
                WebhookDataType wdt = WebhookDataType.builder()
                        .webhook(saved)
                        .dataType(WebhookDataType.DataType.valueOf(dataType))
                        .build();
                webhookDataTypeRepository.save(wdt);
            }
        }
        log.info("Webhook saved with id: {}", saved.getId());
        return mappers.fromWebhook(saved);
    }

    @Override
    public WebhookDTO updateWebhook(WebhookDTO webhookDTO) throws WebhookNotFoundException {
        log.info("In updateWebhook()");
        Webhook webhook = webhookRepository.findById(webhookDTO.id())
                .orElseThrow(() -> new WebhookNotFoundException("Webhook not found"));
        webhook.setEvent(Webhook.Event.valueOf(webhookDTO.event()));
        webhook.setTargetUrl(webhookDTO.targetUrl());
        webhook.setCreatedBy(webhookDTO.createdBy());
        webhook.setCompany(webhookDTO.company());
        webhook.setCreatedAt(webhookDTO.createdAt());
        webhook.setUpdatedAt(webhookDTO.updatedAt());
        if (webhookDTO.userId() != null) {
            User user = userRepository.findById(webhookDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            webhook.setUser(user);
        } else {
            webhook.setUser(null);
        }
        Webhook updated = webhookRepository.save(webhook);
        if (webhookDTO.dataTypes() != null) {
            webhookDataTypeRepository.deleteByWebhookId(webhook.getId());
            for (String dataType : webhookDTO.dataTypes()) {
                WebhookDataType wdt = WebhookDataType.builder()
                        .webhook(updated)
                        .dataType(WebhookDataType.DataType.valueOf(dataType))
                        .build();
                webhookDataTypeRepository.save(wdt);
            }
        }
        log.info("Webhook updated");
        return mappers.fromWebhook(updated);
    }

    @Override
    public WebhookDTO findWebhookById(Long id) throws WebhookNotFoundException {
        log.info("In findWebhookById()");
        Webhook webhook = webhookRepository.findById(id)
                .orElseThrow(() -> new WebhookNotFoundException("Webhook not found"));
        log.info("Webhook found with id: {}", webhook.getId());
        return mappers.fromWebhook(webhook);
    }

    @Override
    public List<WebhookDTO> findAllWebhooks() {
        log.info("In findAllWebhooks()");
        List<Webhook> webhooks = webhookRepository.findAll();
        log.info("All webhooks found");
        return mappers.fromListOfWebhooks(webhooks);
    }

    @Override
    public List<WebhookDTO> searchWebhooks(String keyword, int page, int size) {
        log.info("In searchWebhooks()");
        Page<Webhook> webhookPage = webhookRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} webhooks found", webhookPage.getContent().size());
        return mappers.fromListOfWebhooks(webhookPage.getContent());
    }

    @Override
    public void deleteWebhookById(Long id) {
        log.info("In deleteWebhookById()");
        webhookRepository.deleteById(id);
        log.info("Webhook deleted");
    }

    @Override
    public void deleteAllWebhooks() {
        log.info("In deleteAllWebhooks()");
        webhookRepository.deleteAll();
        log.info("All webhooks deleted");
    }
}