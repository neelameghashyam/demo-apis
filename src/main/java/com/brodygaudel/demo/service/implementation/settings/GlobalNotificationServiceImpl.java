package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.GlobalNotificationDTO;
import com.brodygaudel.demo.entity.User;
import com.brodygaudel.demo.entity.settings.GlobalNotification;
import com.brodygaudel.demo.exception.settings.GlobalNotificationNotFoundException;
import com.brodygaudel.demo.repository.UserRepository;
import com.brodygaudel.demo.repository.settings.GlobalNotificationRepository;
import com.brodygaudel.demo.service.settings.GlobalNotificationService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GlobalNotificationServiceImpl implements GlobalNotificationService {

    private final GlobalNotificationRepository globalNotificationRepository;
    private final UserRepository userRepository;
    private final Mappers mappers;

    public GlobalNotificationServiceImpl(GlobalNotificationRepository globalNotificationRepository, UserRepository userRepository, Mappers mappers) {
        this.globalNotificationRepository = globalNotificationRepository;
        this.userRepository = userRepository;
        this.mappers = mappers;
    }

    @Override
    public GlobalNotificationDTO saveGlobalNotification(GlobalNotificationDTO globalNotificationDTO) {
        log.info("In saveGlobalNotification()");
        GlobalNotification globalNotification = mappers.fromGlobalNotificationDTO(globalNotificationDTO);
        if (globalNotificationDTO.userId() != null) {
            User user = userRepository.findById(globalNotificationDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            globalNotification.setUser(user);
        }
        GlobalNotification saved = globalNotificationRepository.save(globalNotification);
        log.info("GlobalNotification saved with id: {}", saved.getId());
        return mappers.fromGlobalNotification(saved);
    }

    @Override
    public GlobalNotificationDTO updateGlobalNotification(GlobalNotificationDTO globalNotificationDTO) throws GlobalNotificationNotFoundException {
        log.info("In updateGlobalNotification()");
        GlobalNotification globalNotification = globalNotificationRepository.findById(globalNotificationDTO.id())
                .orElseThrow(() -> new GlobalNotificationNotFoundException("GlobalNotification not found"));
        globalNotification.setUseSameEmail(globalNotificationDTO.useSameEmail());
        globalNotification.setNotificationsEmail(globalNotificationDTO.notificationsEmail());
        globalNotification.setNotifyLead(globalNotificationDTO.notifyLead());
        globalNotification.setNotifyServiceChat(globalNotificationDTO.notifyServiceChat());
        globalNotification.setCreatedAt(globalNotificationDTO.createdAt());
        globalNotification.setUpdatedAt(globalNotificationDTO.updatedAt());
        if (globalNotificationDTO.userId() != null) {
            User user = userRepository.findById(globalNotificationDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            globalNotification.setUser(user);
        } else {
            globalNotification.setUser(null);
        }
        GlobalNotification updated = globalNotificationRepository.save(globalNotification);
        log.info("GlobalNotification updated");
        return mappers.fromGlobalNotification(updated);
    }

    @Override
    public GlobalNotificationDTO findGlobalNotificationById(Long id) throws GlobalNotificationNotFoundException {
        log.info("In findGlobalNotificationById()");
        GlobalNotification globalNotification = globalNotificationRepository.findById(id)
                .orElseThrow(() -> new GlobalNotificationNotFoundException("GlobalNotification not found"));
        log.info("GlobalNotification found with id: {}", globalNotification.getId());
        return mappers.fromGlobalNotification(globalNotification);
    }

    @Override
    public List<GlobalNotificationDTO> findAllGlobalNotifications() {
        log.info("In findAllGlobalNotifications()");
        List<GlobalNotification> globalNotifications = globalNotificationRepository.findAll();
        log.info("All global notifications found");
        return mappers.fromListOfGlobalNotifications(globalNotifications);
    }

    @Override
    public List<GlobalNotificationDTO> searchGlobalNotifications(String keyword, int page, int size) {
        log.info("In searchGlobalNotifications()");
        Page<GlobalNotification> globalNotificationPage = globalNotificationRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} global notifications found", globalNotificationPage.getContent().size());
        return mappers.fromListOfGlobalNotifications(globalNotificationPage.getContent());
    }

    @Override
    public void deleteGlobalNotificationById(Long id) {
        log.info("In deleteGlobalNotificationById()");
        globalNotificationRepository.deleteById(id);
        log.info("GlobalNotification deleted");
    }

    @Override
    public void deleteAllGlobalNotifications() {
        log.info("In deleteAllGlobalNotifications()");
        globalNotificationRepository.deleteAll();
        log.info("All global notifications deleted");
    }
}