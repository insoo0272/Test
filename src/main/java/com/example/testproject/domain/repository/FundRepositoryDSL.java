package com.example.testproject.domain.repository;

import com.example.testproject.domain.entity.Fund;
import com.example.testproject.domain.entity.QFund;
import com.example.testproject.domain.entity.QInstitute;
import com.example.testproject.service.dto.AnnualTotalAmountResponseDto;
import com.example.testproject.service.dto.AverageAmountByBankResponseDto;
import com.example.testproject.service.dto.YearofMaxFundResponseDto;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.querydsl.jpa.JPAExpressions.select;

@Repository
public class FundRepositoryDSL {
    private final JPAQueryFactory queryFactory;

    public FundRepositoryDSL(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<AnnualTotalAmountResponseDto> out(){
        List<AnnualTotalAmountResponseDto> annualTotalAmountResponseDtoList = new ArrayList<>();
        QFund fund = QFund.fund;

        var results=  queryFactory.select(
                fund.year,
                fund.amount.sum().as("total_amount")
                        )
                .from(fund)
                .groupBy(fund.year).fetch();

        for(Tuple result : results){
            AnnualTotalAmountResponseDto a = AnnualTotalAmountResponseDto.builder()
                    .year(result.get(0,Integer.class))
                    .totalAmount(result.get(1, Integer.class))
                    .build();
            annualTotalAmountResponseDtoList.add(a);
        }

        return annualTotalAmountResponseDtoList;
    }

    public YearofMaxFundResponseDto out1(){
        QFund fund = QFund.fund;
        QInstitute institute = QInstitute.institute;

        Tuple result=  queryFactory.select(
                        fund.year,institute.name)
                .from(fund)
                .join(institute).on(fund.institute.id.eq(institute.id))
                .groupBy(fund.year,institute.name)
                .orderBy(fund.amount.sum().desc()).limit(1).fetchOne();

        return YearofMaxFundResponseDto.builder()
                .year(result.get(0,Integer.class))
                .instituteName(result.get(1,String.class))
                .build();

    }

    public List<AverageAmountByBankResponseDto> out2(String instituteName){
        QFund fund = QFund.fund;
        QInstitute institute = QInstitute.institute;
        List<AverageAmountByBankResponseDto> aabbrd = new ArrayList<>();

        Tuple result=  queryFactory.select(fund.year, fund.amount.sum().as("Avgamount"))
                .from(fund)
                .join(institute).on(fund.institute.id.eq(institute.id))
                .where(institute.name.eq(instituteName))
                .groupBy(fund.year)
                .orderBy(fund.amount.sum().desc())
                .limit(1)
                .fetchOne();

        Tuple result2=  queryFactory.select(fund.year, fund.amount.sum().as("Avgamount"))
                .from(fund)
                .join(institute).on(fund.institute.id.eq(institute.id))
                .where(institute.name.eq(instituteName))
                .groupBy(fund.year)
                .orderBy(fund.amount.sum().desc())
                .limit(1)
                .fetchOne();

        aabbrd.add(AverageAmountByBankResponseDto.builder()
                .year(result.get(0,Integer.class))
                .avgAmount(result.get(1,Integer.class))
                .build());
        aabbrd.add(AverageAmountByBankResponseDto.builder()
                .year(result2.get(0,Integer.class))
                .avgAmount(result2.get(1,Integer.class))
                .build());
        return aabbrd;
    }

}
