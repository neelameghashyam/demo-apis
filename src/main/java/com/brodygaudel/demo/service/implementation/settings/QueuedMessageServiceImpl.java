package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.QueuedMessageDTO;
import com.brodygaudel.demo.entity.User;
import com.brodygaudel.demo.entity.settings.QueuedMessage;
import com.brodygaudel.demo.exception.settings.QueuedMessageNotFoundException;
import com.brodygaudel.demo.repository.UserRepository;
import com.brodygaudel.demo.repository.settings.QueuedMessageRepository;
import com.brodygaudel.demo.service.settings.QueuedMessageService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QueuedMessageServiceImpl implements QueuedMessageService {

    private final QueuedMessageRepository queuedMessageRepository;
    private final UserRepository userRepository;
    private final Mappers mappers;

    public QueuedMessageServiceImpl(QueuedMessageRepository queuedMessageRepository, UserRepository userRepository, Mappers mappers) {
        this.queuedMessageRepository = queuedMessageRepository;
        this.userRepository = userRepository;
        this.mappers = mappers;
    }

    @Override
    public QueuedMessageDTO saveQueuedMessage(QueuedMessageDTO queuedMessageDTO) {
        log.info("In saveQueuedMessage()");
        QueuedMessage queuedMessage = mappers.fromQueuedMessageDTO(queuedMessageDTO);
        if (queuedMessageDTO.userId() != null) {
            User user = userRepository.findById(queuedMessageDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            queuedMessage.setUser(user);
        }
        QueuedMessage saved = queuedMessageRepository.save(queuedMessage);
        log.info("QueuedMessage saved with id: {}", saved.getId());
        return mappers.fromQueuedMessage(saved);
    }

    @Override
    public QueuedMessageDTO updateQueuedMessage(QueuedMessageDTO queuedMessageDTO) throws QueuedMessageNotFoundException {
        log.info("In updateQueuedMessage()");
        QueuedMessage queuedMessage = queuedMessageRepository.findById(queuedMessageDTO.id())
                .orElseThrow(() -> new QueuedMessageNotFoundException("QueuedMessage not found"));
        queuedMessage.setMessage(queuedMessageDTO.message());
        queuedMessage.setBackgroundColor(queuedMessageDTO.backgroundColor());
        queuedMessage.setTextColor(queuedMessageDTO.textColor());
        queuedMessage.setImageUrl(queuedMessageDTO.imageUrl());
        queuedMessage.setCreatedBy(queuedMessageDTO.createdBy());
        queuedMessage.setCompany(queuedMessageDTO.company());
        queuedMessage.setCreatedAt(queuedMessageDTO.createdAt());
        queuedMessage.setUpdatedAt(queuedMessageDTO.updatedAt());
        if (queuedMessageDTO.userId() != null) {
            User user = userRepository.findById(queuedMessageDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            queuedMessage.setUser(user);
        } else {
            queuedMessage.setUser(null);
        }
        QueuedMessage updated = queuedMessageRepository.save(queuedMessage);
        log.info("QueuedMessage updated");
        return mappers.fromQueuedMessage(updated);
    }

    @Override
    public QueuedMessageDTO findQueuedMessageById(Long id) throws QueuedMessageNotFoundException {
        log.info("In findQueuedMessageById()");
        QueuedMessage queuedMessage = queuedMessageRepository.findById(id)
                .orElseThrow(() -> new QueuedMessageNotFoundException("QueuedMessage not found"));
        log.info("QueuedMessage found with id: {}", queuedMessage.getId());
        return mappers.fromQueuedMessage(queuedMessage);
    }

    @Override
    public List<QueuedMessageDTO> findAllQueuedMessages() {
        log.info("In findAllQueuedMessages()");
        List<QueuedMessage> queuedMessages = queuedMessageRepository.findAll();
        log.info("All queued messages found");
        return mappers.fromListOfQueuedMessages(queuedMessages);
    }

    @Override
    public List<QueuedMessageDTO> searchQueuedMessages(String keyword, int page, int size) {
        log.info("In searchQueuedMessages()");
        Page<QueuedMessage> queuedMessagePage = queuedMessageRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} queued messages found", queuedMessagePage.getContent().size());
        return mappers.fromListOfQueuedMessages(queuedMessagePage.getContent());
    }

    @Override
    public void deleteQueuedMessageById(Long id) {
        log.info("In deleteQueuedMessageById()");
        queuedMessageRepository.deleteById(id);
        log.info("QueuedMessage deleted");
    }

    @Override
    public void deleteAllQueuedMessages() {
        log.info("In deleteAllQueuedMessages()");
        queuedMessageRepository.deleteAll();
        log.info("All queued messages deleted");
    }
}