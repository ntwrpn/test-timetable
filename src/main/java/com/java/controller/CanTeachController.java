
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
import com.java.domain.CanTeach;
import com.java.service.CanTeachService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class CanTeachController {
    @Autowired
    private CanTeachService canTeachService;

    @RequestMapping(value="/canteach/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<CanTeach>> getCanTeachPage(Model model) {
        List<CanTeach> orders = canTeachService.getAll();
        return new ResponseEntity<List<CanTeach>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/canteach/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getCanTeachKeys(Model model) {
        return new ResponseEntity(canTeachService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/canteach/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<CanTeach> getCanTeachPage(Model model, @PathVariable("id") UUID id) {
        Optional<CanTeach> order = canTeachService.getById(id);
        return new ResponseEntity<CanTeach>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/canteach/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody CanTeach obj){
     canTeachService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/canteach/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody CanTeach obj){
     obj.setId(id);
     canTeachService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/canteach/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteCanTeach(Model model, @PathVariable UUID id) {
        canTeachService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


