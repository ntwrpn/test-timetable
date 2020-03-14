
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
import com.java.domain.Severity;
import com.java.service.SeverityService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class SeverityController {
    @Autowired
    private SeverityService severityService;

    @RequestMapping(value="/severity/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Severity>> getSeverityPage(HttpServletRequest request, Model model) {
        List<Severity> severity = severityService.getAll();
        return new ResponseEntity<List<Severity>>(severity, HttpStatus.OK);
    }
    
    @RequestMapping(value="/severity/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSeverityKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(severityService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/severity/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Severity> getSeverityPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<Severity> severity = severityService.getById(id);
        return new ResponseEntity<Severity>(severity.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/severity/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody Severity obj){
        severityService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/severity/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Severity obj){
        obj.setId(id);
        severityService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/severity/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteSeverity(HttpServletRequest request, Model model, @PathVariable UUID id) {
        severityService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


