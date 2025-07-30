package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.QueuedMessageDTO;
import com.brodygaudel.demo.exception.settings.QueuedMessageNotFoundException;

import java.util.List;

public interface QueuedMessageService {
    QueuedMessageDTO saveQueuedMessage(QueuedMessageDTO queuedMessageDTO);
    QueuedMessageDTO updateQueuedMessage(QueuedMessageDTO queuedMessageDTO) throws QueuedMessageNotFoundException;
    QueuedMessageDTO findQueuedMessageById(Long id) throws QueuedMessageNotFoundException;
    List<QueuedMessageDTO> findAllQueuedMessages();
    List<QueuedMessageDTO> searchQueuedMessages(String keyword, int page, int size);
    void deleteQueuedMessageById(Long id);
    void deleteAllQueuedMessages();
}