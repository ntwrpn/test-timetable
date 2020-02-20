
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

    private final UsersService orderService = new UsersService();

    @RequestMapping(value="/users/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Users>> getUsersPage(@RequestParam(required = false) Boolean enabled, Model model) {
        List<Users> orders;
        if (enabled!=null){
            orders = orderService.getByEnabled(enabled);
        } else{
            orders = orderService.getAll();
        }
        return new ResponseEntity<List<Users>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/users/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity getUsersKeys(Model model) {
        return new ResponseEntity(orderService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/users/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Users> getUsersPage(Model model, @PathVariable("id") Integer id) {
        Users order = orderService.getById(id);
        return new ResponseEntity<Users>(order, HttpStatus.OK);
    }

    @RequestMapping(value="/users/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Users obj){
     orderService.add(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/users/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody Users obj){
     orderService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteUsers (Model model, @PathVariable Integer id) {
        orderService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value="/users/accept/{id}", method=RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> AcceptUsers(@PathVariable Integer id) {
        Users order = orderService.getById(id);
        order.setEnabled(true);
        orderService.update(order);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}



