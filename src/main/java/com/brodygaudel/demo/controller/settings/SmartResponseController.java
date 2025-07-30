package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.SmartResponseDTO;
import com.brodygaudel.demo.exception.settings.SmartResponseNotFoundException;
import com.brodygaudel.demo.service.settings.SmartResponseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settings/smart-responses")
public class SmartResponseController {

    private final SmartResponseService service;

    public SmartResponseController(SmartResponseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SmartResponseDTO> save(@Valid @RequestBody SmartResponseDTO smartResponseDTO) {
        SmartResponseDTO saved = service.saveSmartResponse(smartResponseDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SmartResponseDTO> update(@Valid @RequestBody SmartResponseDTO smartResponseDTO) throws SmartResponseNotFoundException {
        SmartResponseDTO updated = service.updateSmartResponse(smartResponseDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SmartResponseDTO> findById(@PathVariable Long id) throws SmartResponseNotFoundException {
        SmartResponseDTO smartResponseDTO = service.findSmartResponseById(id);
        return new ResponseEntity<>(smartResponseDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SmartResponseDTO>> findAll() {
        List<SmartResponseDTO> smartResponseDTOs = service.findAllSmartResponses();
        return new ResponseEntity<>(smartResponseDTOs, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SmartResponseDTO>> search(@RequestParam String keyword,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        List<SmartResponseDTO> smartResponseDTOs = service.searchSmartResponses(keyword, page, size);
        return new ResponseEntity<>(smartResponseDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteSmartResponseById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        service.deleteAllSmartResponses();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}