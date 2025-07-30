package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.AnnouncementDTO;
import com.brodygaudel.demo.exception.settings.AnnouncementNotFoundException;
import com.brodygaudel.demo.service.settings.AnnouncementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settings/announcements")
public class AnnouncementRestController {

    private final AnnouncementService announcementService;

    public AnnouncementRestController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @PostMapping("/save")
    public AnnouncementDTO saveAnnouncement(@Valid @RequestBody AnnouncementDTO announcementDTO) {
        return announcementService.saveAnnouncement(announcementDTO);
    }

    @PutMapping("/update")
    public AnnouncementDTO updateAnnouncement(@Valid @RequestBody AnnouncementDTO announcementDTO) throws AnnouncementNotFoundException {
        return announcementService.updateAnnouncement(announcementDTO);
    }

    @GetMapping("/get/{id}")
    public AnnouncementDTO findAnnouncementById(@PathVariable Long id) throws AnnouncementNotFoundException {
        return announcementService.findAnnouncementById(id);
    }

    @GetMapping("/list")
    public List<AnnouncementDTO> findAllAnnouncements() {
        return announcementService.findAllAnnouncements();
    }

    @GetMapping("/search")
    public List<AnnouncementDTO> searchAnnouncements(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return announcementService.searchAnnouncements(keyword, page, size);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAnnouncementById(@PathVariable Long id) {
        announcementService.deleteAnnouncementById(id);
    }

    @DeleteMapping("/clear")
    public void deleteAllAnnouncements() {
        announcementService.deleteAllAnnouncements();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}