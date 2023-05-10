package com.example.testproject.service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileInfoDto {
    private String fileName;
    private Long fileLength;
    private String contentType;
    private Boolean isReadble;
    private Boolean isFileEmpty;
    private byte[] fileData;
}
