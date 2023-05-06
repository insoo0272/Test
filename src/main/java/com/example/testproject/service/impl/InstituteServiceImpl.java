package com.example.testproject.service.impl;

import com.example.testproject.domain.entity.Institute;
import com.example.testproject.domain.repository.InstituteRepository;
import com.example.testproject.service.InstituteService;
import com.example.testproject.support.exception.NotFoundInsituteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class InstituteServiceImpl implements InstituteService {
    private final InstituteRepository instituteRepository;

    public InstituteServiceImpl(InstituteRepository instituteRepository){
        this.instituteRepository = instituteRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Institute findById(final Long instituteId){
        return instituteRepository.findById(instituteId).orElseThrow(NotFoundInsituteException::new);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Institute> findAll(){
        log.error("여기까지 호출");
        return instituteRepository.findAll();
    }

    public void join(Institute institute){ instituteRepository.save(institute);}
}
