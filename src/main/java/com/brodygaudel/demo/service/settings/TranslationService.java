package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.TranslationDTO;
import com.brodygaudel.demo.exception.settings.TranslationNotFoundException;

import java.util.List;

public interface TranslationService {
    TranslationDTO saveTranslation(TranslationDTO translationDTO);
    TranslationDTO updateTranslation(TranslationDTO translationDTO) throws TranslationNotFoundException;
    TranslationDTO findTranslationById(Long id) throws TranslationNotFoundException;
    List<TranslationDTO> findAllTranslations();
    List<TranslationDTO> searchTranslations(String keyword, int page, int size);
    void deleteTranslationById(Long id);
    void deleteAllTranslations();
}