
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
import com.java.domain.Syllabus;

import com.java.repository.SyllabusService;


import java.util.HashMap;
import javax.annotation.security.RolesAllowed;

@Controller
public class SyllabusController {

    private SyllabusService orderService = new SyllabusService();

    
    @RequestMapping(value="/syllabus/", method=RequestMethod.GET)
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<Syllabus>> getSyllabusPage(Model model) {
        List<Syllabus> orders = orderService.getAll();
        return new ResponseEntity<List<Syllabus>>(orders, HttpStatus.OK);
    }
    
    
    @RequestMapping(value="/syllabus/{id}", method=RequestMethod.GET)
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<Syllabus>> getSyllabusPage(Model model, @PathVariable("id") int id) {
        List<Syllabus> orders = orderService.getById(id);
        return new ResponseEntity<List<Syllabus>>(orders, HttpStatus.OK);
    }

    @RequestMapping(value="/syllabus/", method = RequestMethod.POST, headers="Accept=application/json")
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Void> add(@RequestBody Syllabus obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/syllabus/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Syllabus obj){
     obj.setId(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/syllabus/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Void> DeleteSyllabus(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



