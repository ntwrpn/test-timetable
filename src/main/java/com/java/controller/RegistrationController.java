
package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Users;
import com.java.domain.UserRoles;


import com.java.service.UserRolesService;
import java.util.Set;


import com.java.service.UsersService;
import com.java.service.impl.EmailService;
import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegistrationController {

    @Autowired
    PasswordEncoder passwordEncoder;    

    @Autowired
    private UsersService usersService;
    
    @Autowired
    private EmailService EmailService;

    @Autowired
    private UserRolesService userRolesService;

    @RequestMapping(value="/registration/", method = RequestMethod.GET)
    public String login(){
        return "registration";
    }

    @RequestMapping(value="/registration/", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<Void> add(@RequestBody Users obj){
     if (usersService.getByName(obj.getUsername()).isEmpty()){
        UserRoles role = userRolesService.getByName("ROLE_USER").get();
        obj.setEnabled(false);
        obj.setPassword(passwordEncoder.encode(obj.getPassword()));
        Set<UserRoles> set_roles = new HashSet<UserRoles>();
        set_roles.add(role);
        obj.setUserRoles(set_roles);
        usersService.save(obj);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
        }
    else {
        return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }
    }
/*
    @RequestMapping(value="/resetpassword/", method = RequestMethod.GET)
    public String resetPassword(){
        return "reset";
    }
    
    @RequestMapping(value="/resetpassword/", method = RequestMethod.POST, headers="Accept=application/json")
    @ResponseBody
    public ResponseEntity<Void>  resetPass(HttpServletRequest request, 
        @RequestParam("email") String userEmail) {
        Optional<Users> user = usersService.getByName(userEmail);
        if (user.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        String token = UUID.randomUUID().toString();
        usersService.createPasswordResetTokenForUser(user.get(), token);
        EmailService.sendEmail(constructResetTokenEmail(request.getContextPath(), request.getLocale(), token, user.get()));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    private SimpleMailMessage constructResetTokenEmail(String contextPath, Locale locale, String token, Users user) {
        String url = contextPath + "/user/changePassword?id=" + user.getId() + "&token=" + token;
        //String message = messages.getMessage("message.resetPassword", null, locale);
        return constructEmail("Reset Password", "Confirm reset password:" + " \r\n" + url, user);
      }

      private SimpleMailMessage constructEmail(String subject, String body, Users user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getUsername());
        email.setFrom("timetable-service");
        return email;
      }
 
    @GetMapping("/user/changePassword")
    public String showChangePasswordPage(Locale locale, Model model, 
      @RequestParam("id") UUID id, @RequestParam("token") String token) {
        String result = usersService.validatePasswordResetToken(id, token);
        if (result != null) {
            model.addAttribute("message", result);
            return "redirect:/login?lang=" + locale.getLanguage();
        }
        return "redirect:/updatePassword.html?lang=" + locale.getLanguage();
    }*/
}



