
package com.java.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import com.java.domain.Users;
import com.java.domain.UserRoles;


import com.java.service.UserRolesService;
import java.util.Set;
import java.util.HashSet;



import com.java.service.UsersService;

import java.util.HashMap;
import javax.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class RegistrationController {

    @Autowired
    PasswordEncoder passwordEncoder;    

    @Autowired
    private UsersService orderService;

    @Autowired
    private UserRolesService rolesService;

    @RequestMapping(value="/registration/", method = RequestMethod.GET)
    public String login(){
        return "registration";
    }

    @RequestMapping(value="/registration/", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<Void> add(@RequestBody Users obj){
     Users find_same = orderService.getByName(obj.getUsername()).get();
     if (find_same==null){
        UserRoles role = rolesService.getByName("ROLE_ADMIN").get();
        obj.setEnabled(false);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        obj.setPassword(passwordEncoder.encode(obj.getPassword()));
        Set<UserRoles> set_roles = null;
        set_roles.add(role);
        obj.setUserRoles(set_roles);
        orderService.save(obj);
        Users user = orderService.getByName(obj.getUsername()).get();
        
        //roles.setRole("NONE_ROLE");
        //roles.setUsername(user);
        //rolesService.add(roles);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
        }
    else {
        return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }
    }


 

}



