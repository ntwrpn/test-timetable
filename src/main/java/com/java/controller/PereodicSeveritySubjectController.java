
package com.java.controller;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import java.util.HashMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import com.java.domain.PereodicSeveritySubject;
import com.java.service.PereodicSeveritySubjectService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class PereodicSeveritySubjectController {
    @Autowired
    private PereodicSeveritySubjectService orderService;

    @RequestMapping(value="/pereodicseveritysubject/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<PereodicSeveritySubject>> getPereodicSeveritySubjectPage(Model model) {
        List<PereodicSeveritySubject> orders = orderService.getAll();
        return new ResponseEntity<List<PereodicSeveritySubject>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/pereodicseveritysubject/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getPereodicSeveritySubjectKeys(Model model) {
        return new ResponseEntity(orderService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/pereodicseveritysubject/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<PereodicSeveritySubject> getPereodicSeveritySubjectPage(Model model, @PathVariable("id") UUID id) {
        Optional<PereodicSeveritySubject> order = orderService.getById(id);
        return new ResponseEntity<PereodicSeveritySubject>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/pereodicseveritysubject/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody PereodicSeveritySubject obj){
     orderService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/pereodicseveritysubject/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody PereodicSeveritySubject obj){
     obj.setId(id);
     orderService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/pereodicseveritysubject/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeletePereodicSeveritySubject(Model model, @PathVariable UUID id) {
        orderService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


