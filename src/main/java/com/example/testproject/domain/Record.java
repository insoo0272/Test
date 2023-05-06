package com.example.testproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class Record {
    private Integer year;
    private Integer month;
    private Integer amount;
}
