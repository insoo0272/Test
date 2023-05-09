package com.example.testproject.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.testproject.service.DataReadService;
import com.example.testproject.service.dto.LocalFileReadRequestDto;
import com.example.testproject.service.impl.DataReadServiceImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.filter.CharacterEncodingFilter;

@ExtendWith(MockitoExtension.class)
public class CsvUploadControllerTest {

    @InjectMocks
    private CSVUploadController csvUploadController;

    @Mock
    DataReadServiceImpl dataReadService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(csvUploadController)
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("csv 파일을 가져오는 테스트")
    public void UploadCsvFile() throws Exception{

        LocalFileReadRequestDto localFileReadRequestDto = new LocalFileReadRequestDto("사전과제.csv","UTF-8");

        when(dataReadService.readAndStoreData("사전과제.csv","UTF-8")).thenReturn("업로드가 완료되었습니다.");

        mockMvc.perform(post("/api/local-csv")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(localFileReadRequestDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("업로드가 완료되었습니다.")));
    }

}
