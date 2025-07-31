package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.TemplateDTO;
import com.brodygaudel.demo.entity.User;
import com.brodygaudel.demo.entity.settings.Template;
import com.brodygaudel.demo.exception.settings.TemplateNotFoundException;
import com.brodygaudel.demo.repository.UserRepository;
import com.brodygaudel.demo.repository.settings.TemplateRepository;
import com.brodygaudel.demo.service.settings.TemplateService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;
    private final UserRepository userRepository;
    private final Mappers mappers;

    public TemplateServiceImpl(TemplateRepository templateRepository, UserRepository userRepository, Mappers mappers) {
        this.templateRepository = templateRepository;
        this.userRepository = userRepository;
        this.mappers = mappers;
    }

    @Override
    public TemplateDTO saveTemplate(TemplateDTO templateDTO) {
        log.info("In saveTemplate()");
        Template template = mappers.fromTemplateDTO(templateDTO);
        if (templateDTO.userId() != null) {
            User user = userRepository.findById(templateDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            template.setUser(user);
        }
        Template saved = templateRepository.save(template);
        log.info("Template saved with id: {}", saved.getId());
        return mappers.fromTemplate(saved);
    }

    @Override
    public TemplateDTO updateTemplate(TemplateDTO templateDTO) throws TemplateNotFoundException {
        log.info("In updateTemplate()");
        Template template = templateRepository.findById(templateDTO.id())
                .orElseThrow(() -> new TemplateNotFoundException("Template not found"));
        template.setBusinessCategory(templateDTO.businessCategory());
        template.setBusinessSubcategory(templateDTO.businessSubcategory());
        template.setCreatedBy(templateDTO.createdBy());
        template.setCreatedAt(templateDTO.createdAt());
        template.setUpdatedAt(templateDTO.updatedAt());
        if (templateDTO.userId() != null) {
            User user = userRepository.findById(templateDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            template.setUser(user);
        } else {
            template.setUser(null);
        }
        Template updated = templateRepository.save(template);
        log.info("Template updated");
        return mappers.fromTemplate(updated);
    }

    @Override
    public TemplateDTO findTemplateById(Long id) throws TemplateNotFoundException {
        log.info("In findTemplateById()");
        Template template = templateRepository.findById(id)
                .orElseThrow(() -> new TemplateNotFoundException("Template not found"));
        log.info("Template found with id: {}", template.getId());
        return mappers.fromTemplate(template);
    }

    @Override
    public List<TemplateDTO> findAllTemplates() {
        log.info("In findAllTemplates()");
        List<Template> templates = templateRepository.findAll();
        log.info("All templates found");
        return mappers.fromListOfTemplates(templates);
    }

    @Override
    public List<TemplateDTO> searchTemplates(String keyword, int page, int size) {
        log.info("In searchTemplates()");
        Page<Template> templatePage = templateRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} templates found", templatePage.getContent().size());
        return mappers.fromListOfTemplates(templatePage.getContent());
    }

    @Override
    public void deleteTemplateById(Long id) {
        log.info("In deleteTemplateById()");
        templateRepository.deleteById(id);
        log.info("Template deleted");
    }

    @Override
    public void deleteAllTemplates() {
        log.info("In deleteAllTemplates()");
        templateRepository.deleteAll();
        log.info("All templates deleted");
    }
}