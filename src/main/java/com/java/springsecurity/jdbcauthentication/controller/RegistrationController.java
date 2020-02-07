
package com.java.springsecurity.jdbcauthentication.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import com.java.domain.Users;
import com.java.domain.UserRoles;
import com.java.repository.UserRolesService;

import com.java.repository.UsersService;


import java.util.HashMap;
import javax.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class RegistrationController {

    private UsersService orderService = new UsersService();
    private UserRolesService rolesService = new UserRolesService();

    @RequestMapping(value="/registration/", method = RequestMethod.GET)
    public String login(){
        return "registration";
    }

    @RequestMapping(value="/registration/", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<Void> add(@RequestBody Users obj){
     obj.setEnabled(false);
     orderService.add(obj);
     Users user = orderService.getByName(obj.getUsername());
     UserRoles roles = new UserRoles();
     roles.setRole("NONE_ROLE");
     //roles.setUsername(user);
     rolesService.add(roles);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 

}



