
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
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import com.java.domain.SeveritySubject;
import com.java.service.SeveritySubjectService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class SeveritySubjectController {
    @Autowired
    private SeveritySubjectService severitysubjectService;

    @RequestMapping(value="/severitysubject/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<SeveritySubject>> getSeveritySubjectPage(HttpServletRequest request, Model model) {
        List<SeveritySubject> severitysubject = severitysubjectService.getAll();
        return new ResponseEntity<List<SeveritySubject>>(severitysubject, HttpStatus.OK);
    }
    
    @RequestMapping(value="/severitysubject/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSeveritySubjectKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(severitysubjectService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/severitysubject/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<SeveritySubject> getSeveritySubjectPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<SeveritySubject> severitysubject = severitysubjectService.getById(id);
        return new ResponseEntity<SeveritySubject>(severitysubject.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/severitysubject/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody SeveritySubject obj){
        severitysubjectService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/severitysubject/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody SeveritySubject obj){
        obj.setId(id);
        severitysubjectService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/severitysubject/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteSeveritySubject(HttpServletRequest request, Model model, @PathVariable UUID id) {
        severitysubjectService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


