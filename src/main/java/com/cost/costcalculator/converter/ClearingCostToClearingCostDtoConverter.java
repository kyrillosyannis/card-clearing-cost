package com.cost.costcalculator.converter;

import com.cost.costcalculator.domain.ClearingCost;
import com.cost.costcalculator.dto.ClearingCostDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClearingCostToClearingCostDtoConverter implements Converter<ClearingCost, ClearingCostDto> {

    @Override
    public ClearingCostDto convert(ClearingCost clearingCost) {
        return ClearingCostDto
                .builder()
                .id(clearingCost.getId())
                .issuingCountry(clearingCost.getIssuingCountry())
                .clearingCost(clearingCost.getClearingCost())
                .build();
    }
}
