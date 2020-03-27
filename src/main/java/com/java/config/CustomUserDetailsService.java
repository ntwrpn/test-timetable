package com.java.config;


import com.java.domain.Users;
import com.java.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UsersRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String loginOrEmail)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        Users user = userRepository.findByUsername(loginOrEmail).get();

        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(UUID id) {
        Users user = userRepository.findById(id).get();

        return UserPrincipal.create(user);
    }
}
