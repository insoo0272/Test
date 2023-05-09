package com.example.testproject.service.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YearofMaxFundResponseDto {
    private int year;
    private String instituteName;

}
