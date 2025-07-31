package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.KnowledgeBaseWebsiteDTO;
import com.brodygaudel.demo.entity.settings.KnowledgeBase;
import com.brodygaudel.demo.entity.settings.KnowledgeBaseWebsite;
import com.brodygaudel.demo.exception.settings.KnowledgeBaseWebsiteNotFoundException;
import com.brodygaudel.demo.repository.settings.KnowledgeBaseRepository;
import com.brodygaudel.demo.repository.settings.KnowledgeBaseWebsiteRepository;
import com.brodygaudel.demo.service.settings.KnowledgeBaseWebsiteService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class KnowledgeBaseWebsiteServiceImpl implements KnowledgeBaseWebsiteService {

    private final KnowledgeBaseWebsiteRepository knowledgeBaseWebsiteRepository;
    private final KnowledgeBaseRepository knowledgeBaseRepository;
    private final Mappers mappers;

    public KnowledgeBaseWebsiteServiceImpl(KnowledgeBaseWebsiteRepository knowledgeBaseWebsiteRepository, KnowledgeBaseRepository knowledgeBaseRepository, Mappers mappers) {
        this.knowledgeBaseWebsiteRepository = knowledgeBaseWebsiteRepository;
        this.knowledgeBaseRepository = knowledgeBaseRepository;
        this.mappers = mappers;
    }

    @Override
    public KnowledgeBaseWebsiteDTO saveKnowledgeBaseWebsite(KnowledgeBaseWebsiteDTO knowledgeBaseWebsiteDTO) {
        log.info("In saveKnowledgeBaseWebsite()");
        KnowledgeBaseWebsite knowledgeBaseWebsite = mappers.fromKnowledgeBaseWebsiteDTO(knowledgeBaseWebsiteDTO);
        KnowledgeBase knowledgeBase = knowledgeBaseRepository.findById(knowledgeBaseWebsiteDTO.knowledgeBaseId())
                .orElseThrow(() -> new IllegalArgumentException("KnowledgeBase not found"));
        knowledgeBaseWebsite.setKnowledgeBase(knowledgeBase);
        KnowledgeBaseWebsite saved = knowledgeBaseWebsiteRepository.save(knowledgeBaseWebsite);
        log.info("KnowledgeBaseWebsite saved with id: {}", saved.getId());
        return mappers.fromKnowledgeBaseWebsite(saved);
    }

    @Override
    public KnowledgeBaseWebsiteDTO updateKnowledgeBaseWebsite(KnowledgeBaseWebsiteDTO knowledgeBaseWebsiteDTO) throws KnowledgeBaseWebsiteNotFoundException {
        log.info("In updateKnowledgeBaseWebsite()");
        KnowledgeBaseWebsite knowledgeBaseWebsite = knowledgeBaseWebsiteRepository.findById(knowledgeBaseWebsiteDTO.id())
                .orElseThrow(() -> new KnowledgeBaseWebsiteNotFoundException("KnowledgeBaseWebsite not found"));
        knowledgeBaseWebsite.setWebsite(knowledgeBaseWebsiteDTO.website());
        KnowledgeBase knowledgeBase = knowledgeBaseRepository.findById(knowledgeBaseWebsiteDTO.knowledgeBaseId())
                .orElseThrow(() -> new IllegalArgumentException("KnowledgeBase not found"));
        knowledgeBaseWebsite.setKnowledgeBase(knowledgeBase);
        KnowledgeBaseWebsite updated = knowledgeBaseWebsiteRepository.save(knowledgeBaseWebsite);
        log.info("KnowledgeBaseWebsite updated");
        return mappers.fromKnowledgeBaseWebsite(updated);
    }

    @Override
    public KnowledgeBaseWebsiteDTO findKnowledgeBaseWebsiteById(Long id) throws KnowledgeBaseWebsiteNotFoundException {
        log.info("In findKnowledgeBaseWebsiteById()");
        KnowledgeBaseWebsite knowledgeBaseWebsite = knowledgeBaseWebsiteRepository.findById(id)
                .orElseThrow(() -> new KnowledgeBaseWebsiteNotFoundException("KnowledgeBaseWebsite not found"));
        log.info("KnowledgeBaseWebsite found with id: {}", knowledgeBaseWebsite.getId());
        return mappers.fromKnowledgeBaseWebsite(knowledgeBaseWebsite);
    }

    @Override
    public List<KnowledgeBaseWebsiteDTO> findAllKnowledgeBaseWebsites() {
        log.info("In findAllKnowledgeBaseWebsites()");
        List<KnowledgeBaseWebsite> knowledgeBaseWebsites = knowledgeBaseWebsiteRepository.findAll();
        log.info("All knowledge base websites found");
        return mappers.fromListOfKnowledgeBaseWebsites(knowledgeBaseWebsites);
    }

    @Override
    public List<KnowledgeBaseWebsiteDTO> searchKnowledgeBaseWebsites(String keyword, int page, int size) {
        log.info("In searchKnowledgeBaseWebsites()");
        Page<KnowledgeBaseWebsite> knowledgeBaseWebsitePage = knowledgeBaseWebsiteRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} knowledge base websites found", knowledgeBaseWebsitePage.getContent().size());
        return mappers.fromListOfKnowledgeBaseWebsites(knowledgeBaseWebsitePage.getContent());
    }

    @Override
    public void deleteKnowledgeBaseWebsiteById(Long id) {
        log.info("In deleteKnowledgeBaseWebsiteById()");
        knowledgeBaseWebsiteRepository.deleteById(id);
        log.info("KnowledgeBaseWebsite deleted");
    }

    @Override
    public void deleteAllKnowledgeBaseWebsites() {
        log.info("In deleteAllKnowledgeBaseWebsites()");
        knowledgeBaseWebsiteRepository.deleteAll();
        log.info("All knowledge base websites deleted");
    }
}