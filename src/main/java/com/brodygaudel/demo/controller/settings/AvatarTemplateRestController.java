package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.AvatarTemplateDTO;
import com.brodygaudel.demo.exception.settings.AvatarTemplateNotFoundException;
import com.brodygaudel.demo.service.settings.AvatarTemplateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settings/avatar-templates")
public class AvatarTemplateRestController {

    private final AvatarTemplateService avatarTemplateService;

    public AvatarTemplateRestController(AvatarTemplateService avatarTemplateService) {
        this.avatarTemplateService = avatarTemplateService;
    }

    @PostMapping("/save")
    public AvatarTemplateDTO saveAvatarTemplate(@Valid @RequestBody AvatarTemplateDTO avatarTemplateDTO) {
        return avatarTemplateService.saveAvatarTemplate(avatarTemplateDTO);
    }

    @PutMapping("/update")
    public AvatarTemplateDTO updateAvatarTemplate(@Valid @RequestBody AvatarTemplateDTO avatarTemplateDTO) throws AvatarTemplateNotFoundException {
        return avatarTemplateService.updateAvatarTemplate(avatarTemplateDTO);
    }

    @GetMapping("/get/{id}")
    public AvatarTemplateDTO findAvatarTemplateById(@PathVariable Long id) throws AvatarTemplateNotFoundException {
        return avatarTemplateService.findAvatarTemplateById(id);
    }

    @GetMapping("/list")
    public List<AvatarTemplateDTO> findAllAvatarTemplates() {
        return avatarTemplateService.findAllAvatarTemplates();
    }

    @GetMapping("/search")
    public List<AvatarTemplateDTO> searchAvatarTemplates(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return avatarTemplateService.searchAvatarTemplates(keyword, page, size);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAvatarTemplateById(@PathVariable Long id) {
        avatarTemplateService.deleteAvatarTemplateById(id);
    }

    @DeleteMapping("/clear")
    public void deleteAllAvatarTemplates() {
        avatarTemplateService.deleteAllAvatarTemplates();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}