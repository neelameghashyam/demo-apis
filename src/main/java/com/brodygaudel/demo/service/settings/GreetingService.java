package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.GreetingDTO;
import com.brodygaudel.demo.exception.settings.GreetingNotFoundException;

import java.util.List;

public interface GreetingService {
    GreetingDTO saveGreeting(GreetingDTO greetingDTO);
    GreetingDTO updateGreeting(GreetingDTO greetingDTO) throws GreetingNotFoundException;
    GreetingDTO findGreetingById(Long id) throws GreetingNotFoundException;
    List<GreetingDTO> findAllGreetings();
    List<GreetingDTO> searchGreetings(String keyword, int page, int size);
    void deleteGreetingById(Long id);
    void deleteAllGreetings();
}