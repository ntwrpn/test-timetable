
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
import com.java.domain.PereodicSeverity;
import com.java.service.PereodicSeverityService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class PereodicSeverityController {
    @Autowired
    private PereodicSeverityService pereodicSeverityService;

    @RequestMapping(value="/pereodicseverity/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<PereodicSeverity>> getPereodicSeverityPage(Model model) {
        List<PereodicSeverity> orders = pereodicSeverityService.getAll();
        return new ResponseEntity<List<PereodicSeverity>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/pereodicseverity/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getPereodicSeverityKeys(Model model) {
        return new ResponseEntity(pereodicSeverityService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/pereodicseverity/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<PereodicSeverity> getPereodicSeverityPage(Model model, @PathVariable("id") UUID id) {
        Optional<PereodicSeverity> order = pereodicSeverityService.getById(id);
        return new ResponseEntity<PereodicSeverity>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/pereodicseverity/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody PereodicSeverity obj){
     pereodicSeverityService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/pereodicseverity/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody PereodicSeverity obj){
     obj.setId(id);
     pereodicSeverityService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/pereodicseverity/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeletePereodicSeverity(Model model, @PathVariable UUID id) {
        pereodicSeverityService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


