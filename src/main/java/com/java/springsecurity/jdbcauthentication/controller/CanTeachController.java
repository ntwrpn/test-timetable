
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
import com.java.domain.CanTeach;

import com.java.repository.CanTeachService;


import java.util.HashMap;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class CanTeachController {

    private CanTeachService orderService = new CanTeachService();

    @RequestMapping(value="/canteach/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<CanTeach>> getCanTeachPage(Model model) {
        List<CanTeach> orders = orderService.getAll();
        return new ResponseEntity<List<CanTeach>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/canteach/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<CanTeach> getCorpsKeys(Model model) {
        CanTeach order = new CanTeach();
        return new ResponseEntity<CanTeach>(order, HttpStatus.OK);
    }
    
    @RequestMapping(value="/canteach/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<CanTeach> getCanTeachPage(Model model, @PathVariable("id") int id) {
        List<CanTeach> orders = orderService.getById(id);
        CanTeach order = orders.get(0);
        return new ResponseEntity<CanTeach>(order, HttpStatus.OK);
    }

    @RequestMapping(value="/canteach/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody CanTeach obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/canteach/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody CanTeach obj){
     obj.setId(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/canteach/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteCanTeach(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



