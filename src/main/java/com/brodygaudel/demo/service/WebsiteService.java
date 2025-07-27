package com.brodygaudel.demo.service;

import com.brodygaudel.demo.dto.WebsiteDTO;
import com.brodygaudel.demo.exception.WebsiteNotFoundException;

import java.util.List;

public interface WebsiteService {
    WebsiteDTO saveWebsite(WebsiteDTO websiteDTO);
    WebsiteDTO updateWebsite(WebsiteDTO websiteDTO) throws WebsiteNotFoundException;
    WebsiteDTO findWebsiteById(Long id) throws WebsiteNotFoundException;
    List<WebsiteDTO> findAllWebsites();
    List<WebsiteDTO> searchWebsites(String keyword, int page, int size);
    void deleteWebsiteById(Long id);
    void deleteAllWebsites();
}