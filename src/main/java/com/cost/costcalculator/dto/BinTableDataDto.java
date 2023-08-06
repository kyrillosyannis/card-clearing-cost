package com.cost.costcalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BinTableDataDto {

    private CardDataDto card;
    private CountryDataDto country;
    private BankDataDto bank;
}
