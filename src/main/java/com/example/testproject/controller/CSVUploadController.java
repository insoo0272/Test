package com.example.testproject.controller;

import com.example.testproject.service.DataReadService;
import com.example.testproject.service.dto.LocalFileReadRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/local-csv")
public class CSVUploadController {
    private final DataReadService dataReadService;

    public CSVUploadController(DataReadService dataReadService) {
        this.dataReadService = dataReadService;
    }

    @PostMapping(produces="text/plain;charset=utf-8")
    public ResponseEntity<String> UploadCsvFile(@RequestBody final LocalFileReadRequestDto localFileReadRequestDto){
        String result = dataReadService.readAndStoreData(localFileReadRequestDto.getFileName(),localFileReadRequestDto.getCharset());
        return ResponseEntity.ok(result);
    }
}
