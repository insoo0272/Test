package com.example.testproject.controller;

import com.example.testproject.domain.entity.Institute;
import com.example.testproject.service.DataReadService;
import com.example.testproject.service.impl.InstituteServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InstituteController.class)
@WithMockUser
public class InstituteControllerTest {

    @BeforeAll
    static void beforeAll() {

    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    InstituteServiceImpl instituteServiceImpl;

    @Test
    @DisplayName("csv 파일을 가져오는 테스트")
    void findAll() throws Exception{
        String validToken = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6InNhIiwiaWF0IjoxNjgzMzkwNDI2LCJleHAiOjE2ODMzOTQwMjZ9.qvOwX0IJ34PviCNm9El8MjnR-pEgSZ1TYFSFJwGw2fw";

        mockMvc.perform(
                        get("/api/institute/all")
                                .header("Authorization", "Bearer " + validToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..name").value(hasItem("한국은행")))
                .andExpect(jsonPath("$..code").value(hasItem("Korea")))
                .andDo(print());

        verify(instituteServiceImpl).findAll();
    }
}
