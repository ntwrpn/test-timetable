
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
import com.java.domain.Lectern;
import com.java.service.LecternService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class LecternController {
    @Autowired
    private LecternService lecternService;

    @RequestMapping(value="/lectern/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Lectern>> getLecternPage(Model model) {
        List<Lectern> orders = lecternService.getAll();
        return new ResponseEntity<List<Lectern>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/lectern/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getLecternKeys(Model model) {
        return new ResponseEntity(lecternService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/lectern/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Lectern> getLecternPage(Model model, @PathVariable("id") UUID id) {
        Optional<Lectern> order = lecternService.getById(id);
        return new ResponseEntity<Lectern>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/lectern/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Lectern obj){
     lecternService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/lectern/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody Lectern obj){
     obj.setId(id);
     lecternService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/lectern/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteLectern(Model model, @PathVariable UUID id) {
        lecternService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


