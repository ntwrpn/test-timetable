
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
import com.java.domain.Severity;

import com.java.repository.SeverityService;


import java.util.HashMap;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class SeverityController {

    private SeverityService orderService = new SeverityService();

    @RequestMapping(value="/severity/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Severity>> getSeverityPage(Model model) {
        List<Severity> orders = orderService.getAll();
        return new ResponseEntity<List<Severity>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/severity/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSeverityKeys(Model model) {
        return new ResponseEntity(orderService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/severity/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Severity> getSeverityPage(Model model, @PathVariable("id") int id) {
        List<Severity> orders = orderService.getById(id);
        Severity order = orders.get(0);
        return new ResponseEntity<Severity>(order, HttpStatus.OK);
    }

    @RequestMapping(value="/severity/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Severity obj){
     orderService.add(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/severity/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Severity obj){
     obj.setId(id);
     orderService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/severity/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteSeverity(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}



