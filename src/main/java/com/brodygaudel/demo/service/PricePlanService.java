package com.brodygaudel.demo.service;

import com.brodygaudel.demo.dto.PricePlanDTO;
import com.brodygaudel.demo.exception.PricePlanNotFoundException;

import java.util.List;

public interface PricePlanService {
    PricePlanDTO savePricePlan(PricePlanDTO pricePlanDTO);
    PricePlanDTO updatePricePlan(PricePlanDTO pricePlanDTO) throws PricePlanNotFoundException;
    PricePlanDTO findPricePlanById(Long id) throws PricePlanNotFoundException;
    List<PricePlanDTO> findAllPricePlans();
    List<PricePlanDTO> searchPricePlans(String keyword, int page, int size);
    void deletePricePlanById(Long id);
    void deleteAllPricePlans();
}