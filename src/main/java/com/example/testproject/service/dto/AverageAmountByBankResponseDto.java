package com.example.testproject.service.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AverageAmountByBankResponseDto {
    private int year;
    private int avgAmount;

}
