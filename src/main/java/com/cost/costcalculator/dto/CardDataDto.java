package com.cost.costcalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDataDto {

    private String scheme;
    private String type;
    private String category;
    private Integer length;
    private Integer checkluhn;
    private Integer cvvlen;
}
