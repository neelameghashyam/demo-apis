package com.brodygaudel.demo.controller;

import com.brodygaudel.demo.dto.PricePlanDTO;
import com.brodygaudel.demo.exception.PricePlanNotFoundException;
import com.brodygaudel.demo.service.PricePlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/price_plans")
public class PricePlanRestController {

    private final PricePlanService pricePlanService;

    public PricePlanRestController(PricePlanService pricePlanService) {
        this.pricePlanService = pricePlanService;
    }

    @PostMapping("/save")
    public PricePlanDTO savePricePlan(@RequestBody PricePlanDTO pricePlanDTO) {
        return pricePlanService.savePricePlan(pricePlanDTO);
    }

    @PutMapping("/update")
    public PricePlanDTO updatePricePlan(@RequestBody PricePlanDTO pricePlanDTO) throws PricePlanNotFoundException {
        return pricePlanService.updatePricePlan(pricePlanDTO);
    }

    @GetMapping("/get/{id}")
    public PricePlanDTO findPricePlanById(@PathVariable Long id) throws PricePlanNotFoundException {
        return pricePlanService.findPricePlanById(id);
    }

    @GetMapping("/list")
    public List<PricePlanDTO> findAllPricePlans() {
        return pricePlanService.findAllPricePlans();
    }

    @GetMapping("/search")
    public List<PricePlanDTO> searchPricePlans(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return pricePlanService.searchPricePlans(keyword, page, size);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePricePlanById(@PathVariable Long id) {
        pricePlanService.deletePricePlanById(id);
    }

    @DeleteMapping("/clear")
    public void deleteAllPricePlans() {
        pricePlanService.deleteAllPricePlans();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}