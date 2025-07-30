package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.AvatarTemplateDTO;
import com.brodygaudel.demo.entity.settings.AvatarTemplate;
import com.brodygaudel.demo.exception.settings.AvatarTemplateNotFoundException;
import com.brodygaudel.demo.repository.settings.AvatarTemplateRepository;
import com.brodygaudel.demo.service.settings.AvatarTemplateService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AvatarTemplateServiceImpl implements AvatarTemplateService {

    private final AvatarTemplateRepository avatarTemplateRepository;
    private final Mappers mappers;

    public AvatarTemplateServiceImpl(AvatarTemplateRepository avatarTemplateRepository, Mappers mappers) {
        this.avatarTemplateRepository = avatarTemplateRepository;
        this.mappers = mappers;
    }

    @Override
    public AvatarTemplateDTO saveAvatarTemplate(AvatarTemplateDTO avatarTemplateDTO) {
        log.info("In saveAvatarTemplate()");
        AvatarTemplate avatarTemplate = mappers.fromAvatarTemplateDTO(avatarTemplateDTO);
        AvatarTemplate saved = avatarTemplateRepository.save(avatarTemplate);
        log.info("AvatarTemplate saved with id: {}", saved.getId());
        return mappers.fromAvatarTemplate(saved);
    }

    @Override
    public AvatarTemplateDTO updateAvatarTemplate(AvatarTemplateDTO avatarTemplateDTO) throws AvatarTemplateNotFoundException {
        log.info("In updateAvatarTemplate()");
        AvatarTemplate avatarTemplate = avatarTemplateRepository.findById(avatarTemplateDTO.id())
                .orElseThrow(() -> new AvatarTemplateNotFoundException("AvatarTemplate not found"));
        avatarTemplate.setElementName(avatarTemplateDTO.elementName());
        avatarTemplate.setGender(AvatarTemplate.Gender.valueOf(avatarTemplateDTO.gender()));
        avatarTemplate.setImageUrl(avatarTemplateDTO.imageUrl());
        avatarTemplate.setCreatedAt(avatarTemplateDTO.createdAt());
        avatarTemplate.setUpdatedAt(avatarTemplateDTO.updatedAt());
        AvatarTemplate updated = avatarTemplateRepository.save(avatarTemplate);
        log.info("AvatarTemplate updated");
        return mappers.fromAvatarTemplate(updated);
    }

    @Override
    public AvatarTemplateDTO findAvatarTemplateById(Long id) throws AvatarTemplateNotFoundException {
        log.info("In findAvatarTemplateById()");
        AvatarTemplate avatarTemplate = avatarTemplateRepository.findById(id)
                .orElseThrow(() -> new AvatarTemplateNotFoundException("AvatarTemplate not found"));
        log.info("AvatarTemplate found with id: {}", avatarTemplate.getId());
        return mappers.fromAvatarTemplate(avatarTemplate);
    }

    @Override
    public List<AvatarTemplateDTO> findAllAvatarTemplates() {
        log.info("In findAllAvatarTemplates()");
        List<AvatarTemplate> avatarTemplates = avatarTemplateRepository.findAll();
        log.info("All avatar templates found");
        return mappers.fromListOfAvatarTemplates(avatarTemplates);
    }

    @Override
    public List<AvatarTemplateDTO> searchAvatarTemplates(String keyword, int page, int size) {
        log.info("In searchAvatarTemplates()");
        Page<AvatarTemplate> avatarTemplatePage = avatarTemplateRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} avatar templates found", avatarTemplatePage.getContent().size());
        return mappers.fromListOfAvatarTemplates(avatarTemplatePage.getContent());
    }

    @Override
    public void deleteAvatarTemplateById(Long id) {
        log.info("In deleteAvatarTemplateById()");
        avatarTemplateRepository.deleteById(id);
        log.info("AvatarTemplate deleted");
    }

    @Override
    public void deleteAllAvatarTemplates() {
        log.info("In deleteAllAvatarTemplates()");
        avatarTemplateRepository.deleteAll();
        log.info("All avatar templates deleted");
    }
}