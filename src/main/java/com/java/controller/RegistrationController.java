package com.java.controller;

import com.java.domain.Subgroup;
import com.java.domain.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Users;
import com.java.domain.UserRoles;
import com.java.payload.ApiResponse;
import com.java.payload.RegistrationRequest;
import com.java.payload.ResetPasswordRequest;
import com.java.service.TeacherService;

import com.java.service.UserRolesService;
import java.util.Set;

import com.java.service.UsersService;
import com.java.service.EmailService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UsersService usersService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRolesService userRolesService;

    @RequestMapping(value = "/registration/", method = RequestMethod.GET)
    public String login() {
        return "registration";
    }

    @RequestMapping(value = "/registration/", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> add(@Valid @RequestBody RegistrationRequest regRequest) {
        Users user = new Users();
        if (usersService.getByName(regRequest.getUsername()).isEmpty()) {
            UserRoles role = userRolesService.getByName("ROLE_USER").get();
            user.setName(regRequest.getName());
            user.setSurname(regRequest.getSurname());
            user.setPatronymic(regRequest.getPatronymic());
            user.setEnabled(false);
            user.setUsername(regRequest.getUsername());
            user.setPassword(passwordEncoder.encode(regRequest.getPassword()));
            List<UserRoles> set_roles = new ArrayList<UserRoles>();
            set_roles.add(role);
            user.setUserRoles(set_roles);
            usersService.save(user);

            return new ResponseEntity<>(new ApiResponse(true, "Регистрания пройдена успешно."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse(true, "Такой логин уже занят."), HttpStatus.OK);
        }
    }
    
    
    @GetMapping("/resetPassword")
    public String resetPassword(@RequestParam(required = false) String username){
        if (username!=null){
            Users user = usersService.getByName(username).get();
            String token=usersService.createTokenForUser(user);
            System.out.println(token);
            //emailService.constructResetTokenEmail(token, user);
            return "index";
        } else{
            return "reset";
        }
    }
    
    @GetMapping("/changePassword")
    public ModelAndView  getChangePassword(@RequestParam(required = false) String token, @RequestParam(required = false) UUID id) {
        ModelAndView model = new ModelAndView("changePassword");
        model.addObject("token", token);
        model.addObject("id", id);
        return model;
    }
    
    @PostMapping("/changePassword")
    public ResponseEntity<?> updatePassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        System.out.println(resetPasswordRequest.getPassword());
        return usersService.updatePassword(resetPasswordRequest);
    }
    
}
