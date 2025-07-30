package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.EyeCatcherDTO;
import com.brodygaudel.demo.entity.User;
import com.brodygaudel.demo.entity.settings.EyeCatcher;
import com.brodygaudel.demo.exception.settings.EyeCatcherNotFoundException;
import com.brodygaudel.demo.repository.UserRepository;
import com.brodygaudel.demo.repository.settings.EyeCatcherRepository;
import com.brodygaudel.demo.service.settings.EyeCatcherService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EyeCatcherServiceImpl implements EyeCatcherService {

    private final EyeCatcherRepository eyeCatcherRepository;
    private final UserRepository userRepository;
    private final Mappers mappers;

    public EyeCatcherServiceImpl(EyeCatcherRepository eyeCatcherRepository, UserRepository userRepository, Mappers mappers) {
        this.eyeCatcherRepository = eyeCatcherRepository;
        this.userRepository = userRepository;
        this.mappers = mappers;
    }

    @Override
    public EyeCatcherDTO saveEyeCatcher(EyeCatcherDTO eyeCatcherDTO) {
        log.info("In saveEyeCatcher()");
        EyeCatcher eyeCatcher = mappers.fromEyeCatcherDTO(eyeCatcherDTO);
        if (eyeCatcherDTO.userId() != null) {
            User user = userRepository.findById(eyeCatcherDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            eyeCatcher.setUser(user);
        }
        EyeCatcher saved = eyeCatcherRepository.save(eyeCatcher);
        log.info("EyeCatcher saved with id: {}", saved.getId());
        return mappers.fromEyeCatcher(saved);
    }

    @Override
    public EyeCatcherDTO updateEyeCatcher(EyeCatcherDTO eyeCatcherDTO) throws EyeCatcherNotFoundException {
        log.info("In updateEyeCatcher()");
        EyeCatcher eyeCatcher = eyeCatcherRepository.findById(eyeCatcherDTO.id())
                .orElseThrow(() -> new EyeCatcherNotFoundException("EyeCatcher not found"));
        eyeCatcher.setTitle(eyeCatcherDTO.title());
        eyeCatcher.setText(eyeCatcherDTO.text());
        eyeCatcher.setBackgroundColor(eyeCatcherDTO.backgroundColor());
        eyeCatcher.setTextColor(eyeCatcherDTO.textColor());
        eyeCatcher.setImageUrl(eyeCatcherDTO.imageUrl());
        eyeCatcher.setCreatedBy(eyeCatcherDTO.createdBy());
        eyeCatcher.setCompany(eyeCatcherDTO.company());
        eyeCatcher.setCreatedAt(eyeCatcherDTO.createdAt());
        eyeCatcher.setUpdatedAt(eyeCatcherDTO.updatedAt());
        if (eyeCatcherDTO.userId() != null) {
            User user = userRepository.findById(eyeCatcherDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            eyeCatcher.setUser(user);
        } else {
            eyeCatcher.setUser(null);
        }
        EyeCatcher updated = eyeCatcherRepository.save(eyeCatcher);
        log.info("EyeCatcher updated");
        return mappers.fromEyeCatcher(updated);
    }

    @Override
    public EyeCatcherDTO findEyeCatcherById(Long id) throws EyeCatcherNotFoundException {
        log.info("In findEyeCatcherById()");
        EyeCatcher eyeCatcher = eyeCatcherRepository.findById(id)
                .orElseThrow(() -> new EyeCatcherNotFoundException("EyeCatcher not found"));
        log.info("EyeCatcher found with id: {}", eyeCatcher.getId());
        return mappers.fromEyeCatcher(eyeCatcher);
    }

    @Override
    public List<EyeCatcherDTO> findAllEyeCatchers() {
        log.info("In findAllEyeCatchers()");
        List<EyeCatcher> eyeCatchers = eyeCatcherRepository.findAll();
        log.info("All eye catchers found");
        return mappers.fromListOfEyeCatchers(eyeCatchers);
    }

    @Override
    public List<EyeCatcherDTO> searchEyeCatchers(String keyword, int page, int size) {
        log.info("In searchEyeCatchers()");
        Page<EyeCatcher> eyeCatcherPage = eyeCatcherRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} eye catchers found", eyeCatcherPage.getContent().size());
        return mappers.fromListOfEyeCatchers(eyeCatcherPage.getContent());
    }

    @Override
    public void deleteEyeCatcherById(Long id) {
        log.info("In deleteEyeCatcherById()");
        eyeCatcherRepository.deleteById(id);
        log.info("EyeCatcher deleted");
    }

    @Override
    public void deleteAllEyeCatchers() {
        log.info("In deleteAllEyeCatchers()");
        eyeCatcherRepository.deleteAll();
        log.info("All eye catchers deleted");
    }
}