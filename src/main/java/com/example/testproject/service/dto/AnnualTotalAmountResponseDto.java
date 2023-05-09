package com.example.testproject.service.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnualTotalAmountResponseDto {

    private int year;
    private int totalAmount;
}
