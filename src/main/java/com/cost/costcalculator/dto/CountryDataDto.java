package com.cost.costcalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryDataDto {

    private String name;
    private String code;
    private String flag;
    private String currency;
    private String currency_code;
}
