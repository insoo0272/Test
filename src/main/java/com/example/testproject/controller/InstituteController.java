package com.example.testproject.controller;

import com.example.testproject.domain.entity.Institute;
import com.example.testproject.service.InstituteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/institute")
public class InstituteController {
    private final InstituteService instituteService;

    public InstituteController(final InstituteService instituteService) {
        this.instituteService = instituteService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Institute>> findAll(){
        List<Institute> institutes = instituteService.findAll();
        return ResponseEntity.ok(institutes);
    }
}
