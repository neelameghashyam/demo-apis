package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.GlobalWebhookDTO;
import com.brodygaudel.demo.exception.settings.GlobalWebhookNotFoundException;
import com.brodygaudel.demo.service.settings.GlobalWebhookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settings/global-webhooks")
public class GlobalWebhookController {

    private final GlobalWebhookService service;

    public GlobalWebhookController(GlobalWebhookService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<GlobalWebhookDTO> save(@Valid @RequestBody GlobalWebhookDTO globalWebhookDTO) {
        GlobalWebhookDTO saved = service.saveGlobalWebhook(globalWebhookDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<GlobalWebhookDTO> update(@Valid @RequestBody GlobalWebhookDTO globalWebhookDTO) throws GlobalWebhookNotFoundException {
        GlobalWebhookDTO updated = service.updateGlobalWebhook(globalWebhookDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalWebhookDTO> findById(@PathVariable Long id) throws GlobalWebhookNotFoundException {
        GlobalWebhookDTO globalWebhookDTO = service.findGlobalWebhookById(id);
        return new ResponseEntity<>(globalWebhookDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GlobalWebhookDTO>> findAll() {
        List<GlobalWebhookDTO> globalWebhookDTOs = service.findAllGlobalWebhooks();
        return new ResponseEntity<>(globalWebhookDTOs, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<GlobalWebhookDTO>> search(@RequestParam String keyword,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        List<GlobalWebhookDTO> globalWebhookDTOs = service.searchGlobalWebhooks(keyword, page, size);
        return new ResponseEntity<>(globalWebhookDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteGlobalWebhookById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        service.deleteAllGlobalWebhooks();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}