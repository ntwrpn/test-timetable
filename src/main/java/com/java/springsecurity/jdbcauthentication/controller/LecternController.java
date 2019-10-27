
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
import com.java.domain.Lectern;

import com.java.repository.LecternService;


import java.util.HashMap;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class LecternController {

    private LecternService orderService = new LecternService();

    @RequestMapping(value="/lectern/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Lectern>> getLecternPage(Model model) {
        List<Lectern> orders = orderService.getAll();
        return new ResponseEntity<List<Lectern>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/lectern/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Lectern> getCorpsKeys(Model model) {
        Lectern order = new Lectern();
        return new ResponseEntity<Lectern>(order, HttpStatus.OK);
    }
    
    @RequestMapping(value="/lectern/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Lectern> getLecternPage(Model model, @PathVariable("id") int id) {
        List<Lectern> orders = orderService.getById(id);
        Lectern order = orders.get(0);
        return new ResponseEntity<Lectern>(order, HttpStatus.OK);
    }

    @RequestMapping(value="/lectern/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Lectern obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/lectern/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Lectern obj){
     obj.setId(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/lectern/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteLectern(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



