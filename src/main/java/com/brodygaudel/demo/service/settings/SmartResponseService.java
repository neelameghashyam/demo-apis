package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.SmartResponseDTO;
import com.brodygaudel.demo.exception.settings.SmartResponseNotFoundException;

import java.util.List;

public interface SmartResponseService {
    SmartResponseDTO saveSmartResponse(SmartResponseDTO smartResponseDTO);
    SmartResponseDTO updateSmartResponse(SmartResponseDTO smartResponseDTO) throws SmartResponseNotFoundException;
    SmartResponseDTO findSmartResponseById(Long id) throws SmartResponseNotFoundException;
    List<SmartResponseDTO> findAllSmartResponses();
    List<SmartResponseDTO> searchSmartResponses(String keyword, int page, int size);
    void deleteSmartResponseById(Long id);
    void deleteAllSmartResponses();
}