package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.EyeCatcherDTO;
import com.brodygaudel.demo.exception.settings.EyeCatcherNotFoundException;
import com.brodygaudel.demo.service.settings.EyeCatcherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settings/eye-catchers")
public class EyeCatcherRestController {

    private final EyeCatcherService eyeCatcherService;

    public EyeCatcherRestController(EyeCatcherService eyeCatcherService) {
        this.eyeCatcherService = eyeCatcherService;
    }

    @PostMapping("/save")
    public EyeCatcherDTO saveEyeCatcher(@Valid @RequestBody EyeCatcherDTO eyeCatcherDTO) {
        return eyeCatcherService.saveEyeCatcher(eyeCatcherDTO);
    }

    @PutMapping("/update")
    public EyeCatcherDTO updateEyeCatcher(@Valid @RequestBody EyeCatcherDTO eyeCatcherDTO) throws EyeCatcherNotFoundException {
        return eyeCatcherService.updateEyeCatcher(eyeCatcherDTO);
    }

    @GetMapping("/get/{id}")
    public EyeCatcherDTO findEyeCatcherById(@PathVariable Long id) throws EyeCatcherNotFoundException {
        return eyeCatcherService.findEyeCatcherById(id);
    }

    @GetMapping("/list")
    public List<EyeCatcherDTO> findAllEyeCatchers() {
        return eyeCatcherService.findAllEyeCatchers();
    }

    @GetMapping("/search")
    public List<EyeCatcherDTO> searchEyeCatchers(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return eyeCatcherService.searchEyeCatchers(keyword, page, size);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEyeCatcherById(@PathVariable Long id) {
        eyeCatcherService.deleteEyeCatcherById(id);
    }

    @DeleteMapping("/clear")
    public void deleteAllEyeCatchers() {
        eyeCatcherService.deleteAllEyeCatchers();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}