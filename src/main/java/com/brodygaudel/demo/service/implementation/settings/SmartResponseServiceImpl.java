package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.SmartResponseDTO;
import com.brodygaudel.demo.entity.settings.SmartResponse;
import com.brodygaudel.demo.exception.settings.SmartResponseNotFoundException;
import com.brodygaudel.demo.repository.settings.SmartResponseRepository;
import com.brodygaudel.demo.service.settings.SmartResponseService;
import com.brodygaudel.demo.util.Mappers;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SmartResponseServiceImpl implements SmartResponseService {

    private final SmartResponseRepository smartResponseRepository;
    private final Mappers mappers;

    @Override
    public SmartResponseDTO save(SmartResponseDTO smartResponseDTO) {
        SmartResponse smartResponse = mappers.fromSmartResponseDTO(smartResponseDTO);
        smartResponse = smartResponseRepository.save(smartResponse);
        return mappers.fromSmartResponse(smartResponse);
    }

    @Override
    public SmartResponseDTO update(SmartResponseDTO smartResponseDTO) {
        if (!smartResponseRepository.existsById(smartResponseDTO.id())) {
            throw new SmartResponseNotFoundException("SmartResponse not found with id: " + smartResponseDTO.id());
        }
        SmartResponse smartResponse = mappers.fromSmartResponseDTO(smartResponseDTO);
        smartResponse = smartResponseRepository.save(smartResponse);
        return mappers.fromSmartResponse(smartResponse);
    }

    @Override
    public SmartResponseDTO findById(Long id) {
        SmartResponse smartResponse = smartResponseRepository.findById(id)
                .orElseThrow(() -> new SmartResponseNotFoundException("SmartResponse not found with id: " + id));
        return mappers.fromSmartResponse(smartResponse);
    }

    @Override
    public List<SmartResponseDTO> findAll() {
        return mappers.fromListOfSmartResponses(smartResponseRepository.findAll());
    }

    @Override
    public Page<SmartResponseDTO> search(String keyword, Pageable pageable) {
        return smartResponseRepository.search(keyword, pageable)
                .map(mappers::fromSmartResponse);
    }

    @Override
    public void deleteById(Long id) {
        if (!smartResponseRepository.existsById(id)) {
            throw new SmartResponseNotFoundException("SmartResponse not found with id: " + id);
        }
        smartResponseRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        smartResponseRepository.deleteAll();
    }
}