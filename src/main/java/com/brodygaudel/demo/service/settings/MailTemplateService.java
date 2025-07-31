package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.MailTemplateDTO;
import com.brodygaudel.demo.exception.settings.MailTemplateNotFoundException;

import java.util.List;

public interface MailTemplateService {
    MailTemplateDTO saveMailTemplate(MailTemplateDTO mailTemplateDTO);
    MailTemplateDTO updateMailTemplate(MailTemplateDTO mailTemplateDTO) throws MailTemplateNotFoundException;
    MailTemplateDTO findMailTemplateById(Long id) throws MailTemplateNotFoundException;
    List<MailTemplateDTO> findAllMailTemplates();
    List<MailTemplateDTO> searchMailTemplates(String keyword, int page, int size);
    void deleteMailTemplateById(Long id);
    void deleteAllMailTemplates();
}