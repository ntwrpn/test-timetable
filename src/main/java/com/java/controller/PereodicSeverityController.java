
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
import com.java.domain.PereodicSeverity;
import com.java.service.PereodicSeverityService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class PereodicSeverityController {
    @Autowired
    private PereodicSeverityService pereodicseverityService;

    @RequestMapping(value="/pereodicseverity/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<PereodicSeverity>> getPereodicSeverityPage(HttpServletRequest request, Model model) {
        List<PereodicSeverity> pereodicseverity = pereodicseverityService.getAll();
        return new ResponseEntity<List<PereodicSeverity>>(pereodicseverity, HttpStatus.OK);
    }
    
    @RequestMapping(value="/pereodicseverity/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getPereodicSeverityKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(pereodicseverityService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/pereodicseverity/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<PereodicSeverity> getPereodicSeverityPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<PereodicSeverity> pereodicseverity = pereodicseverityService.getById(id);
        return new ResponseEntity<PereodicSeverity>(pereodicseverity.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/pereodicseverity/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody PereodicSeverity obj){
        pereodicseverityService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/pereodicseverity/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody PereodicSeverity obj){
        obj.setId(id);
        pereodicseverityService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/pereodicseverity/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeletePereodicSeverity(HttpServletRequest request, Model model, @PathVariable UUID id) {
        pereodicseverityService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


