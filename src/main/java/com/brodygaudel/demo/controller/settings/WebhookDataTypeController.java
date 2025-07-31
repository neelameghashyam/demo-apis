package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.WebhookDataTypeDTO;
import com.brodygaudel.demo.exception.settings.WebhookDataTypeNotFoundException;
import com.brodygaudel.demo.service.settings.WebhookDataTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settings/webhook-data-types")
public class WebhookDataTypeController {

    private final WebhookDataTypeService webhookDataTypeService;

    public WebhookDataTypeController(WebhookDataTypeService webhookDataTypeService) {
        this.webhookDataTypeService = webhookDataTypeService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public WebhookDataTypeDTO saveWebhookDataType(@RequestBody WebhookDataTypeDTO webhookDataTypeDTO) {
        return webhookDataTypeService.saveWebhookDataType(webhookDataTypeDTO);
    }

    @PutMapping("/update")
    public WebhookDataTypeDTO updateWebhookDataType(@RequestBody WebhookDataTypeDTO webhookDataTypeDTO) throws WebhookDataTypeNotFoundException {
        return webhookDataTypeService.updateWebhookDataType(webhookDataTypeDTO);
    }

    @GetMapping("/find/{id}")
    public WebhookDataTypeDTO findWebhookDataTypeById(@PathVariable Long id) throws WebhookDataTypeNotFoundException {
        return webhookDataTypeService.findWebhookDataTypeById(id);
    }

    @GetMapping("/all")
    public List<WebhookDataTypeDTO> findAllWebhookDataTypes() {
        return webhookDataTypeService.findAllWebhookDataTypes();
    }

    @GetMapping("/search")
    public List<WebhookDataTypeDTO> searchWebhookDataTypes(@RequestParam String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return webhookDataTypeService.searchWebhookDataTypes(keyword, page, size);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWebhookDataTypeById(@PathVariable Long id) {
        webhookDataTypeService.deleteWebhookDataTypeById(id);
    }

    @DeleteMapping("/delete/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllWebhookDataTypes() {
        webhookDataTypeService.deleteAllWebhookDataTypes();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        return "An error occurred: " + e.getMessage();
    }
}