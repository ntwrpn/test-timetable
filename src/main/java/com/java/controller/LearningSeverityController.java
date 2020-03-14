
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
import com.java.domain.LearningSeverity;
import com.java.service.LearningSeverityService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class LearningSeverityController {
    @Autowired
    private LearningSeverityService learningseverityService;

    @RequestMapping(value="/learningseverity/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<LearningSeverity>> getLearningSeverityPage(HttpServletRequest request, Model model) {
        List<LearningSeverity> learningseverity = learningseverityService.getAll();
        return new ResponseEntity<List<LearningSeverity>>(learningseverity, HttpStatus.OK);
    }
    
    @RequestMapping(value="/learningseverity/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getLearningSeverityKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(learningseverityService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/learningseverity/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<LearningSeverity> getLearningSeverityPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<LearningSeverity> learningseverity = learningseverityService.getById(id);
        return new ResponseEntity<LearningSeverity>(learningseverity.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/learningseverity/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody LearningSeverity obj){
        learningseverityService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/learningseverity/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody LearningSeverity obj){
        obj.setId(id);
        learningseverityService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/learningseverity/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteLearningSeverity(HttpServletRequest request, Model model, @PathVariable UUID id) {
        learningseverityService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


