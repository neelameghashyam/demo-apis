package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.MailTemplateDTO;
import com.brodygaudel.demo.entity.User;
import com.brodygaudel.demo.entity.settings.MailTemplate;
import com.brodygaudel.demo.exception.settings.MailTemplateNotFoundException;
import com.brodygaudel.demo.repository.UserRepository;
import com.brodygaudel.demo.repository.settings.MailTemplateRepository;
import com.brodygaudel.demo.service.settings.MailTemplateService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MailTemplateServiceImpl implements MailTemplateService {

    private final MailTemplateRepository mailTemplateRepository;
    private final UserRepository userRepository;
    private final Mappers mappers;

    public MailTemplateServiceImpl(MailTemplateRepository mailTemplateRepository, UserRepository userRepository, Mappers mappers) {
        this.mailTemplateRepository = mailTemplateRepository;
        this.userRepository = userRepository;
        this.mappers = mappers;
    }

    @Override
    public MailTemplateDTO saveMailTemplate(MailTemplateDTO mailTemplateDTO) {
        log.info("In saveMailTemplate()");
        MailTemplate mailTemplate = mappers.fromMailTemplateDTO(mailTemplateDTO);
        if (mailTemplateDTO.userId() != null) {
            User user = userRepository.findById(mailTemplateDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            mailTemplate.setUser(user);
        }
        MailTemplate saved = mailTemplateRepository.save(mailTemplate);
        log.info("MailTemplate saved with id: {}", saved.getId());
        return mappers.fromMailTemplate(saved);
    }

    @Override
    public MailTemplateDTO updateMailTemplate(MailTemplateDTO mailTemplateDTO) throws MailTemplateNotFoundException {
        log.info("In updateMailTemplate()");
        MailTemplate mailTemplate = mailTemplateRepository.findById(mailTemplateDTO.id())
                .orElseThrow(() -> new MailTemplateNotFoundException("MailTemplate not found"));
        mailTemplate.setName(mailTemplateDTO.name());
        mailTemplate.setUseCase(mailTemplateDTO.useCase());
        mailTemplate.setSubject(mailTemplateDTO.subject());
        mailTemplate.setActive(mailTemplateDTO.active());
        mailTemplate.setCreatedBy(mailTemplateDTO.createdBy());
        mailTemplate.setCreatedAt(mailTemplateDTO.createdAt());
        mailTemplate.setModifiedBy(mailTemplateDTO.modifiedBy());
        mailTemplate.setModifiedAt(mailTemplateDTO.modifiedAt());
        if (mailTemplateDTO.userId() != null) {
            User user = userRepository.findById(mailTemplateDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            mailTemplate.setUser(user);
        } else {
            mailTemplate.setUser(null);
        }
        MailTemplate updated = mailTemplateRepository.save(mailTemplate);
        log.info("MailTemplate updated");
        return mappers.fromMailTemplate(updated);
    }

    @Override
    public MailTemplateDTO findMailTemplateById(Long id) throws MailTemplateNotFoundException {
        log.info("In findMailTemplateById()");
        MailTemplate mailTemplate = mailTemplateRepository.findById(id)
                .orElseThrow(() -> new MailTemplateNotFoundException("MailTemplate not found"));
        log.info("MailTemplate found with id: {}", mailTemplate.getId());
        return mappers.fromMailTemplate(mailTemplate);
    }

    @Override
    public List<MailTemplateDTO> findAllMailTemplates() {
        log.info("In findAllMailTemplates()");
        List<MailTemplate> mailTemplates = mailTemplateRepository.findAll();
        log.info("All mail templates found");
        return mappers.fromListOfMailTemplates(mailTemplates);
    }

    @Override
    public List<MailTemplateDTO> searchMailTemplates(String keyword, int page, int size) {
        log.info("In searchMailTemplates()");
        Page<MailTemplate> mailTemplatePage = mailTemplateRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} mail templates found", mailTemplatePage.getContent().size());
        return mappers.fromListOfMailTemplates(mailTemplatePage.getContent());
    }

    @Override
    public void deleteMailTemplateById(Long id) {
        log.info("In deleteMailTemplateById()");
        mailTemplateRepository.deleteById(id);
        log.info("MailTemplate deleted");
    }

    @Override
    public void deleteAllMailTemplates() {
        log.info("In deleteAllMailTemplates()");
        mailTemplateRepository.deleteAll();
        log.info("All mail templates deleted");
    }
}