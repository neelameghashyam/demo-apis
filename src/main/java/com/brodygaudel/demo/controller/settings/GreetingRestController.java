package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.GreetingDTO;
import com.brodygaudel.demo.exception.settings.GreetingNotFoundException;
import com.brodygaudel.demo.service.settings.GreetingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settings/greetings")
public class GreetingRestController {

    private final GreetingService greetingService;

    public GreetingRestController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @PostMapping("/save")
    public GreetingDTO saveGreeting(@Valid @RequestBody GreetingDTO greetingDTO) {
        return greetingService.saveGreeting(greetingDTO);
    }

    @PutMapping("/update")
    public GreetingDTO updateGreeting(@Valid @RequestBody GreetingDTO greetingDTO) throws GreetingNotFoundException {
        return greetingService.updateGreeting(greetingDTO);
    }

    @GetMapping("/get/{id}")
    public GreetingDTO findGreetingById(@PathVariable Long id) throws GreetingNotFoundException {
        return greetingService.findGreetingById(id);
    }

    @GetMapping("/list")
    public List<GreetingDTO> findAllGreetings() {
        return greetingService.findAllGreetings();
    }

    @GetMapping("/search")
    public List<GreetingDTO> searchGreetings(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return greetingService.searchGreetings(keyword, page, size);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGreetingById(@PathVariable Long id) {
        greetingService.deleteGreetingById(id);
    }

    @DeleteMapping("/clear")
    public void deleteAllGreetings() {
        greetingService.deleteAllGreetings();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}