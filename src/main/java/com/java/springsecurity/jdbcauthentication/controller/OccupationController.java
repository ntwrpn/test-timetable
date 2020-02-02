
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
import com.java.domain.Occupation;

import com.java.repository.OccupationService;


import java.util.HashMap;
import javax.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class OccupationController {

    private OccupationService orderService = new OccupationService();

    @RequestMapping(value="/occupation/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Occupation>> getOccupationPage(Model model) {
        List<Occupation> orders = orderService.getAll();
        return new ResponseEntity<List<Occupation>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/occupation/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getOccupationCounterKeys(Model model) {
        return new ResponseEntity(orderService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/occupation/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Occupation> getOccupationPage(Model model, @PathVariable("id") int id) {
        List<Occupation> orders = orderService.getById(id);
        Occupation order = orders.get(0);
        return new ResponseEntity<Occupation>(order, HttpStatus.OK);
    }

    @RequestMapping(value="/occupation/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Occupation obj){
     orderService.add(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/occupation/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Occupation obj){
     obj.setId(id);
     orderService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/occupation/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteOccupation(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}



