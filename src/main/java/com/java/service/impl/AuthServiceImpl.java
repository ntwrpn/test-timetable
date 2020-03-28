/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.service.impl;

import com.java.config.JwtProvider;
import com.java.domain.UserRoles;
import com.java.domain.Users;
import com.java.dto.UserDTOwithToken;
import com.java.payload.ApiResponse;
import com.java.payload.SignUpRequest;
import com.java.repository.UserRolesRepository;
import com.java.repository.UsersRepository;
import com.java.service.AuthService;
import com.java.service.EmailService;
import com.java.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersRepository userRepository;

    @Autowired
    UserRolesRepository roleRepository;

    @Autowired
    UsersService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider tokenProvider;


    @Override
    public ResponseEntity<?> authenticateUser(String login, String password) {
        try {
            Optional<Users> user = userRepository.findByUsername(login);
            if(user.isPresent()) {
                if (!user.get().isEnabled()) {
                    return new ResponseEntity(new ApiResponse(false, "Verify your email"),
                            HttpStatus.BAD_REQUEST);
                }
            }
            else {
                return new ResponseEntity<>(new ApiResponse(false, "Incorrect login or password"),
                        HttpStatus.BAD_REQUEST);
            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login,
                            password
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateJwtToken(authentication);

            //UserDTOwithToken userDTOwithToken = userWithTokenMapper.entityToDto(user.get());
            //userDTOwithToken.setToken(new JwtAuthenticationResponse(jwt));
            // user.setToken(new JwtAuthenticationResponse(jwt));

//            return ResponseEntity.ok(user);
            return ResponseEntity.ok(user.get());
        } catch (AuthenticationException ex) {
            return new ResponseEntity<>(new ApiResponse(false, "Incorrect login or password"),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
