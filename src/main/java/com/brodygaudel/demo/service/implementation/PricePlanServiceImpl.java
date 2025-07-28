package com.brodygaudel.demo.service.implementation;

import com.brodygaudel.demo.dto.PricePlanDTO;
import com.brodygaudel.demo.entity.PricePlan;
import com.brodygaudel.demo.exception.PricePlanNotFoundException;
import com.brodygaudel.demo.repository.PricePlanRepository;
import com.brodygaudel.demo.service.PricePlanService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PricePlanServiceImpl implements PricePlanService {

    private final PricePlanRepository pricePlanRepository;
    private final Mappers mappers;

    public PricePlanServiceImpl(PricePlanRepository pricePlanRepository, Mappers mappers) {
        this.pricePlanRepository = pricePlanRepository;
        this.mappers = mappers;
    }

    @Override
    public PricePlanDTO savePricePlan(PricePlanDTO pricePlanDTO) {
        log.info("In savePricePlan()");
        PricePlan pricePlan = mappers.fromPricePlanDTO(pricePlanDTO);
        PricePlan pricePlanSaved = pricePlanRepository.save(pricePlan);
        log.info("PricePlan saved with id: {}", pricePlanSaved.getId());
        return mappers.fromPricePlan(pricePlanSaved);
    }

    @Override
    public PricePlanDTO updatePricePlan(PricePlanDTO pricePlanDTO) throws PricePlanNotFoundException {
        log.info("In updatePricePlan()");
        PricePlan pricePlan = pricePlanRepository.findById(pricePlanDTO.id())
                .orElseThrow(() -> new PricePlanNotFoundException("PricePlan not found"));
        pricePlan.setPlanName(pricePlanDTO.planName());
        pricePlan.setPlanDescription(pricePlanDTO.planDescription());
        pricePlan.setStatus(pricePlanDTO.status());
        pricePlan.setDefaultPlan(pricePlanDTO.defaultPlan());
        pricePlan.setFreePlan(pricePlanDTO.freePlan());
        pricePlan.setType(pricePlanDTO.type());
        pricePlan.setDateAdded(pricePlanDTO.dateAdded());
        pricePlan.setPriceMonthly(pricePlanDTO.priceMonthly());
        pricePlan.setPriceAnnually(pricePlanDTO.priceAnnually());
        pricePlan.setUnlimitedChat(pricePlanDTO.unlimitedChat());
        pricePlan.setNumberOfChats(pricePlanDTO.numberOfChats());
        pricePlan.setExtraChatAmount(pricePlanDTO.extraChatAmount());
        pricePlan.setUnlimitedChatHistoryStorage(pricePlanDTO.unlimitedChatHistoryStorage());
        pricePlan.setChatHistoryDurationDays(pricePlanDTO.chatHistoryDurationDays());
        pricePlan.setCostPerExtraDayOfStorage(pricePlanDTO.costPerExtraDayOfStorage());
        pricePlan.setUnlimitedUsers(pricePlanDTO.unlimitedUsers());
        pricePlan.setNumberOfUsers(pricePlanDTO.numberOfUsers());
        pricePlan.setExtraUserCost(pricePlanDTO.extraUserCost());
        pricePlan.setNumberOfWebsites(pricePlanDTO.numberOfWebsites());
        pricePlan.setExtraWebsiteCost(pricePlanDTO.extraWebsiteCost());
        pricePlan.setChatTakeover(pricePlanDTO.chatTakeover());
        pricePlan.setChatTagging(pricePlanDTO.chatTagging());
        pricePlan.setChatTranscript(pricePlanDTO.chatTranscript());
        pricePlan.setChatbotOpenaiIncluded(pricePlanDTO.chatbotOpenaiIncluded());
        pricePlan.setManagedAccount(pricePlanDTO.managedAccount());
        pricePlan.setCustomPlan(pricePlanDTO.customPlan());
        pricePlan.setCreatedAt(pricePlanDTO.createdAt());
        pricePlan.setUpdatedAt(pricePlanDTO.updatedAt());
        PricePlan pricePlanUpdated = pricePlanRepository.save(pricePlan);
        log.info("PricePlan updated");
        return mappers.fromPricePlan(pricePlanUpdated);
    }

    @Override
    public PricePlanDTO findPricePlanById(Long id) throws PricePlanNotFoundException {
        log.info("In findPricePlanById()");
        PricePlan pricePlan = pricePlanRepository.findById(id)
                .orElseThrow(() -> new PricePlanNotFoundException("PricePlan not found"));
        log.info("PricePlan found with id: {}", pricePlan.getId());
        return mappers.fromPricePlan(pricePlan);
    }

    @Override
    public List<PricePlanDTO> findAllPricePlans() {
        log.info("In findAllPricePlans()");
        List<PricePlan> pricePlans = pricePlanRepository.findAll();
        log.info("All price plans found");
        return mappers.fromListOfPricePlans(pricePlans);
    }

    @Override
    public List<PricePlanDTO> searchPricePlans(String keyword, int page, int size) {
        log.info("In searchPricePlans()");
        Page<PricePlan> pricePlanPage = pricePlanRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} price plans found", pricePlanPage.getContent().size());
        return mappers.fromListOfPricePlans(pricePlanPage.getContent());
    }

    @Override
    public void deletePricePlanById(Long id) {
        log.info("In deletePricePlanById()");
        pricePlanRepository.deleteById(id);
        log.info("PricePlan deleted");
    }

    @Override
    public void deleteAllPricePlans() {
        log.info("In deleteAllPricePlans()");
        pricePlanRepository.deleteAll();
        log.info("All price plans deleted");
    }
}