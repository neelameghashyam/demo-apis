package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.GlobalNotificationDTO;
import com.brodygaudel.demo.exception.settings.GlobalNotificationNotFoundException;

import java.util.List;

public interface GlobalNotificationService {
    GlobalNotificationDTO saveGlobalNotification(GlobalNotificationDTO globalNotificationDTO);
    GlobalNotificationDTO updateGlobalNotification(GlobalNotificationDTO globalNotificationDTO) throws GlobalNotificationNotFoundException;
    GlobalNotificationDTO findGlobalNotificationById(Long id) throws GlobalNotificationNotFoundException;
    List<GlobalNotificationDTO> findAllGlobalNotifications();
    List<GlobalNotificationDTO> searchGlobalNotifications(String keyword, int page, int size);
    void deleteGlobalNotificationById(Long id);
    void deleteAllGlobalNotifications();
}