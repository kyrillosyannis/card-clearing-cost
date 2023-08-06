package com.cost.costcalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClearingCostDto {

    private Long id;
    private String issuingCountry;
    private BigDecimal clearingCost;
}
