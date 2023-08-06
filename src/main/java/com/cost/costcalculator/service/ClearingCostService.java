package com.cost.costcalculator.service;

import com.cost.costcalculator.domain.ClearingCost;
import com.cost.costcalculator.dto.BinTableCardDetailsResponseDto;
import com.cost.costcalculator.dto.ClearingCostDto;
import com.cost.costcalculator.dto.ClearingCostResponseDto;
import com.cost.costcalculator.repository.ClearingCostRepository;
import com.cost.costcalculator.web_client.BinTableClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class ClearingCostService {

    private final ClearingCostRepository clearingCostRepository;
    private final ConversionService conversionService;
    private final BinTableClient binTableClient;

    public ClearingCostService(ClearingCostRepository clearingCostRepository, ConversionService conversionService,
                               BinTableClient binTableClient) {
        this.clearingCostRepository = clearingCostRepository;
        this.conversionService = conversionService;
        this.binTableClient = binTableClient;
    }

    public ClearingCostDto save(ClearingCostDto clearingCostDto) {
        ClearingCost clearingCost = conversionService.convert(clearingCostDto, ClearingCost.class);
        clearingCostRepository.save(clearingCost);
        clearingCostDto = conversionService.convert(clearingCost, ClearingCostDto.class);
        return clearingCostDto;
    }

    public Page<ClearingCostDto> findAll(Pageable pageable) {
        Page<ClearingCost> clearingCostPage =  clearingCostRepository.findAll(pageable);
        List<ClearingCostDto> clearingCostDtoList =  clearingCostPage.stream()
                .map(clearingCost -> conversionService.convert(clearingCost, ClearingCostDto.class))
                .toList();
        return new PageImpl<>(clearingCostDtoList, pageable, clearingCostPage.getTotalElements());
    }

    public ClearingCostDto findById(Long id) {
        ClearingCost clearingCost = clearingCostRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("no element for id: {}", id);
                    return new NoSuchElementException("Element does not exist");
                });
        ClearingCostDto clearingCostDto = conversionService.convert(clearingCost, ClearingCostDto.class);
        return clearingCostDto;
    }

    public Mono<ClearingCostResponseDto> calculateClearingCost(String cardNumber) {
        Mono<BinTableCardDetailsResponseDto> response = binTableClient.getDetailsFromCardNumber(cardNumber);
        Mono<ClearingCostResponseDto> costResponse =  response.map(resp -> {
            ClearingCost clearingCost = clearingCostRepository.findByIssuingCountry(resp.getData().getCountry().getCode())
                    .orElseThrow(() -> {
                log.error("Country clearing cost not found");
                return new NoSuchElementException("Country clearing cost not found");
            });
            ClearingCostResponseDto cost = ClearingCostResponseDto
                    .builder()
                    .clearingCost(clearingCost.getClearingCost())
                    .issuingCountry(clearingCost.getIssuingCountry())
                    .build();
            return cost;
        });
        return costResponse;
    }

    public void deleteById(Long id) {
        clearingCostRepository.deleteById(id);
    }

    private String encrypt(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA3-256");
        byte[] bytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
        BigInteger number = new BigInteger(1, bytes);
        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));
        // Pad with leading zeros
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
}
