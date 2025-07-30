package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.AnnouncementDTO;
import com.brodygaudel.demo.exception.settings.AnnouncementNotFoundException;

import java.util.List;

public interface AnnouncementService {
    AnnouncementDTO saveAnnouncement(AnnouncementDTO announcementDTO);
    AnnouncementDTO updateAnnouncement(AnnouncementDTO announcementDTO) throws AnnouncementNotFoundException;
    AnnouncementDTO findAnnouncementById(Long id) throws AnnouncementNotFoundException;
    List<AnnouncementDTO> findAllAnnouncements();
    List<AnnouncementDTO> searchAnnouncements(String keyword, int page, int size);
    void deleteAnnouncementById(Long id);
    void deleteAllAnnouncements();
}