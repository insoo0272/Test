package com.example.testproject.service;

import com.example.testproject.domain.entity.Institute;
import com.example.testproject.support.exception.NotFoundInsituteException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InstituteService {
    Institute findById(final Long instituteId);

    List<Institute> findAll();

    void join(Institute institute);
}
