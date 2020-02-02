
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
import com.java.domain.SeveritySubject;

import com.java.repository.SeveritySubjectService;


import java.util.HashMap;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class SeveritySubjectController {

    private SeveritySubjectService orderService = new SeveritySubjectService();

    @RequestMapping(value="/severitysubject/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<SeveritySubject>> getSeveritySubjectPage(Model model) {
        List<SeveritySubject> orders = orderService.getAll();
        return new ResponseEntity<List<SeveritySubject>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/severitysubject/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSeveritySubjectKeys(Model model) {
        return new ResponseEntity(orderService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/severitysubject/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<SeveritySubject> getSeveritySubjectPage(Model model, @PathVariable("id") int id) {
        List<SeveritySubject> orders = orderService.getById(id);
        SeveritySubject order = orders.get(0);
        return new ResponseEntity<SeveritySubject>(order, HttpStatus.OK);
    }

    @RequestMapping(value="/severitysubject/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody SeveritySubject obj){
     orderService.add(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/severitysubject/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody SeveritySubject obj){
     obj.setId(id);
     orderService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/severitysubject/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteSeveritySubject(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}



