
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
import com.java.domain.Severity;
import com.java.service.SeverityService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class SeverityController {
    @Autowired
    private SeverityService severityService;

    @RequestMapping(value="/severity/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Severity>> getSeverityPage(Model model) {
        List<Severity> orders = severityService.getAll();
        return new ResponseEntity<List<Severity>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/severity/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSeverityKeys(Model model) {
        return new ResponseEntity(severityService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/severity/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Severity> getSeverityPage(Model model, @PathVariable("id") UUID id) {
        Optional<Severity> order = severityService.getById(id);
        return new ResponseEntity<Severity>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/severity/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Severity obj){
     severityService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/severity/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody Severity obj){
     obj.setId(id);
     severityService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/severity/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteSeverity(Model model, @PathVariable UUID id) {
        severityService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


