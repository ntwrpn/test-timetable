
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

import com.java.repository.UsersService;


import java.util.HashMap;
import javax.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class UsersController {

    private UsersService orderService = new UsersService();

    @RequestMapping(value="/users/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Users>> getUsersPage(Model model) {
        List<Users> orders = orderService.getAll();
        return new ResponseEntity<List<Users>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/users/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Users> getCorpsKeys(Model model) {
        Users order = new Users();
        return new ResponseEntity<Users>(order, HttpStatus.OK);
    }
    
    @RequestMapping(value="/users/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Users> getUsersPage(Model model, @PathVariable("id") int id) {
        List<Users> orders = orderService.getById(id);
        Users order = orders.get(0);
        return new ResponseEntity<Users>(order, HttpStatus.OK);
    }

    @RequestMapping(value="/users/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Users obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/users/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody Users obj){
     obj.setUsername(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteUsers(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



