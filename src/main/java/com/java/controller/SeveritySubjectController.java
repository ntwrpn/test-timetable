
package com.java.controller;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.SeveritySubject;
import com.java.service.SeveritySubjectService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class SeveritySubjectController {
    @Autowired
    private SeveritySubjectService severitySubjectService;

    @RequestMapping(value="/severitysubject/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<SeveritySubject>> getSeveritySubjectPage(Model model) {
        List<SeveritySubject> orders = severitySubjectService.getAll();
        return new ResponseEntity<List<SeveritySubject>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/severitysubject/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSeveritySubjectKeys(Model model) {
        return new ResponseEntity(severitySubjectService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/severitysubject/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<SeveritySubject> getSeveritySubjectPage(Model model, @PathVariable("id") UUID id) {
        Optional<SeveritySubject> order = severitySubjectService.getById(id);
        return new ResponseEntity<SeveritySubject>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/severitysubject/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody SeveritySubject obj){
     severitySubjectService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/severitysubject/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody SeveritySubject obj){
     obj.setId(id);
     severitySubjectService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/severitysubject/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteSeveritySubject(Model model, @PathVariable UUID id) {
        severitySubjectService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


