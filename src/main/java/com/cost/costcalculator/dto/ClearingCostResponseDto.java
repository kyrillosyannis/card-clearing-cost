package com.cost.costcalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClearingCostResponseDto {

    private String issuingCountry;
    private BigDecimal clearingCost;
}
