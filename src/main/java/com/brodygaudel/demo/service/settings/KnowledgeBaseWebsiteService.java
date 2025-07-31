package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.KnowledgeBaseWebsiteDTO;
import com.brodygaudel.demo.exception.settings.KnowledgeBaseWebsiteNotFoundException;

import java.util.List;

public interface KnowledgeBaseWebsiteService {
    KnowledgeBaseWebsiteDTO saveKnowledgeBaseWebsite(KnowledgeBaseWebsiteDTO knowledgeBaseWebsiteDTO);
    KnowledgeBaseWebsiteDTO updateKnowledgeBaseWebsite(KnowledgeBaseWebsiteDTO knowledgeBaseWebsiteDTO) throws KnowledgeBaseWebsiteNotFoundException;
    KnowledgeBaseWebsiteDTO findKnowledgeBaseWebsiteById(Long id) throws KnowledgeBaseWebsiteNotFoundException;
    List<KnowledgeBaseWebsiteDTO> findAllKnowledgeBaseWebsites();
    List<KnowledgeBaseWebsiteDTO> searchKnowledgeBaseWebsites(String keyword, int page, int size);
    void deleteKnowledgeBaseWebsiteById(Long id);
    void deleteAllKnowledgeBaseWebsites();
}