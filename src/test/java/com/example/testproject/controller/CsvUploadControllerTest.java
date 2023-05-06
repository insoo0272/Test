package com.example.testproject.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.testproject.service.DataReadService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CSVUploadController.class)
@WithMockUser
public class CsvUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DataReadService dataReadService;

    @Test
    @DisplayName("csv 파일을 가져오는 테스트")
    void UploadCsvFile() throws Exception{
        given(dataReadService.readAndStoreData("사전과제3.csv","UTF-8")).willReturn("업로드가 완료되었습니다.");

        String Path = "사전과제3.csv";
        String charset = "UTF-8";
        String content = "{\n" +
                "    \"fileName\" : \"사전과제3.csv\",\n" +
                "    \"charset\" : \"UTF-8\"\n" +
                "}";

        mockMvc.perform(
                post("/api/local-csv")
                        .header("Authorization", "Bearer 1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("업로드가 완료되었습니다.")))
                .andDo(print());

        verify(dataReadService).readAndStoreData("사전과제3.csv", "UTF-8");
    }

}