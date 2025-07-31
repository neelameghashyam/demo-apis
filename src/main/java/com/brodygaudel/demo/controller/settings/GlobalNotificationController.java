package com.brodygaudel.demo.controller.settings;

import com.brodygaudel.demo.dto.settings.GlobalNotificationDTO;
import com.brodygaudel.demo.exception.settings.GlobalNotificationNotFoundException;
import com.brodygaudel.demo.service.settings.GlobalNotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settings/global-notifications")
public class GlobalNotificationController {

    private final GlobalNotificationService globalNotificationService;

    public GlobalNotificationController(GlobalNotificationService globalNotificationService) {
        this.globalNotificationService = globalNotificationService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public GlobalNotificationDTO saveGlobalNotification(@RequestBody GlobalNotificationDTO globalNotificationDTO) {
        return globalNotificationService.saveGlobalNotification(globalNotificationDTO);
    }

    @PutMapping("/update")
    public GlobalNotificationDTO updateGlobalNotification(@RequestBody GlobalNotificationDTO globalNotificationDTO) throws GlobalNotificationNotFoundException {
        return globalNotificationService.updateGlobalNotification(globalNotificationDTO);
    }

    @GetMapping("/find/{id}")
    public GlobalNotificationDTO findGlobalNotificationById(@PathVariable Long id) throws GlobalNotificationNotFoundException {
        return globalNotificationService.findGlobalNotificationById(id);
    }

    @GetMapping("/all")
    public List<GlobalNotificationDTO> findAllGlobalNotifications() {
        return globalNotificationService.findAllGlobalNotifications();
    }

    @GetMapping("/search")
    public List<GlobalNotificationDTO> searchGlobalNotifications(@RequestParam String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return globalNotificationService.searchGlobalNotifications(keyword, page, size);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGlobalNotificationById(@PathVariable Long id) {
        globalNotificationService.deleteGlobalNotificationById(id);
    }

    @DeleteMapping("/delete/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllGlobalNotifications() {
        globalNotificationService.deleteAllGlobalNotifications();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        return "An error occurred: " + e.getMessage();
    }
}