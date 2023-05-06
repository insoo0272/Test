package com.example.testproject.service.dto;

import lombok.Data;

@Data
public class YearofMaxFundResponseDto {
    private int year;
    private String instituteName;

    public YearofMaxFundResponseDto(int year, String instituteName) {
        this.year = year;
        this.instituteName = instituteName;
    }
}
