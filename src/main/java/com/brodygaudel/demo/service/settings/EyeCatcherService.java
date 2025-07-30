package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.EyeCatcherDTO;
import com.brodygaudel.demo.exception.settings.EyeCatcherNotFoundException;

import java.util.List;

public interface EyeCatcherService {
    EyeCatcherDTO saveEyeCatcher(EyeCatcherDTO eyeCatcherDTO);
    EyeCatcherDTO updateEyeCatcher(EyeCatcherDTO eyeCatcherDTO) throws EyeCatcherNotFoundException;
    EyeCatcherDTO findEyeCatcherById(Long id) throws EyeCatcherNotFoundException;
    List<EyeCatcherDTO> findAllEyeCatchers();
    List<EyeCatcherDTO> searchEyeCatchers(String keyword, int page, int size);
    void deleteEyeCatcherById(Long id);
    void deleteAllEyeCatchers();
}