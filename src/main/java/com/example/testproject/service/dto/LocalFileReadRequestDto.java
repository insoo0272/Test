package com.example.testproject.service.dto;

import lombok.Data;

@Data
public class LocalFileReadRequestDto {

    private String fileName;
    private String charset = "UTF-8";
}
