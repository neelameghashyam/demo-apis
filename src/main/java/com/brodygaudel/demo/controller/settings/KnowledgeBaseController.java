package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.KnowledgeBaseDTO;
import com.brodygaudel.demo.service.settings.KnowledgeBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settings/knowledge-bases")
@RequiredArgsConstructor
public class KnowledgeBaseController {

    private final KnowledgeBaseService knowledgeBaseService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public KnowledgeBaseDTO save(@RequestBody KnowledgeBaseDTO knowledgeBaseDTO) {
        return knowledgeBaseService.save(knowledgeBaseDTO);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public KnowledgeBaseDTO update(@RequestBody KnowledgeBaseDTO knowledgeBaseDTO) {
        return knowledgeBaseService.update(knowledgeBaseDTO);
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public KnowledgeBaseDTO findById(@PathVariable Long id) {
        return knowledgeBaseService.findById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<KnowledgeBaseDTO> findAll() {
        return knowledgeBaseService.findAll();
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<KnowledgeBaseDTO> search(@RequestParam String keyword, @RequestParam int page, @RequestParam int size) {
        return knowledgeBaseService.search(keyword, PageRequest.of(page, size));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        knowledgeBaseService.deleteById(id);
    }

    @DeleteMapping("/delete/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        knowledgeBaseService.deleteAll();
    }
}