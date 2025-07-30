package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.TagDTO;
import com.brodygaudel.demo.entity.User;
import com.brodygaudel.demo.entity.settings.Tag;
import com.brodygaudel.demo.exception.settings.TagNotFoundException;
import com.brodygaudel.demo.repository.UserRepository;
import com.brodygaudel.demo.repository.settings.TagRepository;
import com.brodygaudel.demo.service.settings.TagService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final Mappers mappers;

    public TagServiceImpl(TagRepository tagRepository, UserRepository userRepository, Mappers mappers) {
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
        this.mappers = mappers;
    }

    @Override
    public TagDTO saveTag(TagDTO tagDTO) {
        log.info("In saveTag()");
        Tag tag = mappers.fromTagDTO(tagDTO);
        if (tagDTO.userId() != null) {
            User user = userRepository.findById(tagDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            tag.setUser(user);
        }
        Tag saved = tagRepository.save(tag);
        log.info("Tag saved with id: {}", saved.getId());
        return mappers.fromTag(saved);
    }

    @Override
    public TagDTO updateTag(TagDTO tagDTO) throws TagNotFoundException {
        log.info("In updateTag()");
        Tag tag = tagRepository.findById(tagDTO.id())
                .orElseThrow(() -> new TagNotFoundException("Tag not found"));
        tag.setTag(tagDTO.tag());
        tag.setIsDefault(tagDTO.isDefault());
        tag.setCreatedBy(tagDTO.createdBy());
        tag.setCompany(tagDTO.company());
        tag.setCreatedAt(tagDTO.createdAt());
        tag.setUpdatedAt(tagDTO.updatedAt());
        if (tagDTO.userId() != null) {
            User user = userRepository.findById(tagDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            tag.setUser(user);
        } else {
            tag.setUser(null);
        }
        Tag updated = tagRepository.save(tag);
        log.info("Tag updated");
        return mappers.fromTag(updated);
    }

    @Override
    public TagDTO findTagById(Long id) throws TagNotFoundException {
        log.info("In findTagById()");
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new TagNotFoundException("Tag not found"));
        log.info("Tag found with id: {}", tag.getId());
        return mappers.fromTag(tag);
    }

    @Override
    public List<TagDTO> findAllTags() {
        log.info("In findAllTags()");
        List<Tag> tags = tagRepository.findAll();
        log.info("All tags found");
        return mappers.fromListOfTags(tags);
    }

    @Override
    public List<TagDTO> searchTags(String keyword, int page, int size) {
        log.info("In searchTags()");
        Page<Tag> tagPage = tagRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} tags found", tagPage.getContent().size());
        return mappers.fromListOfTags(tagPage.getContent());
    }

    @Override
    public void deleteTagById(Long id) {
        log.info("In deleteTagById()");
        tagRepository.deleteById(id);
        log.info("Tag deleted");
    }

    @Override
    public void deleteAllTags() {
        log.info("In deleteAllTags()");
        tagRepository.deleteAll();
        log.info("All tags deleted");
    }
}