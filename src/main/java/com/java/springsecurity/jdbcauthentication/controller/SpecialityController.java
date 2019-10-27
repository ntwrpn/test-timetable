
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
import com.java.domain.Speciality;

import com.java.repository.SpecialityService;


import java.util.HashMap;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class SpecialityController {

    private SpecialityService orderService = new SpecialityService();

    @RequestMapping(value="/speciality/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Speciality>> getSpecialityPage(Model model) {
        List<Speciality> orders = orderService.getAll();
        return new ResponseEntity<List<Speciality>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/speciality/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Speciality> getCorpsKeys(Model model) {
        Speciality order = new Speciality();
        return new ResponseEntity<Speciality>(order, HttpStatus.OK);
    }
    
    @RequestMapping(value="/speciality/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Speciality> getSpecialityPage(Model model, @PathVariable("id") int id) {
        List<Speciality> orders = orderService.getById(id);
        Speciality order = orders.get(0);
        return new ResponseEntity<Speciality>(order, HttpStatus.OK);
    }

    @RequestMapping(value="/speciality/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Speciality obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/speciality/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Speciality obj){
     obj.setId(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/speciality/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteSpeciality(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



