package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.IntegrationDTO;
import com.brodygaudel.demo.exception.settings.IntegrationNotFoundException;
import com.brodygaudel.demo.service.settings.IntegrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settings/integrations")
public class IntegrationController {

    private final IntegrationService service;

    public IntegrationController(IntegrationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<IntegrationDTO> save(@Valid @RequestBody IntegrationDTO integrationDTO) {
        IntegrationDTO saved = service.saveIntegration(integrationDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<IntegrationDTO> update(@Valid @RequestBody IntegrationDTO integrationDTO) throws IntegrationNotFoundException {
        IntegrationDTO updated = service.updateIntegration(integrationDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IntegrationDTO> findById(@PathVariable Long id) throws IntegrationNotFoundException {
        IntegrationDTO integrationDTO = service.findIntegrationById(id);
        return new ResponseEntity<>(integrationDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<IntegrationDTO>> findAll() {
        List<IntegrationDTO> integrationDTOs = service.findAllIntegrations();
        return new ResponseEntity<>(integrationDTOs, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<IntegrationDTO>> search(@RequestParam String keyword,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        List<IntegrationDTO> integrationDTOs = service.searchIntegrations(keyword, page, size);
        return new ResponseEntity<>(integrationDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteIntegrationById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        service.deleteAllIntegrations();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}