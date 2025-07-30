package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.InactivityTimeoutDTO;
import com.brodygaudel.demo.exception.settings.InactivityTimeoutNotFoundException;
import com.brodygaudel.demo.service.settings.InactivityTimeoutService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settings/inactivity-timeouts")
public class InactivityTimeoutController {

    private final InactivityTimeoutService service;

    public InactivityTimeoutController(InactivityTimeoutService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<InactivityTimeoutDTO> save(@Valid @RequestBody InactivityTimeoutDTO inactivityTimeoutDTO) {
        InactivityTimeoutDTO saved = service.saveInactivityTimeout(inactivityTimeoutDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<InactivityTimeoutDTO> update(@Valid @RequestBody InactivityTimeoutDTO inactivityTimeoutDTO) throws InactivityTimeoutNotFoundException {
        InactivityTimeoutDTO updated = service.updateInactivityTimeout(inactivityTimeoutDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InactivityTimeoutDTO> findById(@PathVariable Long id) throws InactivityTimeoutNotFoundException {
        InactivityTimeoutDTO inactivityTimeoutDTO = service.findInactivityTimeoutById(id);
        return new ResponseEntity<>(inactivityTimeoutDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<InactivityTimeoutDTO>> findAll() {
        List<InactivityTimeoutDTO> inactivityTimeoutDTOs = service.findAllInactivityTimeouts();
        return new ResponseEntity<>(inactivityTimeoutDTOs, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<InactivityTimeoutDTO>> search(@RequestParam String keyword,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        List<InactivityTimeoutDTO> inactivityTimeoutDTOs = service.searchInactivityTimeouts(keyword, page, size);
        return new ResponseEntity<>(inactivityTimeoutDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteInactivityTimeoutById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        service.deleteAllInactivityTimeouts();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}