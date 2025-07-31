package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.TemplateDTO;
import com.brodygaudel.demo.exception.settings.TemplateNotFoundException;
import com.brodygaudel.demo.service.settings.TemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settings/templates")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public TemplateDTO saveTemplate(@RequestBody TemplateDTO templateDTO) {
        return templateService.saveTemplate(templateDTO);
    }

    @PutMapping("/update")
    public TemplateDTO updateTemplate(@RequestBody TemplateDTO templateDTO) throws TemplateNotFoundException {
        return templateService.updateTemplate(templateDTO);
    }

    @GetMapping("/find/{id}")
    public TemplateDTO findTemplateById(@PathVariable Long id) throws TemplateNotFoundException {
        return templateService.findTemplateById(id);
    }

    @GetMapping("/all")
    public List<TemplateDTO> findAllTemplates() {
        return templateService.findAllTemplates();
    }

    @GetMapping("/search")
    public List<TemplateDTO> searchTemplates(@RequestParam String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return templateService.searchTemplates(keyword, page, size);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTemplateById(@PathVariable Long id) {
        templateService.deleteTemplateById(id);
    }

    @DeleteMapping("/delete/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllTemplates() {
        templateService.deleteAllTemplates();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        return "An error occurred: " + e.getMessage();
    }
}