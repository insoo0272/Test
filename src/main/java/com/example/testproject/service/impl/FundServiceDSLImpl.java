package com.example.testproject.service.impl;

import com.example.testproject.domain.repository.FundRepository;
import com.example.testproject.domain.repository.FundRepositoryDSL;
import com.example.testproject.service.dto.AnnualTotalAmountResponseDto;
import com.example.testproject.service.dto.AverageAmountByBankResponseDto;
import com.example.testproject.service.dto.YearofMaxFundResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FundServiceDSLImpl {
    private final FundRepositoryDSL fundRepository;

    public FundServiceDSLImpl(FundRepositoryDSL fundRepository) {
        this.fundRepository = fundRepository;
    }

    public List<AnnualTotalAmountResponseDto> out(){
        List<AnnualTotalAmountResponseDto> annualtotalamounts = new ArrayList<>();
        annualtotalamounts = fundRepository.out();
        return annualtotalamounts;
    }

    public YearofMaxFundResponseDto out1(){
        return fundRepository.out1();
    }

    public List<AverageAmountByBankResponseDto> out2(String institutename){
        return fundRepository.out2(institutename);
    }
}
