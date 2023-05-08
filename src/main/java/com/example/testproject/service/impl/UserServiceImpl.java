package com.example.testproject.service.impl;

import com.example.testproject.domain.entity.User;
import com.example.testproject.support.exception.InvalidAuthInfoException;
import com.example.testproject.domain.repository.UserRepository;
import com.example.testproject.service.UserService;
import com.example.testproject.support.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository){ this.userRepository = userRepository;}

    @Value("${jwt.secret}")
    private String secretKey;
    private Long expiredMs = 1000 * 60 *60l;

    public String login(String userId, String password){

        Optional<User> user = userRepository.findById(userId);

        log.error(user.get().toString());
        if(user.isEmpty()){
            log.error("No user registered with id {}", userId);
            throw new InvalidAuthInfoException("No user registered with given id.");
        }

        byte[] passwordBytes = user.get().getEncodedPassword().getBytes();
        log.error(passwordBytes.toString());
        if (!password.equals(new String(Base64.getDecoder().decode(passwordBytes)))) {
            log.error("Password is incorrect");
            throw new InvalidAuthInfoException("Password is incorrect.");
        }

        return JwtUtil.createJwt(userId, secretKey, expiredMs);
    }
    @Override
    public void signUp(String userId, String password) throws InvalidAuthInfoException {
        User user = User.builder()
                .id(userId)
                .encodedPassword(Base64.getEncoder().encodeToString(password.getBytes()))
                .build();
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
