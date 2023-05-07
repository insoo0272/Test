package com.example.testproject.service;

import com.example.testproject.domain.entity.User;
import com.example.testproject.domain.repository.UserRepository;
import com.example.testproject.service.impl.UserServiceImpl;
import com.example.testproject.support.exception.InvalidAuthInfoException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Base64;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("회원가입 테스트")
    void SignUp(){
        User user = new User("test",Base64.getEncoder().encodeToString("1234".getBytes()));

        given(userRepository.save(user)).willReturn(user);

        userServiceImpl.signUp("test","1234");

        verify(userRepository).save(any(User.class));
        //Assertions.assertThat(userRepository.findById("test")).isEqualTo(user);

    }
}
