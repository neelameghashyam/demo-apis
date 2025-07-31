package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.KnowledgeBaseDTO;
import com.brodygaudel.demo.exception.settings.KnowledgeBaseNotFoundException;

import java.util.List;

public interface KnowledgeBaseService {
    KnowledgeBaseDTO saveKnowledgeBase(KnowledgeBaseDTO knowledgeBaseDTO);
    KnowledgeBaseDTO updateKnowledgeBase(KnowledgeBaseDTO knowledgeBaseDTO) throws KnowledgeBaseNotFoundException;
    KnowledgeBaseDTO findKnowledgeBaseById(Long id) throws KnowledgeBaseNotFoundException;
    List<KnowledgeBaseDTO> findAllKnowledgeBases();
    List<KnowledgeBaseDTO> searchKnowledgeBases(String keyword, int page, int size);
    void deleteKnowledgeBaseById(Long id);
    void deleteAllKnowledgeBases();
}