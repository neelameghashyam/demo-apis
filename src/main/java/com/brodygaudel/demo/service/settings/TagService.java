package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.TagDTO;
import com.brodygaudel.demo.exception.settings.TagNotFoundException;

import java.util.List;

public interface TagService {
    TagDTO saveTag(TagDTO tagDTO);
    TagDTO updateTag(TagDTO tagDTO) throws TagNotFoundException;
    TagDTO findTagById(Long id) throws TagNotFoundException;
    List<TagDTO> findAllTags();
    List<TagDTO> searchTags(String keyword, int page, int size);
    void deleteTagById(Long id);
    void deleteAllTags();
}