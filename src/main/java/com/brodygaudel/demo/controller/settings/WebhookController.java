package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.WebhookDTO;
import com.brodygaudel.demo.service.settings.WebhookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settings/webhooks")
@RequiredArgsConstructor
public class WebhookController {

    private final WebhookService webhookService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public WebhookDTO save(@RequestBody WebhookDTO webhookDTO) {
        return webhookService.save(webhookDTO);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public WebhookDTO update(@RequestBody WebhookDTO webhookDTO) {
        return webhookService.update(webhookDTO);
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WebhookDTO findById(@PathVariable Long id) {
        return webhookService.findById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<WebhookDTO> findAll() {
        return webhookService.findAll();
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<WebhookDTO> search(@RequestParam String keyword, @RequestParam int page, @RequestParam int size) {
        return webhookService.search(keyword, PageRequest.of(page, size));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        webhookService.deleteById(id);
    }

    @DeleteMapping("/delete/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        webhookService.deleteAll();
    }
}