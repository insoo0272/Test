package com.example.testproject.domain.repository;

import com.example.testproject.domain.entity.Institute;
import com.example.testproject.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InstituteRepository extends JpaRepository<Institute, Long> {
    Institute findByCode(String code);
    Institute findByName(String name);

}
