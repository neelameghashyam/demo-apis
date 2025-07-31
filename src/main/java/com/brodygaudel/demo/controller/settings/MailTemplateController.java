package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.MailTemplateDTO;
import com.brodygaudel.demo.exception.settings.MailTemplateNotFoundException;
import com.brodygaudel.demo.service.settings.MailTemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settings/mail-templates")
public class MailTemplateController {

    private final MailTemplateService mailTemplateService;

    public MailTemplateController(MailTemplateService mailTemplateService) {
        this.mailTemplateService = mailTemplateService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public MailTemplateDTO saveMailTemplate(@RequestBody MailTemplateDTO mailTemplateDTO) {
        return mailTemplateService.saveMailTemplate(mailTemplateDTO);
    }

    @PutMapping("/update")
    public MailTemplateDTO updateMailTemplate(@RequestBody MailTemplateDTO mailTemplateDTO) throws MailTemplateNotFoundException {
        return mailTemplateService.updateMailTemplate(mailTemplateDTO);
    }

    @GetMapping("/find/{id}")
    public MailTemplateDTO findMailTemplateById(@PathVariable Long id) throws MailTemplateNotFoundException {
        return mailTemplateService.findMailTemplateById(id);
    }

    @GetMapping("/all")
    public List<MailTemplateDTO> findAllMailTemplates() {
        return mailTemplateService.findAllMailTemplates();
    }

    @GetMapping("/search")
    public List<MailTemplateDTO> searchMailTemplates(@RequestParam String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return mailTemplateService.searchMailTemplates(keyword, page, size);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMailTemplateById(@PathVariable Long id) {
        mailTemplateService.deleteMailTemplateById(id);
    }

    @DeleteMapping("/delete/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllMailTemplates() {
        mailTemplateService.deleteAllMailTemplates();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        return "An error occurred: " + e.getMessage();
    }
}