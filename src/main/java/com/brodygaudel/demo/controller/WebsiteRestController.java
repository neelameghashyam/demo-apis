package com.brodygaudel.demo.controller;

import com.brodygaudel.demo.dto.WebsiteDTO;
import com.brodygaudel.demo.exception.WebsiteNotFoundException;
import com.brodygaudel.demo.service.WebsiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/websites")
public class WebsiteRestController {

    private final WebsiteService websiteService;

    public WebsiteRestController(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @PostMapping("/save")
    public WebsiteDTO saveWebsite(@RequestBody WebsiteDTO websiteDTO) {
        return websiteService.saveWebsite(websiteDTO);
    }

    @PutMapping("/update")
    public WebsiteDTO updateWebsite(@RequestBody WebsiteDTO websiteDTO) throws WebsiteNotFoundException {
        return websiteService.updateWebsite(websiteDTO);
    }

    @GetMapping("/get/{id}")
    public WebsiteDTO findWebsiteById(@PathVariable Long id) throws WebsiteNotFoundException {
        return websiteService.findWebsiteById(id);
    }

    @GetMapping("/list")
    public List<WebsiteDTO> findAllWebsites() {
        return websiteService.findAllWebsites();
    }

    @GetMapping("/search")
    public List<WebsiteDTO> searchWebsites(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return websiteService.searchWebsites(keyword, page, size);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteWebsiteById(@PathVariable Long id) {
        websiteService.deleteWebsiteById(id);
    }

    @DeleteMapping("/clear")
    public void deleteAllWebsites() {
        websiteService.deleteAllWebsites();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}