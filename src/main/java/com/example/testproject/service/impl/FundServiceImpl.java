package com.example.testproject.service.impl;

import com.example.testproject.domain.repository.FundRepository;
import com.example.testproject.service.FundService;
import com.example.testproject.service.dto.AnnualTotalAmountResponseDto;
import com.example.testproject.service.dto.AverageAmountByBankResponseDto;
import com.example.testproject.service.dto.YearofMaxFundResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FundServiceImpl implements FundService {
    private final FundRepository fundRepository;

    public FundServiceImpl(FundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }

    public List<AnnualTotalAmountResponseDto> AnnualTotalAmount(){
        List<AnnualTotalAmountResponseDto> annualtotalamounts = new ArrayList<>();
        List<Object[]> allAndAnnualTotalFunds = fundRepository.findAllAnualTotalFund();
        for (Object[] allAndAnnualTotalFund : allAndAnnualTotalFunds){
            AnnualTotalAmountResponseDto annualTotalAmountResponseDto = new AnnualTotalAmountResponseDto();
            annualTotalAmountResponseDto.setYear(Integer.parseInt(allAndAnnualTotalFund[0].toString()));
            annualTotalAmountResponseDto.setTotalAmount(Integer.parseInt((allAndAnnualTotalFund[1].toString())));
            annualtotalamounts.add(annualTotalAmountResponseDto);
        }
        return annualtotalamounts;
    }

    public YearofMaxFundResponseDto YearInstituteofMaxFund(){
        List<Object[]> YearInstituteofMaxFund = fundRepository.findYearInstituteofMaxFund();
        log.error(YearInstituteofMaxFund.get(0)[0].toString() + YearInstituteofMaxFund.get(0)[1].toString());
        return new YearofMaxFundResponseDto(
                Integer.parseInt(YearInstituteofMaxFund.get(0)[0].toString()),
                YearInstituteofMaxFund.get(0)[1].toString()
        );
    }

    @Transactional(readOnly = true)
    public List<AverageAmountByBankResponseDto> AverageAmountByBank(String instituteName){
        List<AverageAmountByBankResponseDto> AverageAmountByBanks = new ArrayList<>();
        List<Object[]> MaxAverageAmountByBank = fundRepository.findMaxAverageAmountByBank(instituteName);
        List<Object[]> MinAverageAmountByBank = fundRepository.findMinAverageAmountByBank(instituteName);
        log.error(MaxAverageAmountByBank.toString());
        log.error(MinAverageAmountByBank.toString());
        AverageAmountByBanks.add(new AverageAmountByBankResponseDto(
                Integer.parseInt(MaxAverageAmountByBank.get(0)[0].toString()),
                Integer.parseInt(MaxAverageAmountByBank.get(0)[1].toString())));
        AverageAmountByBanks.add(new AverageAmountByBankResponseDto(
                Integer.parseInt(MinAverageAmountByBank.get(0)[0].toString()),
                Integer.parseInt(MinAverageAmountByBank.get(0)[1].toString())));
        return AverageAmountByBanks;
    }


}
