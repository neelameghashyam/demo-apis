package com.brodygaudel.demo.service.settings;

import com.brodygaudel.demo.dto.settings.InactivityTimeoutDTO;
import com.brodygaudel.demo.exception.settings.InactivityTimeoutNotFoundException;

import java.util.List;

public interface InactivityTimeoutService {
    InactivityTimeoutDTO saveInactivityTimeout(InactivityTimeoutDTO inactivityTimeoutDTO);
    InactivityTimeoutDTO updateInactivityTimeout(InactivityTimeoutDTO inactivityTimeoutDTO) throws InactivityTimeoutNotFoundException;
    InactivityTimeoutDTO findInactivityTimeoutById(Long id) throws InactivityTimeoutNotFoundException;
    List<InactivityTimeoutDTO> findAllInactivityTimeouts();
    List<InactivityTimeoutDTO> searchInactivityTimeouts(String keyword, int page, int size);
    void deleteInactivityTimeoutById(Long id);
    void deleteAllInactivityTimeouts();
}