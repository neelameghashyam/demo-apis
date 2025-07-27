package com.brodygaudel.demo.service.implementation;

import com.brodygaudel.demo.dto.WebsiteDTO;
import com.brodygaudel.demo.entity.Website;
import com.brodygaudel.demo.exception.WebsiteNotFoundException;
import com.brodygaudel.demo.repository.WebsiteRepository;
import com.brodygaudel.demo.service.WebsiteService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WebsiteServiceImpl implements WebsiteService {

    private final WebsiteRepository websiteRepository;
    private final Mappers mappers;

    public WebsiteServiceImpl(WebsiteRepository websiteRepository, Mappers mappers) {
        this.websiteRepository = websiteRepository;
        this.mappers = mappers;
    }

    @Override
    public WebsiteDTO saveWebsite(WebsiteDTO websiteDTO) {
        log.info("In saveWebsite()");
        Website website = mappers.fromWebsiteDTO(websiteDTO);
        Website websiteSaved = websiteRepository.save(website);
        log.info("Website saved with id: {}", websiteSaved.getId());
        return mappers.fromWebsite(websiteSaved);
    }

    @Override
    public WebsiteDTO updateWebsite(WebsiteDTO websiteDTO) throws WebsiteNotFoundException {
        log.info("In updateWebsite()");
        Website website = websiteRepository.findById(websiteDTO.id())
                .orElseThrow(() -> new WebsiteNotFoundException("Website not found"));
        website.setProtocol(Website.Protocol.valueOf(websiteDTO.protocol()));
        website.setDomain(websiteDTO.domain());
        website.setCompanyId(websiteDTO.companyId());
        website.setBusinessCategory(websiteDTO.businessCategory());
        website.setDateAdded(websiteDTO.dateAdded());
        website.setIsActive(websiteDTO.isActive());
        website.setIsVerified(websiteDTO.isVerified());
        Website websiteUpdated = websiteRepository.save(website);
        log.info("Website updated");
        return mappers.fromWebsite(websiteUpdated);
    }

    @Override
    public WebsiteDTO findWebsiteById(Long id) throws WebsiteNotFoundException {
        log.info("In findWebsiteById()");
        Website website = websiteRepository.findById(id)
                .orElseThrow(() -> new WebsiteNotFoundException("Website not found"));
        log.info("Website found with id: {}", website.getId());
        return mappers.fromWebsite(website);
    }

    @Override
    public List<WebsiteDTO> findAllWebsites() {
        log.info("In findAllWebsites()");
        List<Website> websites = websiteRepository.findAll();
        log.info("All websites found");
        return mappers.fromListOfWebsites(websites);
    }

    @Override
    public List<WebsiteDTO> searchWebsites(String keyword, int page, int size) {
        log.info("In searchWebsites()");
        Page<Website> websitePage = websiteRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} websites found", websitePage.getContent().size());
        return mappers.fromListOfWebsites(websitePage.getContent());
    }

    @Override
    public void deleteWebsiteById(Long id) {
        log.info("In deleteWebsiteById()");
        websiteRepository.deleteById(id);
        log.info("Website deleted");
    }

    @Override
    public void deleteAllWebsites() {
        log.info("In deleteAllWebsites()");
        websiteRepository.deleteAll();
        log.info("All websites deleted");
    }
}