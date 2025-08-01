package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.KnowledgeBaseDTO;
import com.brodygaudel.demo.entity.settings.KnowledgeBase;
import com.brodygaudel.demo.exception.settings.KnowledgeBaseNotFoundException;
import com.brodygaudel.demo.repository.settings.KnowledgeBaseRepository;
import com.brodygaudel.demo.service.settings.KnowledgeBaseService;
import com.brodygaudel.demo.util.Mappers;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KnowledgeBaseServiceImpl implements KnowledgeBaseService {

    private final KnowledgeBaseRepository knowledgeBaseRepository;
    private final Mappers mappers;

    @Override
    public KnowledgeBaseDTO save(KnowledgeBaseDTO knowledgeBaseDTO) {
        KnowledgeBase knowledgeBase = mappers.fromKnowledgeBaseDTO(knowledgeBaseDTO);
        knowledgeBase = knowledgeBaseRepository.save(knowledgeBase);
        return mappers.fromKnowledgeBase(knowledgeBase);
    }

    @Override
    public KnowledgeBaseDTO update(KnowledgeBaseDTO knowledgeBaseDTO) {
        if (!knowledgeBaseRepository.existsById(knowledgeBaseDTO.id())) {
            throw new KnowledgeBaseNotFoundException("KnowledgeBase not found with id: " + knowledgeBaseDTO.id());
        }
        KnowledgeBase knowledgeBase = mappers.fromKnowledgeBaseDTO(knowledgeBaseDTO);
        knowledgeBase = knowledgeBaseRepository.save(knowledgeBase);
        return mappers.fromKnowledgeBase(knowledgeBase);
    }

    @Override
    public KnowledgeBaseDTO findById(Long id) {
        KnowledgeBase knowledgeBase = knowledgeBaseRepository.findById(id)
                .orElseThrow(() -> new KnowledgeBaseNotFoundException("KnowledgeBase not found with id: " + id));
        return mappers.fromKnowledgeBase(knowledgeBase);
    }

    @Override
    public List<KnowledgeBaseDTO> findAll() {
        return mappers.fromListOfKnowledgeBases(knowledgeBaseRepository.findAll());
    }

    @Override
    public Page<KnowledgeBaseDTO> search(String keyword, Pageable pageable) {
        return knowledgeBaseRepository.search(keyword, pageable)
                .map(mappers::fromKnowledgeBase);
    }

    @Override
    public void deleteById(Long id) {
        if (!knowledgeBaseRepository.existsById(id)) {
            throw new KnowledgeBaseNotFoundException("KnowledgeBase not found with id: " + id);
        }
        knowledgeBaseRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        knowledgeBaseRepository.deleteAll();
    }
}