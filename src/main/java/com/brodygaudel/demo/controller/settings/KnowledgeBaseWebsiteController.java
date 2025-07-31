package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.KnowledgeBaseWebsiteDTO;
import com.brodygaudel.demo.exception.settings.KnowledgeBaseWebsiteNotFoundException;
import com.brodygaudel.demo.service.settings.KnowledgeBaseWebsiteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settings/knowledge-base-websites")
public class KnowledgeBaseWebsiteController {

    private final KnowledgeBaseWebsiteService knowledgeBaseWebsiteService;

    public KnowledgeBaseWebsiteController(KnowledgeBaseWebsiteService knowledgeBaseWebsiteService) {
        this.knowledgeBaseWebsiteService = knowledgeBaseWebsiteService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public KnowledgeBaseWebsiteDTO saveKnowledgeBaseWebsite(@RequestBody KnowledgeBaseWebsiteDTO knowledgeBaseWebsiteDTO) {
        return knowledgeBaseWebsiteService.saveKnowledgeBaseWebsite(knowledgeBaseWebsiteDTO);
    }

    @PutMapping("/update")
    public KnowledgeBaseWebsiteDTO updateKnowledgeBaseWebsite(@RequestBody KnowledgeBaseWebsiteDTO knowledgeBaseWebsiteDTO) throws KnowledgeBaseWebsiteNotFoundException {
        return knowledgeBaseWebsiteService.updateKnowledgeBaseWebsite(knowledgeBaseWebsiteDTO);
    }

    @GetMapping("/find/{id}")
    public KnowledgeBaseWebsiteDTO findKnowledgeBaseWebsiteById(@PathVariable Long id) throws KnowledgeBaseWebsiteNotFoundException {
        return knowledgeBaseWebsiteService.findKnowledgeBaseWebsiteById(id);
    }

    @GetMapping("/all")
    public List<KnowledgeBaseWebsiteDTO> findAllKnowledgeBaseWebsites() {
        return knowledgeBaseWebsiteService.findAllKnowledgeBaseWebsites();
    }

    @GetMapping("/search")
    public List<KnowledgeBaseWebsiteDTO> searchKnowledgeBaseWebsites(@RequestParam String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return knowledgeBaseWebsiteService.searchKnowledgeBaseWebsites(keyword, page, size);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKnowledgeBaseWebsiteById(@PathVariable Long id) {
        knowledgeBaseWebsiteService.deleteKnowledgeBaseWebsiteById(id);
    }

    @DeleteMapping("/delete/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllKnowledgeBaseWebsites() {
        knowledgeBaseWebsiteService.deleteAllKnowledgeBaseWebsites();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        return "An error occurred: " + e.getMessage();
    }
}