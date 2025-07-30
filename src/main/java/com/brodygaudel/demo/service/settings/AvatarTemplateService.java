package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.AvatarTemplateDTO;
import com.brodygaudel.demo.exception.settings.AvatarTemplateNotFoundException;

import java.util.List;

public interface AvatarTemplateService {
    AvatarTemplateDTO saveAvatarTemplate(AvatarTemplateDTO avatarTemplateDTO);
    AvatarTemplateDTO updateAvatarTemplate(AvatarTemplateDTO avatarTemplateDTO) throws AvatarTemplateNotFoundException;
    AvatarTemplateDTO findAvatarTemplateById(Long id) throws AvatarTemplateNotFoundException;
    List<AvatarTemplateDTO> findAllAvatarTemplates();
    List<AvatarTemplateDTO> searchAvatarTemplates(String keyword, int page, int size);
    void deleteAvatarTemplateById(Long id);
    void deleteAllAvatarTemplates();
}