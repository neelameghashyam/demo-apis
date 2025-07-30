package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.AnnouncementDTO;
import com.brodygaudel.demo.entity.User;
import com.brodygaudel.demo.entity.settings.Announcement;
import com.brodygaudel.demo.exception.settings.AnnouncementNotFoundException;
import com.brodygaudel.demo.repository.UserRepository;
import com.brodygaudel.demo.repository.settings.AnnouncementRepository;
import com.brodygaudel.demo.service.settings.AnnouncementService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final UserRepository userRepository;
    private final Mappers mappers;

    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository, UserRepository userRepository, Mappers mappers) {
        this.announcementRepository = announcementRepository;
        this.userRepository = userRepository;
        this.mappers = mappers;
    }

    @Override
    public AnnouncementDTO saveAnnouncement(AnnouncementDTO announcementDTO) {
        log.info("In saveAnnouncement()");
        Announcement announcement = mappers.fromAnnouncementDTO(announcementDTO);
        if (announcementDTO.userId() != null) {
            User user = userRepository.findById(announcementDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            announcement.setUser(user);
        }
        Announcement saved = announcementRepository.save(announcement);
        log.info("Announcement saved with id: {}", saved.getId());
        return mappers.fromAnnouncement(saved);
    }

    @Override
    public AnnouncementDTO updateAnnouncement(AnnouncementDTO announcementDTO) throws AnnouncementNotFoundException {
        log.info("In updateAnnouncement()");
        Announcement announcement = announcementRepository.findById(announcementDTO.id())
                .orElseThrow(() -> new AnnouncementNotFoundException("Announcement not found"));
        announcement.setPageType(Announcement.PageType.valueOf(announcementDTO.pageType()));
        announcement.setTitle(announcementDTO.title());
        announcement.setText(announcementDTO.text());
        announcement.setImageUrl(announcementDTO.imageUrl());
        announcement.setCreatedAt(announcementDTO.createdAt());
        announcement.setUpdatedAt(announcementDTO.updatedAt());
        if (announcementDTO.userId() != null) {
            User user = userRepository.findById(announcementDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            announcement.setUser(user);
        } else {
            announcement.setUser(null);
        }
        Announcement updated = announcementRepository.save(announcement);
        log.info("Announcement updated");
        return mappers.fromAnnouncement(updated);
    }

    @Override
    public AnnouncementDTO findAnnouncementById(Long id) throws AnnouncementNotFoundException {
        log.info("In findAnnouncementById()");
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new AnnouncementNotFoundException("Announcement not found"));
        log.info("Announcement found with id: {}", announcement.getId());
        return mappers.fromAnnouncement(announcement);
    }

    @Override
    public List<AnnouncementDTO> findAllAnnouncements() {
        log.info("In findAllAnnouncements()");
        List<Announcement> announcements = announcementRepository.findAll();
        log.info("All announcements found");
        return mappers.fromListOfAnnouncements(announcements);
    }

    @Override
    public List<AnnouncementDTO> searchAnnouncements(String keyword, int page, int size) {
        log.info("In searchAnnouncements()");
        Page<Announcement> announcementPage = announcementRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} announcements found", announcementPage.getContent().size());
        return mappers.fromListOfAnnouncements(announcementPage.getContent());
    }

    @Override
    public void deleteAnnouncementById(Long id) {
        log.info("In deleteAnnouncementById()");
        announcementRepository.deleteById(id);
        log.info("Announcement deleted");
    }

    @Override
    public void deleteAllAnnouncements() {
        log.info("In deleteAllAnnouncements()");
        announcementRepository.deleteAll();
        log.info("All announcements deleted");
    }
}