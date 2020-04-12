package com.java.controller;

import com.java.config.JwtProvider;
import com.java.domain.Employee;
import com.java.domain.Lectern;
import com.java.domain.Teacher;
import com.java.domain.Users;
import com.java.payload.JwtAuthenticationResponse;
import com.java.payload.LoginRequest;
import com.java.service.EmployeeService;
import com.java.service.LecternService;
import com.java.service.TeacherService;
import com.java.service.UsersService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private LecternService lecternService;

    @Autowired
    private UsersService userService;

    @Autowired
    private TeacherService teacherService;
    
    @Autowired
    private EmployeeService employeeService;


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
            if (user.getTeacher() != null) {
                return "redirect:http://localhost:4200/lectern/" + user.getTeacher().getLectern().getId();
            } else {
                return "mypage";
            }
        } else {
            String login = request.getUserPrincipal().getName();
            Users user = userService.getByName(login).get();
            if (!user.getUserRoles().isEmpty()) {
                if (user.getUserRoles().get(0).getEndpoint() != null) {
                    return "redirect:" + user.getUserRoles().get(0).getEndpoint();
                }
            }
            return "mypage";
        }
    }

    @RequestMapping(value = "/mypage")
    public String mypage() {
        return "mypage";
    }

    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/objects")
    public String objects(HttpServletRequest request) {
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
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }

}
