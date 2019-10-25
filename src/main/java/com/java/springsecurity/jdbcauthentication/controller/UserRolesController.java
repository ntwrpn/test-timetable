
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
import com.java.domain.UserRoles;

import com.java.repository.UserRolesService;


import java.util.HashMap;
import javax.annotation.security.RolesAllowed;

@Controller
public class UserRolesController {

    private UserRolesService orderService = new UserRolesService();

    @RequestMapping(value="/user_roles/", method=RequestMethod.GET)
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<List<UserRoles>> getUserRolesPage(Model model) {
        List<UserRoles> orders = orderService.getAll();
        return new ResponseEntity<List<UserRoles>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/user_roles/", method=RequestMethod.OPTIONS)
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<UserRoles> getCorpsKeys(Model model) {
        UserRoles order = new UserRoles();
        return new ResponseEntity<UserRoles>(order, HttpStatus.OK);
    }
    
    @RequestMapping(value="/user_roles/{id}", method=RequestMethod.GET)
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<UserRoles> getUserRolesPage(Model model, @PathVariable("id") int id) {
        List<UserRoles> orders = orderService.getById(id);
        UserRoles order = orders.get(0);
        return new ResponseEntity<UserRoles>(order, HttpStatus.OK);
    }

    @RequestMapping(value="/user_roles/", method = RequestMethod.POST, headers="Accept=application/json")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Void> add(@RequestBody UserRoles obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/user_roles/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody UserRoles obj){
     obj.setUser_role_id(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/user_roles/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Void> DeleteUserRoles(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



