package com.example.testproject.controller;

import com.example.testproject.domain.repository.FundRepository;
import com.example.testproject.service.FundService;
import com.example.testproject.service.dto.AnnualTotalAmountResponseDto;
import com.example.testproject.service.dto.AverageAmountByBankResponseDto;
import com.example.testproject.service.dto.YearofMaxFundResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fund")
public class FundController {

    private final FundService fundService;

    public FundController(FundService fundService) {
        this.fundService = fundService;
    }

    @GetMapping
    @RequestMapping("/year")
    public ResponseEntity<List<AnnualTotalAmountResponseDto>> AnnualTotalAmount(){
        return ResponseEntity.ok(fundService.AnnualTotalAmount());
    }

    @GetMapping
    @RequestMapping("/maximum")
    public ResponseEntity<YearofMaxFundResponseDto> YearInstituteofMaxFund(){
        return ResponseEntity.ok(fundService.YearInstituteofMaxFund());
    }

    @GetMapping
    @RequestMapping("/avgMax-avgMin")
    public ResponseEntity<List<AverageAmountByBankResponseDto>> YearAverageAmountMaxMin(@RequestParam(value = "instituteName") String instituteName){
        return ResponseEntity.ok(fundService.AverageAmountByBank(instituteName));
    }
}
