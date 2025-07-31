package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.TemplateDTO;
import com.brodygaudel.demo.exception.settings.TemplateNotFoundException;

import java.util.List;

public interface TemplateService {
    TemplateDTO saveTemplate(TemplateDTO templateDTO);
    TemplateDTO updateTemplate(TemplateDTO templateDTO) throws TemplateNotFoundException;
    TemplateDTO findTemplateById(Long id) throws TemplateNotFoundException;
    List<TemplateDTO> findAllTemplates();
    List<TemplateDTO> searchTemplates(String keyword, int page, int size);
    void deleteTemplateById(Long id);
    void deleteAllTemplates();
}