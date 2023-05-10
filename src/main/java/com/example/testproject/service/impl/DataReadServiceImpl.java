package com.example.testproject.service.impl;

import com.example.testproject.Configuration.InstituteConfig;
import com.example.testproject.domain.Record;
import com.example.testproject.domain.entity.Fund;
import com.example.testproject.domain.entity.Institute;
import com.example.testproject.domain.repository.FundRepository;
import com.example.testproject.domain.repository.InstituteRepository;
import com.example.testproject.service.DataReadService;
import com.example.testproject.support.exception.DataReadWriteFailException;
import com.example.testproject.support.utils.TextReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class DataReadServiceImpl  implements DataReadService {

    private final TextReader textReader;
    private final InstituteRepository instituteRepository;
    private final FundRepository fundRepository;
    private final InstituteConfig instituteConfig;

    public DataReadServiceImpl(TextReader textReader, InstituteRepository instituteRepository, FundRepository fundRepository, InstituteConfig instituteConfig) {
        this.textReader = textReader;
        this.instituteRepository = instituteRepository;
        this.fundRepository = fundRepository;
        this.instituteConfig = instituteConfig;
    }

    @Override
    public String readAndStoreData(String path, String charset) throws DataReadWriteFailException {
        Stream<List<String>> data;
        try {
            data = readCsvFromFile(path, charset);
        }catch (IOException e) {
            log.error("Failed to read data from csv file. path={}, charset={}", path, charset, e);
            throw new DataReadWriteFailException();
        }
        Stream<List<Record>> recordsByRow = data.skip(1).map(line -> {
            try{
                return mapDataToRecordRow(line);
            }catch (Exception e) {
                log.error("Failed to read data. It is wrong typed. {}", line, e);
                throw new DataReadWriteFailException("Wrong typed data row.");
            }
        });
        List<Institute> institutes = instituteConfig.getNameAndCodes().stream()
                .map(csvMeta -> instituteRepository.findByName(csvMeta.getName()))
                .collect(Collectors.toList());
        log.error(institutes.toString());
        recordsByRow.forEach(records -> {
            try{
                storeRecords(records, institutes);
            }catch (Exception e) {
                log.error("Failed to store data.", e);
                throw new DataReadWriteFailException();
            }
        });
        return "업로드가 완료되었습니다.";
    }

    private Stream<List<String>> readCsvFromFile(String fileName, String charset) throws IOException {
        String path = "classpath:" + fileName;
        return textReader.read(path, charset);
    }

    private List<Record> mapDataToRecordRow(List<String> data) throws Exception{
        Integer year = Integer.parseInt(data.get(0));
        Integer month = Integer.parseInt(data.get(1));

        if(year < 0 || !(1<= month && month <=12)){
            throw new Exception("Invalid type of year or month");
        }
        return data.stream()
                .skip(2)
                .filter(str -> !isNullOrEmpty(str))
                .map(Integer::parseInt)
                .map(amount -> Record.builder()
                        .year(year)
                        .month(month)
                        .amount(amount)
                        .build())
                .collect(Collectors.toList());
    }

    void storeRecords(List<Record> records, List<Institute> institutes) throws Exception {
        if (records.size() != institutes.size()) {
            throw new Exception("The number of columns in data must be same with that of institutes");
        }
        for (int i = 0; i < institutes.size(); ++i) {
            Record record = records.get(i);
            Institute institute = institutes.get(i);

            Fund fund = Fund.builder()
                    .year(record.getYear())
                    .month(record.getMonth())
                    .amount(record.getAmount())
                    .institute(institute)
                    .build();
            fundRepository.save(fund);
            //institute.getFundList().add(fund);
        }
    }
    public static boolean isNullOrEmpty(String str) {
        if (str == null) {
            return true;
        } else if (str.trim().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
