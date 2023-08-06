package com.cost.costcalculator.controller;

import com.cost.costcalculator.dto.CalculateCostRequestDto;
import com.cost.costcalculator.dto.ClearingCostDto;
import com.cost.costcalculator.dto.ClearingCostResponseDto;
import com.cost.costcalculator.service.ClearingCostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clearing-costs")
public class ClearingCostController {

    private final ClearingCostService clearingCostService;

    public ClearingCostController(ClearingCostService clearingCostService) {
        this.clearingCostService = clearingCostService;
    }

    @PostMapping("/calculate")
    public Mono<ClearingCostResponseDto> calculateClearingCost(@RequestBody CalculateCostRequestDto calculateCostRequestDto) {
        return clearingCostService.calculateClearingCost(calculateCostRequestDto.getCardNumber());
    }

    @GetMapping
    public Page<ClearingCostDto> findAll() {
        return clearingCostService.findAll(Pageable.unpaged());
    }

    @PostMapping
    public ClearingCostDto create(@RequestBody ClearingCostDto clearingCostDto) {
        return clearingCostService.save(clearingCostDto);
    }

    @PutMapping("/{id}")
    public ClearingCostDto update(@RequestBody ClearingCostDto clearingCostDto) {
        return clearingCostService.save(clearingCostDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        clearingCostService.deleteById(id);
    }
}
