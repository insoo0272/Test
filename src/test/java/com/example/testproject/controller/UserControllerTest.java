package com.example.testproject.controller;

import com.example.testproject.domain.entity.User;
import com.example.testproject.service.dto.LoginRequestDto;
import com.example.testproject.service.impl.UserServiceImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Base64;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImpl userService;
    private MockMvc mockMvc;

    @BeforeEach
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("회원 가입 성공")
    void SignUpsuccess() throws Exception {
        String Id = "Test";
        String password = "1234";
        LoginRequestDto loginRequestDto = new LoginRequestDto(Id,password);
        String password2 = Base64.getEncoder().encodeToString("1234".getBytes());

        //given
        doNothing().when(userService).signUp(Id,password);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(loginRequestDto))
        );

        //then
        MvcResult mvcResult = resultActions.andExpect(status().isOk())
                .andExpect(content().string(containsString("회원가입 완료")))
                .andReturn();
    }

    @Test
    @DisplayName("토큰 부여 성공")
    void loginsuccess() throws Exception {
        String Id = "Test";
        String password = "1234";
        LoginRequestDto loginRequestDto = new LoginRequestDto(Id,password);

        //given
        //doReturn(anyString()).when(userService).login(Id,password);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(loginRequestDto))
        );

        //then
        MvcResult mvcResult = resultActions.andExpect(status().isOk())
                .andReturn();
    }
}
