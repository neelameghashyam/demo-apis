package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.QueuedMessageDTO;
import com.brodygaudel.demo.exception.settings.QueuedMessageNotFoundException;
import com.brodygaudel.demo.service.settings.QueuedMessageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settings/queued-messages")
public class QueuedMessageController {

    private final QueuedMessageService service;

    public QueuedMessageController(QueuedMessageService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<QueuedMessageDTO> save(@Valid @RequestBody QueuedMessageDTO queuedMessageDTO) {
        QueuedMessageDTO saved = service.saveQueuedMessage(queuedMessageDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<QueuedMessageDTO> update(@Valid @RequestBody QueuedMessageDTO queuedMessageDTO) throws QueuedMessageNotFoundException {
        QueuedMessageDTO updated = service.updateQueuedMessage(queuedMessageDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QueuedMessageDTO> findById(@PathVariable Long id) throws QueuedMessageNotFoundException {
        QueuedMessageDTO queuedMessageDTO = service.findQueuedMessageById(id);
        return new ResponseEntity<>(queuedMessageDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<QueuedMessageDTO>> findAll() {
        List<QueuedMessageDTO> queuedMessageDTOs = service.findAllQueuedMessages();
        return new ResponseEntity<>(queuedMessageDTOs, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<QueuedMessageDTO>> search(@RequestParam String keyword,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        List<QueuedMessageDTO> queuedMessageDTOs = service.searchQueuedMessages(keyword, page, size);
        return new ResponseEntity<>(queuedMessageDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteQueuedMessageById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        service.deleteAllQueuedMessages();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}