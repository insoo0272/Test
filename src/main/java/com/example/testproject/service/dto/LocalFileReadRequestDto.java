package com.example.testproject.service.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocalFileReadRequestDto {

    private String fileName;
    private String charset;
}
