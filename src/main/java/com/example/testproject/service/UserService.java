package com.example.testproject.service;

import com.example.testproject.support.exception.InvalidAuthInfoException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  extends UserDetailsService {
    String login(String userName, String password);
    void signUp(String userId, String password) throws InvalidAuthInfoException;
}
