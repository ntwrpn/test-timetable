
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
import com.java.domain.OccupationCounter;
import com.java.service.OccupationCounterService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class OccupationCounterController {
    @Autowired
    private OccupationCounterService occupationCounterService;

    @RequestMapping(value="/occupationcounter/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OccupationCounter>> getOccupationCounterPage(Model model) {
        List<OccupationCounter> orders = occupationCounterService.getAll();
        return new ResponseEntity<List<OccupationCounter>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/occupationcounter/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getOccupationCounterKeys(Model model) {
        return new ResponseEntity(occupationCounterService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/occupationcounter/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<OccupationCounter> getOccupationCounterPage(Model model, @PathVariable("id") UUID id) {
        Optional<OccupationCounter> order = occupationCounterService.getById(id);
        return new ResponseEntity<OccupationCounter>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/occupationcounter/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody OccupationCounter obj){
     occupationCounterService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/occupationcounter/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody OccupationCounter obj){
     obj.setId(id);
     occupationCounterService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/occupationcounter/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteOccupationCounter(Model model, @PathVariable UUID id) {
        occupationCounterService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


