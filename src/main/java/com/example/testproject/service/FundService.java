package com.example.testproject.service;

import com.example.testproject.service.dto.AnnualTotalAmountResponseDto;
import com.example.testproject.service.dto.AverageAmountByBankResponseDto;
import com.example.testproject.service.dto.YearofMaxFundResponseDto;

import java.util.List;

public interface FundService {
    List<AnnualTotalAmountResponseDto> AnnualTotalAmount();
    YearofMaxFundResponseDto YearInstituteofMaxFund();
    List<AverageAmountByBankResponseDto> AverageAmountByBank(String instituteName);
}
