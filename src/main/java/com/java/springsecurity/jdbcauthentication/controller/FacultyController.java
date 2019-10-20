
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
import com.java.domain.Faculty;

import com.java.repository.FacultyService;


import java.util.HashMap;

@Controller
public class FacultyController {

    private FacultyService orderService = new FacultyService();

    @RequestMapping(value="/faculty/", method=RequestMethod.GET)
    public ResponseEntity<List<Faculty>> getFacultyPage(Model model) {
        List<Faculty> orders = orderService.getAll();
        return new ResponseEntity<List<Faculty>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/faculty/", method=RequestMethod.OPTIONS)
    public ResponseEntity<Faculty> getCorpsKeys(Model model) {
        Faculty order = new Faculty();
        return new ResponseEntity<Faculty>(order, HttpStatus.OK);
    }
    
    @RequestMapping(value="/faculty/{id}", method=RequestMethod.GET)
    public ResponseEntity<Faculty> getFacultyPage(Model model, @PathVariable("id") int id) {
        List<Faculty> orders = orderService.getById(id);
        Faculty order = orders.get(0);
        return new ResponseEntity<Faculty>(order, HttpStatus.OK);
    }

    @RequestMapping(value="/faculty/", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<Void> add(@RequestBody Faculty obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/faculty/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Faculty obj){
     obj.setId(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/faculty/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    public ResponseEntity<Void> DeleteFaculty(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



