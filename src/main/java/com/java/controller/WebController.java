package com.java.controller;

import com.java.config.JwtProvider;
import com.java.domain.Users;
import com.java.payload.JwtAuthenticationResponse;
import com.java.payload.LoginRequest;
import com.java.service.UsersService;
import java.net.URI;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UsersService userService;

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/home")
    public String home(HttpServletRequest request) {
        if (request.isUserInRole("ADMIN")) {
            return "admin";
        } else if (request.isUserInRole("USER")) {
            return "mypage";
        } else if (request.isUserInRole("LECTERN")) {
            String login = request.getUserPrincipal().getName();
            String jwt = getJwt(request);
            Users user = userService.getByName(login).get();
            return "redirect:http://localhost:4200/lectern/" + user.getLectern().getId();// + "?token=" + jwt;
        } else {
            String login = request.getUserPrincipal().getName();
            Users user = userService.getByName(login).get();
            if (!user.getUserRoles().isEmpty()){
                if (user.getUserRoles().get(0).getEndpoint()!=null){
                    return "redirect:"+user.getUserRoles().get(0).getEndpoint();
                }
            }
            return "mypage";
        }
    }

    @RequestMapping(value = "/mypage")
    public String mypage() {
        return "mypage";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/objects")
    public String objects() {
        return "object";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/user")
    public String user() {
        return "user";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/admin")
    public String admin() {
        return "admin";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/403")
    public String Error403() {
        return "403";
    }

    private String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
          return authHeader.replace("Bearer ","");
        }
        return null;
    }
    
    
 
}
