
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
import com.java.domain.Occupation;
import com.java.service.OccupationService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class OccupationController {
    @Autowired
    private OccupationService occupationService;

    @RequestMapping(value="/occupation/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Occupation>> getOccupationPage(Model model) {
        List<Occupation> orders = occupationService.getAll();
        return new ResponseEntity<List<Occupation>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/occupation/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getOccupationKeys(Model model) {
        return new ResponseEntity(occupationService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/occupation/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Occupation> getOccupationPage(Model model, @PathVariable("id") UUID id) {
        Optional<Occupation> order = occupationService.getById(id);
        return new ResponseEntity<Occupation>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/occupation/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Occupation obj){
     occupationService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/occupation/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody Occupation obj){
     obj.setId(id);
     occupationService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/occupation/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteOccupation(Model model, @PathVariable UUID id) {
        occupationService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


