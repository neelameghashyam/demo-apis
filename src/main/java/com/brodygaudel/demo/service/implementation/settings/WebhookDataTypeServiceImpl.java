package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.WebhookDataTypeDTO;
import com.brodygaudel.demo.entity.settings.Webhook;
import com.brodygaudel.demo.entity.settings.WebhookDataType;
import com.brodygaudel.demo.exception.settings.WebhookDataTypeNotFoundException;
import com.brodygaudel.demo.repository.settings.WebhookDataTypeRepository;
import com.brodygaudel.demo.repository.settings.WebhookRepository;
import com.brodygaudel.demo.service.settings.WebhookDataTypeService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WebhookDataTypeServiceImpl implements WebhookDataTypeService {

    private final WebhookDataTypeRepository webhookDataTypeRepository;
    private final WebhookRepository webhookRepository;
    private final Mappers mappers;

    public WebhookDataTypeServiceImpl(WebhookDataTypeRepository webhookDataTypeRepository, WebhookRepository webhookRepository, Mappers mappers) {
        this.webhookDataTypeRepository = webhookDataTypeRepository;
        this.webhookRepository = webhookRepository;
        this.mappers = mappers;
    }

    @Override
    public WebhookDataTypeDTO saveWebhookDataType(WebhookDataTypeDTO webhookDataTypeDTO) {
        log.info("In saveWebhookDataType()");
        WebhookDataType webhookDataType = mappers.fromWebhookDataTypeDTO(webhookDataTypeDTO);
        Webhook webhook = webhookRepository.findById(webhookDataTypeDTO.webhookId())
                .orElseThrow(() -> new IllegalArgumentException("Webhook not found"));
        webhookDataType.setWebhook(webhook);
        WebhookDataType saved = webhookDataTypeRepository.save(webhookDataType);
        log.info("WebhookDataType saved with id: {}", saved.getId());
        return mappers.fromWebhookDataType(saved);
    }

    @Override
    public WebhookDataTypeDTO updateWebhookDataType(WebhookDataTypeDTO webhookDataTypeDTO) throws WebhookDataTypeNotFoundException {
        log.info("In updateWebhookDataType()");
        WebhookDataType webhookDataType = webhookDataTypeRepository.findById(webhookDataTypeDTO.id())
                .orElseThrow(() -> new WebhookDataTypeNotFoundException("WebhookDataType not found"));
        webhookDataType.setDataType(WebhookDataType.DataType.valueOf(webhookDataTypeDTO.dataType()));
        Webhook webhook = webhookRepository.findById(webhookDataTypeDTO.webhookId())
                .orElseThrow(() -> new IllegalArgumentException("Webhook not found"));
        webhookDataType.setWebhook(webhook);
        WebhookDataType updated = webhookDataTypeRepository.save(webhookDataType);
        log.info("WebhookDataType updated");
        return mappers.fromWebhookDataType(updated);
    }

    @Override
    public WebhookDataTypeDTO findWebhookDataTypeById(Long id) throws WebhookDataTypeNotFoundException {
        log.info("In findWebhookDataTypeById()");
        WebhookDataType webhookDataType = webhookDataTypeRepository.findById(id)
                .orElseThrow(() -> new WebhookDataTypeNotFoundException("WebhookDataType not found"));
        log.info("WebhookDataType found with id: {}", webhookDataType.getId());
        return mappers.fromWebhookDataType(webhookDataType);
    }

    @Override
    public List<WebhookDataTypeDTO> findAllWebhookDataTypes() {
        log.info("In findAllWebhookDataTypes()");
        List<WebhookDataType> webhookDataTypes = webhookDataTypeRepository.findAll();
        log.info("All webhook data types found");
        return mappers.fromListOfWebhookDataTypes(webhookDataTypes);
    }

    @Override
    public List<WebhookDataTypeDTO> searchWebhookDataTypes(String keyword, int page, int size) {
        log.info("In searchWebhookDataTypes()");
        Page<WebhookDataType> webhookDataTypePage = webhookDataTypeRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} webhook data types found", webhookDataTypePage.getContent().size());
        return mappers.fromListOfWebhookDataTypes(webhookDataTypePage.getContent());
    }

    @Override
    public void deleteWebhookDataTypeById(Long id) {
        log.info("In deleteWebhookDataTypeById()");
        webhookDataTypeRepository.deleteById(id);
        log.info("WebhookDataType deleted");
    }

    @Override
    public void deleteAllWebhookDataTypes() {
        log.info("In deleteAllWebhookDataTypes()");
        webhookDataTypeRepository.deleteAll();
        log.info("All webhook data types deleted");
    }
}