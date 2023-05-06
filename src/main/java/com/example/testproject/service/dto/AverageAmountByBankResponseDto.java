package com.example.testproject.service.dto;

import lombok.Data;

@Data
public class AverageAmountByBankResponseDto {
    private int year;
    private int avgAmount;

    public AverageAmountByBankResponseDto(int year, int avgAmount) {
        this.year = year;
        this.avgAmount = avgAmount;
    }
}
