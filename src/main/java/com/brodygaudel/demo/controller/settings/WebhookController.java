package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.WebhookDTO;
import com.brodygaudel.demo.exception.settings.WebhookNotFoundException;
import com.brodygaudel.demo.service.settings.WebhookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settings/webhooks")
public class WebhookController {

    private final WebhookService service;

    public WebhookController(WebhookService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WebhookDTO> save(@Valid @RequestBody WebhookDTO webhookDTO) {
        WebhookDTO saved = service.saveWebhook(webhookDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<WebhookDTO> update(@Valid @RequestBody WebhookDTO webhookDTO) throws WebhookNotFoundException {
        WebhookDTO updated = service.updateWebhook(webhookDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebhookDTO> findById(@PathVariable Long id) throws WebhookNotFoundException {
        WebhookDTO webhookDTO = service.findWebhookById(id);
        return new ResponseEntity<>(webhookDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<WebhookDTO>> findAll() {
        List<WebhookDTO> webhookDTOs = service.findAllWebhooks();
        return new ResponseEntity<>(webhookDTOs, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<WebhookDTO>> search(@RequestParam String keyword,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        List<WebhookDTO> webhookDTOs = service.searchWebhooks(keyword, page, size);
        return new ResponseEntity<>(webhookDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteWebhookById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        service.deleteAllWebhooks();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}