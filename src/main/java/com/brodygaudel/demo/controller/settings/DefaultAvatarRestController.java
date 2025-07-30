package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.DefaultAvatarDTO;
import com.brodygaudel.demo.exception.settings.DefaultAvatarNotFoundException;
import com.brodygaudel.demo.service.settings.DefaultAvatarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settings/default-avatars")
public class DefaultAvatarRestController {

    private final DefaultAvatarService defaultAvatarService;

    public DefaultAvatarRestController(DefaultAvatarService defaultAvatarService) {
        this.defaultAvatarService = defaultAvatarService;
    }

    @PostMapping("/save")
    public DefaultAvatarDTO saveDefaultAvatar(@Valid @RequestBody DefaultAvatarDTO defaultAvatarDTO) {
        return defaultAvatarService.saveDefaultAvatar(defaultAvatarDTO);
    }

    @PutMapping("/update")
    public DefaultAvatarDTO updateDefaultAvatar(@Valid @RequestBody DefaultAvatarDTO defaultAvatarDTO) throws DefaultAvatarNotFoundException {
        return defaultAvatarService.updateDefaultAvatar(defaultAvatarDTO);
    }

    @GetMapping("/get/{id}")
    public DefaultAvatarDTO findDefaultAvatarById(@PathVariable Long id) throws DefaultAvatarNotFoundException {
        return defaultAvatarService.findDefaultAvatarById(id);
    }

    @GetMapping("/list")
    public List<DefaultAvatarDTO> findAllDefaultAvatars() {
        return defaultAvatarService.findAllDefaultAvatars();
    }

    @GetMapping("/search")
    public List<DefaultAvatarDTO> searchDefaultAvatars(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return defaultAvatarService.searchDefaultAvatars(keyword, page, size);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDefaultAvatarById(@PathVariable Long id) {
        defaultAvatarService.deleteDefaultAvatarById(id);
    }

    @DeleteMapping("/clear")
    public void deleteAllDefaultAvatars() {
        defaultAvatarService.deleteAllDefaultAvatars();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}