package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.WebhookDTO;
import com.brodygaudel.demo.exception.settings.WebhookNotFoundException;
import com.brodygaudel.demo.service.settings.WebhookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settings/webhooks")
public class WebhookController {

    private final WebhookService webhookService;

    public WebhookController(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public WebhookDTO saveWebhook(@RequestBody WebhookDTO webhookDTO) {
        return webhookService.saveWebhook(webhookDTO);
    }

    @PutMapping("/update")
    public WebhookDTO updateWebhook(@RequestBody WebhookDTO webhookDTO) throws WebhookNotFoundException {
        return webhookService.updateWebhook(webhookDTO);
    }

    @GetMapping("/find/{id}")
    public WebhookDTO findWebhookById(@PathVariable Long id) throws WebhookNotFoundException {
        return webhookService.findWebhookById(id);
    }

    @GetMapping("/all")
    public List<WebhookDTO> findAllWebhooks() {
        return webhookService.findAllWebhooks();
    }

    @GetMapping("/search")
    public List<WebhookDTO> searchWebhooks(@RequestParam String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return webhookService.searchWebhooks(keyword, page, size);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWebhookById(@PathVariable Long id) {
        webhookService.deleteWebhookById(id);
    }

    @DeleteMapping("/delete/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllWebhooks() {
        webhookService.deleteAllWebhooks();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        return "An error occurred: " + e.getMessage();
    }
}