package com.example.testproject.domain.Repository;

import com.example.testproject.domain.repository.FundRepositoryDSL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
public class FundRepositoryDSLTest {

    @InjectMocks
    private FundRepositoryDSL fundRepositoryDSL;

    private MockMvc mockMvc;

    @BeforeEach
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(fundRepositoryDSL)
                .alwaysDo(print())
                .build();
    }

    @Test
    public void out() throws Exception{

    }
}
