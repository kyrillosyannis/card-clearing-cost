package com.cost.costcalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BinTableCardDetailsResponseDto {

    private Short result;
    private String message;
    private BinTableDataDto data;
}
