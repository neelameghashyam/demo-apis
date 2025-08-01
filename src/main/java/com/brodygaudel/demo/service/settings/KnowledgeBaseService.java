package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.KnowledgeBaseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KnowledgeBaseService {
    KnowledgeBaseDTO save(KnowledgeBaseDTO knowledgeBaseDTO);
    KnowledgeBaseDTO update(KnowledgeBaseDTO knowledgeBaseDTO);
    KnowledgeBaseDTO findById(Long id);
    List<KnowledgeBaseDTO> findAll();
    Page<KnowledgeBaseDTO> search(String keyword, Pageable pageable);
    void deleteById(Long id);
    void deleteAll();
}