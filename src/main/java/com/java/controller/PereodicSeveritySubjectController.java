
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
import com.java.domain.PereodicSeveritySubject;
import com.java.service.PereodicSeveritySubjectService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class PereodicSeveritySubjectController {
    @Autowired
    private PereodicSeveritySubjectService pereodicseveritysubjectService;

    @RequestMapping(value="/pereodicseveritysubject/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<PereodicSeveritySubject>> getPereodicSeveritySubjectPage(HttpServletRequest request, Model model) {
        List<PereodicSeveritySubject> pereodicseveritysubject = pereodicseveritysubjectService.getAll();
        return new ResponseEntity<List<PereodicSeveritySubject>>(pereodicseveritysubject, HttpStatus.OK);
    }
    
    @RequestMapping(value="/pereodicseveritysubject/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getPereodicSeveritySubjectKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(pereodicseveritysubjectService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/pereodicseveritysubject/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<PereodicSeveritySubject> getPereodicSeveritySubjectPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<PereodicSeveritySubject> pereodicseveritysubject = pereodicseveritysubjectService.getById(id);
        return new ResponseEntity<PereodicSeveritySubject>(pereodicseveritysubject.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/pereodicseveritysubject/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody PereodicSeveritySubject obj){
        pereodicseveritysubjectService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/pereodicseveritysubject/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody PereodicSeveritySubject obj){
        obj.setId(id);
        pereodicseveritysubjectService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/pereodicseveritysubject/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeletePereodicSeveritySubject(HttpServletRequest request, Model model, @PathVariable UUID id) {
        pereodicseveritysubjectService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


