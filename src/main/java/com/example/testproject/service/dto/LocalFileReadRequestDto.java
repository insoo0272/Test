package com.example.testproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalFileReadRequestDto {

    private String fileName;
    private String charset = "UTF-8";
}
