package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.SmartResponseDTO;
import com.brodygaudel.demo.service.settings.SmartResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settings/smart-responses")
@RequiredArgsConstructor
public class SmartResponseController {

    private final SmartResponseService smartResponseService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public SmartResponseDTO save(@RequestBody SmartResponseDTO smartResponseDTO) {
        return smartResponseService.save(smartResponseDTO);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public SmartResponseDTO update(@RequestBody SmartResponseDTO smartResponseDTO) {
        return smartResponseService.update(smartResponseDTO);
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SmartResponseDTO findById(@PathVariable Long id) {
        return smartResponseService.findById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<SmartResponseDTO> findAll() {
        return smartResponseService.findAll();
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<SmartResponseDTO> search(@RequestParam String keyword, @RequestParam int page, @RequestParam int size) {
        return smartResponseService.search(keyword, PageRequest.of(page, size));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        smartResponseService.deleteById(id);
    }

    @DeleteMapping("/delete/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        smartResponseService.deleteAll();
    }
}