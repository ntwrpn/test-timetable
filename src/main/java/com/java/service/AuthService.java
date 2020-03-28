package com.java.service;


import com.java.payload.SignUpRequest;
import org.springframework.http.ResponseEntity;


public interface AuthService {
    ResponseEntity<?> authenticateUser(String login, String password);
}

