package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.SmartResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SmartResponseService {
    SmartResponseDTO save(SmartResponseDTO smartResponseDTO);
    SmartResponseDTO update(SmartResponseDTO smartResponseDTO);
    SmartResponseDTO findById(Long id);
    List<SmartResponseDTO> findAll();
    Page<SmartResponseDTO> search(String keyword, Pageable pageable);
    void deleteById(Long id);
    void deleteAll();
}