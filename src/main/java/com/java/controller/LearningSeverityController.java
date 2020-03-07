
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
import com.java.domain.LearningSeverity;
import com.java.service.LearningSeverityService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class LearningSeverityController {
    @Autowired
    private LearningSeverityService learningSeverityService;

    @RequestMapping(value="/learningseverity/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<LearningSeverity>> getLearningSeverityPage(Model model) {
        List<LearningSeverity> orders = learningSeverityService.getAll();
        return new ResponseEntity<List<LearningSeverity>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/learningseverity/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getLearningSeverityKeys(Model model) {
        return new ResponseEntity(learningSeverityService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/learningseverity/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<LearningSeverity> getLearningSeverityPage(Model model, @PathVariable("id") UUID id) {
        Optional<LearningSeverity> order = learningSeverityService.getById(id);
        return new ResponseEntity<LearningSeverity>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/learningseverity/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody LearningSeverity obj){
     learningSeverityService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/learningseverity/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody LearningSeverity obj){
     obj.setId(id);
     learningSeverityService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/learningseverity/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteLearningSeverity(Model model, @PathVariable UUID id) {
        learningSeverityService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


