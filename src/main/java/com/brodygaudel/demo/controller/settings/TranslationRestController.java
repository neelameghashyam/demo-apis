package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.TranslationDTO;
import com.brodygaudel.demo.exception.settings.TranslationNotFoundException;
import com.brodygaudel.demo.service.settings.TranslationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settings/translations")
public class TranslationRestController {

    private final TranslationService translationService;

    public TranslationRestController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @PostMapping("/save")
    public TranslationDTO saveTranslation(@Valid @RequestBody TranslationDTO translationDTO) {
        return translationService.saveTranslation(translationDTO);
    }

    @PutMapping("/update")
    public TranslationDTO updateTranslation(@Valid @RequestBody TranslationDTO translationDTO) throws TranslationNotFoundException {
        return translationService.updateTranslation(translationDTO);
    }

    @GetMapping("/get/{id}")
    public TranslationDTO findTranslationById(@PathVariable Long id) throws TranslationNotFoundException {
        return translationService.findTranslationById(id);
    }

    @GetMapping("/list")
    public List<TranslationDTO> findAllTranslations() {
        return translationService.findAllTranslations();
    }

    @GetMapping("/search")
    public List<TranslationDTO> searchTranslations(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return translationService.searchTranslations(keyword, page, size);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTranslationById(@PathVariable Long id) {
        translationService.deleteTranslationById(id);
    }

    @DeleteMapping("/clear")
    public void deleteAllTranslations() {
        translationService.deleteAllTranslations();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}