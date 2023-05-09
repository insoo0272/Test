package com.example.testproject.controller;

import com.example.testproject.service.FundService;
import com.example.testproject.service.dto.AnnualTotalAmountResponseDto;
import com.example.testproject.service.dto.AverageAmountByBankResponseDto;
import com.example.testproject.service.dto.YearofMaxFundResponseDto;
import com.example.testproject.service.impl.FundServiceDSLImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/funddsl")
public class FundDSLController {
    private final FundServiceDSLImpl fundService;

    public FundDSLController(FundServiceDSLImpl fundService) {
        this.fundService = fundService;
    }

    @GetMapping
    @RequestMapping("/year")
    public ResponseEntity<List<AnnualTotalAmountResponseDto>> AnnualTotalAmount(){
        return ResponseEntity.ok(fundService.out());
    }

    @GetMapping
    @RequestMapping("/out1")
    public ResponseEntity<YearofMaxFundResponseDto> out1(){
        return ResponseEntity.ok(fundService.out1());
    }
    @GetMapping
    @RequestMapping("/out2")
    public ResponseEntity<List<AverageAmountByBankResponseDto>> out2(@RequestParam(value = "instituteName") String instituteName){
        return ResponseEntity.ok(fundService.out2(instituteName));
    }
}

