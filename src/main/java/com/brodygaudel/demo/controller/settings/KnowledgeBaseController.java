package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.KnowledgeBaseDTO;
import com.brodygaudel.demo.exception.settings.KnowledgeBaseNotFoundException;
import com.brodygaudel.demo.service.settings.KnowledgeBaseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settings/knowledge-bases")
public class KnowledgeBaseController {

    private final KnowledgeBaseService knowledgeBaseService;

    public KnowledgeBaseController(KnowledgeBaseService knowledgeBaseService) {
        this.knowledgeBaseService = knowledgeBaseService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public KnowledgeBaseDTO saveKnowledgeBase(@RequestBody KnowledgeBaseDTO knowledgeBaseDTO) {
        return knowledgeBaseService.saveKnowledgeBase(knowledgeBaseDTO);
    }

    @PutMapping("/update")
    public KnowledgeBaseDTO updateKnowledgeBase(@RequestBody KnowledgeBaseDTO knowledgeBaseDTO) throws KnowledgeBaseNotFoundException {
        return knowledgeBaseService.updateKnowledgeBase(knowledgeBaseDTO);
    }

    @GetMapping("/find/{id}")
    public KnowledgeBaseDTO findKnowledgeBaseById(@PathVariable Long id) throws KnowledgeBaseNotFoundException {
        return knowledgeBaseService.findKnowledgeBaseById(id);
    }

    @GetMapping("/all")
    public List<KnowledgeBaseDTO> findAllKnowledgeBases() {
        return knowledgeBaseService.findAllKnowledgeBases();
    }

    @GetMapping("/search")
    public List<KnowledgeBaseDTO> searchKnowledgeBases(@RequestParam String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return knowledgeBaseService.searchKnowledgeBases(keyword, page, size);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKnowledgeBaseById(@PathVariable Long id) {
        knowledgeBaseService.deleteKnowledgeBaseById(id);
    }

    @DeleteMapping("/delete/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllKnowledgeBases() {
        knowledgeBaseService.deleteAllKnowledgeBases();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        return "An error occurred: " + e.getMessage();
    }
}