
package com.java.springsecurity.jdbcauthentication.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import com.java.domain.OccupationCounter;

import com.java.repository.OccupationCounterService;


import java.util.HashMap;
import javax.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class OccupationCounterController {

    private OccupationCounterService orderService = new OccupationCounterService();

    @RequestMapping(value="/occupationcounter/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OccupationCounter>> getOccupationCounterPage(Model model) {
        List<OccupationCounter> orders = orderService.getAll();
        return new ResponseEntity<List<OccupationCounter>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/occupationcounter/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getOccupationCounterKeys(Model model) {
        return new ResponseEntity(orderService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/occupationcounter/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<OccupationCounter> getOccupationCounterPage(Model model, @PathVariable("id") int id) {
        List<OccupationCounter> orders = orderService.getById(id);
        OccupationCounter order = orders.get(0);
        return new ResponseEntity<OccupationCounter>(order, HttpStatus.OK);
    }

    @RequestMapping(value="/occupationcounter/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody OccupationCounter obj){
     orderService.add(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/occupationcounter/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody OccupationCounter obj){
     obj.setId(id);
     orderService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/occupationcounter/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteOccupationCounter(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}



