package com.example.testproject.controller;

import com.example.testproject.service.DataReadService;
import com.example.testproject.service.dto.FileInfoDto;
import com.example.testproject.service.dto.LocalFileReadRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class CSVUploadController {
    private final DataReadService dataReadService;

    public CSVUploadController(DataReadService dataReadService) {
        this.dataReadService = dataReadService;
    }

    @PostMapping(value="/local-csv", produces="text/plain;charset=utf-8")
    public ResponseEntity<String> UploadCsvFile(@RequestBody final LocalFileReadRequestDto localFileReadRequestDto){
        String result = dataReadService.readAndStoreData(localFileReadRequestDto.getFileName(),localFileReadRequestDto.getCharset());
        return ResponseEntity.ok(result);
    }

    @PostMapping(value="/getFileInfo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileInfoDto> getFileInfo(@RequestParam("file") MultipartFile userFile) throws IOException {
        FileInfoDto fileInfoDto = new FileInfoDto();

        fileInfoDto.setFileName(userFile.getName());
        fileInfoDto.setContentType(userFile.getContentType());
        fileInfoDto.setFileLength(userFile.getSize());
        fileInfoDto.setIsFileEmpty(userFile.isEmpty());
        fileInfoDto.setIsReadble(userFile.getResource().isReadable());
        fileInfoDto.setFileData(userFile.getBytes());

        BufferedReader br;
        String aa = "";
        String line;
        InputStream is = userFile.getInputStream();
        br = new BufferedReader(new InputStreamReader(is));
        while((line = br.readLine())!= null){
            aa = aa + line + "\n";
        }

        log.error(aa);

        return ResponseEntity.ok(fileInfoDto);
    }
}
