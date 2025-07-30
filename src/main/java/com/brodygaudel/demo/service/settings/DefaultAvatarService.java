package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.DefaultAvatarDTO;
import com.brodygaudel.demo.exception.settings.DefaultAvatarNotFoundException;

import java.util.List;

public interface DefaultAvatarService {
    DefaultAvatarDTO saveDefaultAvatar(DefaultAvatarDTO defaultAvatarDTO);
    DefaultAvatarDTO updateDefaultAvatar(DefaultAvatarDTO defaultAvatarDTO) throws DefaultAvatarNotFoundException;
    DefaultAvatarDTO findDefaultAvatarById(Long id) throws DefaultAvatarNotFoundException;
    List<DefaultAvatarDTO> findAllDefaultAvatars();
    List<DefaultAvatarDTO> searchDefaultAvatars(String keyword, int page, int size);
    void deleteDefaultAvatarById(Long id);
    void deleteAllDefaultAvatars();
}