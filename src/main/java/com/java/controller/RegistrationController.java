
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
import java.util.HashSet;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class RegistrationController {

    @Autowired
    PasswordEncoder passwordEncoder;    

    @Autowired
    private UsersService usersService;

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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
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


 

}



