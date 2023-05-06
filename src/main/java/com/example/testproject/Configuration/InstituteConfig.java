package com.example.testproject.Configuration;

import com.example.testproject.domain.CsvMeta;
import com.example.testproject.domain.entity.Institute;
import com.example.testproject.domain.repository.InstituteRepository;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("institute")
@Data
public class InstituteConfig {

    private final InstituteRepository instituteRepository;
    private List<CsvMeta> nameAndCodes;

    public InstituteConfig(final InstituteRepository instituteRepository) {
        this.instituteRepository = instituteRepository;
    }

    @PostConstruct
    public void init() {
        nameAndCodes.forEach(csvMeta -> {
            Institute institute = Institute.builder()
                    .name(csvMeta.getName())
                    .code(csvMeta.getCode())
                    .build();
            instituteRepository.save(institute);
        });
    }
}