package com.example.testproject.service;

import com.example.testproject.support.exception.InvalidAuthInfoException;

public interface UserService {
    String login(String userName, String password);
    void signUp(String userId, String password) throws InvalidAuthInfoException;
}
