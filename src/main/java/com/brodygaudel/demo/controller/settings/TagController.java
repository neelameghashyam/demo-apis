package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.TagDTO;
import com.brodygaudel.demo.exception.settings.TagNotFoundException;
import com.brodygaudel.demo.service.settings.TagService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settings/tags")
public class TagController {

    private final TagService service;

    public TagController(TagService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TagDTO> save(@Valid @RequestBody TagDTO tagDTO) {
        TagDTO saved = service.saveTag(tagDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TagDTO> update(@Valid @RequestBody TagDTO tagDTO) throws TagNotFoundException {
        TagDTO updated = service.updateTag(tagDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDTO> findById(@PathVariable Long id) throws TagNotFoundException {
        TagDTO tagDTO = service.findTagById(id);
        return new ResponseEntity<>(tagDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TagDTO>> findAll() {
        List<TagDTO> tagDTOs = service.findAllTags();
        return new ResponseEntity<>(tagDTOs, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TagDTO>> search(@RequestParam String keyword,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        List<TagDTO> tagDTOs = service.searchTags(keyword, page, size);
        return new ResponseEntity<>(tagDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteTagById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        service.deleteAllTags();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}