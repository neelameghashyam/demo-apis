package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.DefaultAvatarDTO;
import com.brodygaudel.demo.entity.User;
import com.brodygaudel.demo.entity.settings.DefaultAvatar;
import com.brodygaudel.demo.exception.settings.DefaultAvatarNotFoundException;
import com.brodygaudel.demo.repository.UserRepository;
import com.brodygaudel.demo.repository.settings.DefaultAvatarRepository;
import com.brodygaudel.demo.service.settings.DefaultAvatarService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DefaultAvatarServiceImpl implements DefaultAvatarService {

    private final DefaultAvatarRepository defaultAvatarRepository;
    private final UserRepository userRepository;
    private final Mappers mappers;

    public DefaultAvatarServiceImpl(DefaultAvatarRepository defaultAvatarRepository, UserRepository userRepository, Mappers mappers) {
        this.defaultAvatarRepository = defaultAvatarRepository;
        this.userRepository = userRepository;
        this.mappers = mappers;
    }

    @Override
    public DefaultAvatarDTO saveDefaultAvatar(DefaultAvatarDTO defaultAvatarDTO) {
        log.info("In saveDefaultAvatar()");
        DefaultAvatar defaultAvatar = mappers.fromDefaultAvatarDTO(defaultAvatarDTO);
        User user = userRepository.findById(defaultAvatarDTO.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        defaultAvatar.setUser(user);
        DefaultAvatar saved = defaultAvatarRepository.save(defaultAvatar);
        log.info("DefaultAvatar saved with id: {}", saved.getId());
        return mappers.fromDefaultAvatar(saved);
    }

    @Override
    public DefaultAvatarDTO updateDefaultAvatar(DefaultAvatarDTO defaultAvatarDTO) throws DefaultAvatarNotFoundException {
        log.info("In updateDefaultAvatar()");
        DefaultAvatar defaultAvatar = defaultAvatarRepository.findById(defaultAvatarDTO.id())
                .orElseThrow(() -> new DefaultAvatarNotFoundException("DefaultAvatar not found"));
        defaultAvatar.setName(defaultAvatarDTO.name());
        defaultAvatar.setJobTitle(defaultAvatarDTO.jobTitle());
        defaultAvatar.setAvatarImageUrl(defaultAvatarDTO.avatarImageUrl());
        defaultAvatar.setCreatedAt(defaultAvatarDTO.createdAt());
        defaultAvatar.setUpdatedAt(defaultAvatarDTO.updatedAt());
        User user = userRepository.findById(defaultAvatarDTO.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        defaultAvatar.setUser(user);
        DefaultAvatar updated = defaultAvatarRepository.save(defaultAvatar);
        log.info("DefaultAvatar updated");
        return mappers.fromDefaultAvatar(updated);
    }

    @Override
    public DefaultAvatarDTO findDefaultAvatarById(Long id) throws DefaultAvatarNotFoundException {
        log.info("In findDefaultAvatarById()");
        DefaultAvatar defaultAvatar = defaultAvatarRepository.findById(id)
                .orElseThrow(() -> new DefaultAvatarNotFoundException("DefaultAvatar not found"));
        log.info("DefaultAvatar found with id: {}", defaultAvatar.getId());
        return mappers.fromDefaultAvatar(defaultAvatar);
    }

    @Override
    public List<DefaultAvatarDTO> findAllDefaultAvatars() {
        log.info("In findAllDefaultAvatars()");
        List<DefaultAvatar> defaultAvatars = defaultAvatarRepository.findAll();
        log.info("All default avatars found");
        return mappers.fromListOfDefaultAvatars(defaultAvatars);
    }

    @Override
    public List<DefaultAvatarDTO> searchDefaultAvatars(String keyword, int page, int size) {
        log.info("In searchDefaultAvatars()");
        Page<DefaultAvatar> defaultAvatarPage = defaultAvatarRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} default avatars found", defaultAvatarPage.getContent().size());
        return mappers.fromListOfDefaultAvatars(defaultAvatarPage.getContent());
    }

    @Override
    public void deleteDefaultAvatarById(Long id) {
        log.info("In deleteDefaultAvatarById()");
        defaultAvatarRepository.deleteById(id);
        log.info("DefaultAvatar deleted");
    }

    @Override
    public void deleteAllDefaultAvatars() {
        log.info("In deleteAllDefaultAvatars()");
        defaultAvatarRepository.deleteAll();
        log.info("All default avatars deleted");
    }
}