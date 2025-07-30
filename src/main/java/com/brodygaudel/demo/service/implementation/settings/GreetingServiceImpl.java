package com.brodygaudel.demo.service.implementation.settings;

import com.brodygaudel.demo.dto.settings.GreetingDTO;
import com.brodygaudel.demo.entity.User;
import com.brodygaudel.demo.entity.settings.Greeting;
import com.brodygaudel.demo.exception.settings.GreetingNotFoundException;
import com.brodygaudel.demo.repository.UserRepository;
import com.brodygaudel.demo.repository.settings.GreetingRepository;
import com.brodygaudel.demo.service.settings.GreetingService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GreetingServiceImpl implements GreetingService {

    private final GreetingRepository greetingRepository;
    private final UserRepository userRepository;
    private final Mappers mappers;

    public GreetingServiceImpl(GreetingRepository greetingRepository, UserRepository userRepository, Mappers mappers) {
        this.greetingRepository = greetingRepository;
        this.userRepository = userRepository;
        this.mappers = mappers;
    }

    @Override
    public GreetingDTO saveGreeting(GreetingDTO greetingDTO) {
        log.info("In saveGreeting()");
        Greeting greeting = mappers.fromGreetingDTO(greetingDTO);
        if (greetingDTO.userId() != null) {
            User user = userRepository.findById(greetingDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            greeting.setUser(user);
        }
        Greeting saved = greetingRepository.save(greeting);
        log.info("Greeting saved with id: {}", saved.getId());
        return mappers.fromGreeting(saved);
    }

    @Override
    public GreetingDTO updateGreeting(GreetingDTO greetingDTO) throws GreetingNotFoundException {
        log.info("In updateGreeting()");
        Greeting greeting = greetingRepository.findById(greetingDTO.id())
                .orElseThrow(() -> new GreetingNotFoundException("Greeting not found"));
        greeting.setTitle(greetingDTO.title());
        greeting.setGreeting(greetingDTO.greeting());
        greeting.setType(Greeting.Type.valueOf(greetingDTO.type()));
        greeting.setVisible(greetingDTO.visible());
        greeting.setCreatedAt(greetingDTO.createdAt());
        greeting.setUpdatedAt(greetingDTO.updatedAt());
        if (greetingDTO.userId() != null) {
            User user = userRepository.findById(greetingDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            greeting.setUser(user);
        } else {
            greeting.setUser(null);
        }
        Greeting updated = greetingRepository.save(greeting);
        log.info("Greeting updated");
        return mappers.fromGreeting(updated);
    }

    @Override
    public GreetingDTO findGreetingById(Long id) throws GreetingNotFoundException {
        log.info("In findGreetingById()");
        Greeting greeting = greetingRepository.findById(id)
                .orElseThrow(() -> new GreetingNotFoundException("Greeting not found"));
        log.info("Greeting found with id: {}", greeting.getId());
        return mappers.fromGreeting(greeting);
    }

    @Override
    public List<GreetingDTO> findAllGreetings() {
        log.info("In findAllGreetings()");
        List<Greeting> greetings = greetingRepository.findAll();
        log.info("All greetings found");
        return mappers.fromListOfGreetings(greetings);
    }

    @Override
    public List<GreetingDTO> searchGreetings(String keyword, int page, int size) {
        log.info("In searchGreetings()");
        Page<Greeting> greetingPage = greetingRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} greetings found", greetingPage.getContent().size());
        return mappers.fromListOfGreetings(greetingPage.getContent());
    }

    @Override
    public void deleteGreetingById(Long id) {
        log.info("In deleteGreetingById()");
        greetingRepository.deleteById(id);
        log.info("Greeting deleted");
    }

    @Override
    public void deleteAllGreetings() {
        log.info("In deleteAllGreetings()");
        greetingRepository.deleteAll();
        log.info("All greetings deleted");
    }
}