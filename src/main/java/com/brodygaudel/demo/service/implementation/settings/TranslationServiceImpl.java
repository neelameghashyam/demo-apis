package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.TranslationDTO;
import com.brodygaudel.demo.entity.settings.Greeting;
import com.brodygaudel.demo.entity.settings.Translation;
import com.brodygaudel.demo.exception.settings.TranslationNotFoundException;
import com.brodygaudel.demo.repository.settings.GreetingRepository;
import com.brodygaudel.demo.repository.settings.TranslationRepository;
import com.brodygaudel.demo.service.settings.TranslationService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TranslationServiceImpl implements TranslationService {

    private final TranslationRepository translationRepository;
    private final GreetingRepository greetingRepository;
    private final Mappers mappers;

    public TranslationServiceImpl(TranslationRepository translationRepository, GreetingRepository greetingRepository, Mappers mappers) {
        this.translationRepository = translationRepository;
        this.greetingRepository = greetingRepository;
        this.mappers = mappers;
    }

    @Override
    public TranslationDTO saveTranslation(TranslationDTO translationDTO) {
        log.info("In saveTranslation()");
        Translation translation = mappers.fromTranslationDTO(translationDTO);
        Greeting greeting = greetingRepository.findById(translationDTO.greetingId())
                .orElseThrow(() -> new IllegalArgumentException("Greeting not found"));
        translation.setGreeting(greeting);
        Translation saved = translationRepository.save(translation);
        log.info("Translation saved with id: {}", saved.getId());
        return mappers.fromTranslation(saved);
    }

    @Override
    public TranslationDTO updateTranslation(TranslationDTO translationDTO) throws TranslationNotFoundException {
        log.info("In updateTranslation()");
        Translation translation = translationRepository.findById(translationDTO.id())
                .orElseThrow(() -> new TranslationNotFoundException("Translation not found"));
        translation.setLanguage(translationDTO.language());
        translation.setGreetingText(translationDTO.greetingText());
        translation.setCreatedAt(translationDTO.createdAt());
        translation.setUpdatedAt(translationDTO.updatedAt());
        Greeting greeting = greetingRepository.findById(translationDTO.greetingId())
                .orElseThrow(() -> new IllegalArgumentException("Greeting not found"));
        translation.setGreeting(greeting);
        Translation updated = translationRepository.save(translation);
        log.info("Translation updated");
        return mappers.fromTranslation(updated);
    }

    @Override
    public TranslationDTO findTranslationById(Long id) throws TranslationNotFoundException {
        log.info("In findTranslationById()");
        Translation translation = translationRepository.findById(id)
                .orElseThrow(() -> new TranslationNotFoundException("Translation not found"));
        log.info("Translation found with id: {}", translation.getId());
        return mappers.fromTranslation(translation);
    }

    @Override
    public List<TranslationDTO> findAllTranslations() {
        log.info("In findAllTranslations()");
        List<Translation> translations = translationRepository.findAll();
        log.info("All translations found");
        return mappers.fromListOfTranslations(translations);
    }

    @Override
    public List<TranslationDTO> searchTranslations(String keyword, int page, int size) {
        log.info("In searchTranslations()");
        Page<Translation> translationPage = translationRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} translations found", translationPage.getContent().size());
        return mappers.fromListOfTranslations(translationPage.getContent());
    }

    @Override
    public void deleteTranslationById(Long id) {
        log.info("In deleteTranslationById()");
        translationRepository.deleteById(id);
        log.info("Translation deleted");
    }

    @Override
    public void deleteAllTranslations() {
        log.info("In deleteAllTranslations()");
        translationRepository.deleteAll();
        log.info("All translations deleted");
    }
}