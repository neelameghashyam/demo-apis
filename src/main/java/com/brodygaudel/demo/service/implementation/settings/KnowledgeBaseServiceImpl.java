package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.KnowledgeBaseDTO;
import com.brodygaudel.demo.entity.User;
import com.brodygaudel.demo.entity.settings.KnowledgeBase;
import com.brodygaudel.demo.entity.settings.KnowledgeBaseWebsite;
import com.brodygaudel.demo.exception.settings.KnowledgeBaseNotFoundException;
import com.brodygaudel.demo.repository.UserRepository;
import com.brodygaudel.demo.repository.settings.KnowledgeBaseRepository;
import com.brodygaudel.demo.repository.settings.KnowledgeBaseWebsiteRepository;
import com.brodygaudel.demo.service.settings.KnowledgeBaseService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class KnowledgeBaseServiceImpl implements KnowledgeBaseService {

    private final KnowledgeBaseRepository knowledgeBaseRepository;
    private final KnowledgeBaseWebsiteRepository knowledgeBaseWebsiteRepository;
    private final UserRepository userRepository;
    private final Mappers mappers;

    public KnowledgeBaseServiceImpl(KnowledgeBaseRepository knowledgeBaseRepository, KnowledgeBaseWebsiteRepository knowledgeBaseWebsiteRepository, UserRepository userRepository, Mappers mappers) {
        this.knowledgeBaseRepository = knowledgeBaseRepository;
        this.knowledgeBaseWebsiteRepository = knowledgeBaseWebsiteRepository;
        this.userRepository = userRepository;
        this.mappers = mappers;
    }

    @Override
    public KnowledgeBaseDTO saveKnowledgeBase(KnowledgeBaseDTO knowledgeBaseDTO) {
        log.info("In saveKnowledgeBase()");
        KnowledgeBase knowledgeBase = mappers.fromKnowledgeBaseDTO(knowledgeBaseDTO);
        if (knowledgeBaseDTO.userId() != null) {
            User user = userRepository.findById(knowledgeBaseDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            knowledgeBase.setUser(user);
        }
        KnowledgeBase saved = knowledgeBaseRepository.save(knowledgeBase);
        if (knowledgeBaseDTO.websites() != null) {
            for (String website : knowledgeBaseDTO.websites()) {
                KnowledgeBaseWebsite kbw = KnowledgeBaseWebsite.builder()
                        .knowledgeBase(saved)
                        .website(website)
                        .build();
                knowledgeBaseWebsiteRepository.save(kbw);
            }
        }
        log.info("KnowledgeBase saved with id: {}", saved.getId());
        return mappers.fromKnowledgeBase(saved);
    }

    @Override
    public KnowledgeBaseDTO updateKnowledgeBase(KnowledgeBaseDTO knowledgeBaseDTO) throws KnowledgeBaseNotFoundException {
        log.info("In updateKnowledgeBase()");
        KnowledgeBase knowledgeBase = knowledgeBaseRepository.findById(knowledgeBaseDTO.id())
                .orElseThrow(() -> new KnowledgeBaseNotFoundException("KnowledgeBase not found"));
        knowledgeBase.setQuestionTitle(knowledgeBaseDTO.questionTitle());
        knowledgeBase.setAnswerInformation(knowledgeBaseDTO.answerInformation());
        knowledgeBase.setKeywords(knowledgeBaseDTO.keywords());
        knowledgeBase.setCreatedAt(knowledgeBaseDTO.createdAt());
        knowledgeBase.setUpdatedAt(knowledgeBaseDTO.updatedAt());
        if (knowledgeBaseDTO.userId() != null) {
            User user = userRepository.findById(knowledgeBaseDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            knowledgeBase.setUser(user);
        } else {
            knowledgeBase.setUser(null);
        }
        KnowledgeBase updated = knowledgeBaseRepository.save(knowledgeBase);
        if (knowledgeBaseDTO.websites() != null) {
            knowledgeBaseWebsiteRepository.deleteByKnowledgeBaseId(knowledgeBase.getId());
            for (String website : knowledgeBaseDTO.websites()) {
                KnowledgeBaseWebsite kbw = KnowledgeBaseWebsite.builder()
                        .knowledgeBase(updated)
                        .website(website)
                        .build();
                knowledgeBaseWebsiteRepository.save(kbw);
            }
        }
        log.info("KnowledgeBase updated");
        return mappers.fromKnowledgeBase(updated);
    }

    @Override
    public KnowledgeBaseDTO findKnowledgeBaseById(Long id) throws KnowledgeBaseNotFoundException {
        log.info("In findKnowledgeBaseById()");
        KnowledgeBase knowledgeBase = knowledgeBaseRepository.findById(id)
                .orElseThrow(() -> new KnowledgeBaseNotFoundException("KnowledgeBase not found"));
        log.info("KnowledgeBase found with id: {}", knowledgeBase.getId());
        return mappers.fromKnowledgeBase(knowledgeBase);
    }

    @Override
    public List<KnowledgeBaseDTO> findAllKnowledgeBases() {
        log.info("In findAllKnowledgeBases()");
        List<KnowledgeBase> knowledgeBases = knowledgeBaseRepository.findAll();
        log.info("All knowledge bases found");
        return mappers.fromListOfKnowledgeBases(knowledgeBases);
    }

    @Override
    public List<KnowledgeBaseDTO> searchKnowledgeBases(String keyword, int page, int size) {
        log.info("In searchKnowledgeBases()");
        Page<KnowledgeBase> knowledgeBasePage = knowledgeBaseRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} knowledge bases found", knowledgeBasePage.getContent().size());
        return mappers.fromListOfKnowledgeBases(knowledgeBasePage.getContent());
    }

    @Override
    public void deleteKnowledgeBaseById(Long id) {
        log.info("In deleteKnowledgeBaseById()");
        knowledgeBaseRepository.deleteById(id);
        log.info("KnowledgeBase deleted");
    }

    @Override
    public void deleteAllKnowledgeBases() {
        log.info("In deleteAllKnowledgeBases()");
        knowledgeBaseRepository.deleteAll();
        log.info("All knowledge bases deleted");
    }
}