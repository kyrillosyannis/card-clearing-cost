package com.cost.costcalculator.converter;

import com.cost.costcalculator.domain.ClearingCost;
import com.cost.costcalculator.dto.ClearingCostDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClearingCostDtoToClearingCostConverter implements Converter<ClearingCostDto, ClearingCost> {

    @Override
    public ClearingCost convert(ClearingCostDto clearingCostDto) {
        return ClearingCost
                .builder()
                .id(clearingCostDto.getId())
                .issuingCountry(clearingCostDto.getIssuingCountry())
                .clearingCost(clearingCostDto.getClearingCost())
                .build();
    }
}
