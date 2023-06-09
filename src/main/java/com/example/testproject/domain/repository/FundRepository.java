package com.example.testproject.domain.repository;

import com.example.testproject.domain.entity.Fund;
import com.example.testproject.domain.entity.User;
import com.example.testproject.service.dto.AnnualTotalAmountResponseDto;
import com.example.testproject.service.dto.YearofMaxFundResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface FundRepository extends JpaRepository<Fund, Long>  {

    @Query(value =
            "select year, SUM(amount) AS total_amount from fund\n" +
            "group by year"
            , nativeQuery = true)
    List<Object[]> findAllAnualTotalFund();

    @Query(value =
        "SELECT YEAR, detail.institute_name FROM \n" +
                "(select year, institute.name AS institute_name,SUM(amount) AS year_amount \n" +
                "from fund join institute on fund.institute = institute.id group by YEAR, institute.name) detail\n" +
                "ORDER BY year_amount DESC LIMIT 1",nativeQuery = true)
    List<Object[]> findYearInstituteofMaxFund();

    @Query(value = "SELECT Year, detail.Avgamount from \n" +
            "(SELECT year, ROUND(SUM(amount)/12) AS Avgamount from fund join institute on fund.institute = institute.id \n" +
            "WHERE institute.name = ?1 group by YEAR, institute.name) detail \n" +
            "ORDER BY detail.Avgamount DESC LIMIT 1", nativeQuery = true)
    List<Object[]> findMaxAverageAmountByBank(String InstituteName);

    @Query(value = "SELECT Year, detail.Avgamount FROM \n" +
            "(SELECT year, ROUND(SUM(amount)/12) AS Avgamount FROM fund join institute on fund.institute = institute.id \n" +
            "WHERE institute.name = ?1 group by YEAR, institute.name) detail \n" +
            "ORDER BY detail.Avgamount ASC LIMIT 1", nativeQuery = true)
    List<Object[]> findMinAverageAmountByBank(String InstituteName);
}
