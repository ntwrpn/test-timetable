
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
import com.java.domain.Turn;

import com.java.repository.TurnService;


import java.util.HashMap;
import javax.annotation.security.RolesAllowed;

@Controller
public class TurnController {

    private TurnService orderService = new TurnService();

    @RequestMapping(value="/turn/", method=RequestMethod.GET)
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<Turn>> getTurnPage(Model model) {
        List<Turn> orders = orderService.getAll();
        return new ResponseEntity<List<Turn>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/turn/", method=RequestMethod.OPTIONS)
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Turn> getCorpsKeys(Model model) {
        Turn order = new Turn();
        return new ResponseEntity<Turn>(order, HttpStatus.OK);
    }
    
    @RequestMapping(value="/turn/{id}", method=RequestMethod.GET)
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Turn> getTurnPage(Model model, @PathVariable("id") int id) {
        List<Turn> orders = orderService.getById(id);
        Turn order = orders.get(0);
        return new ResponseEntity<Turn>(order, HttpStatus.OK);
    }

    @RequestMapping(value="/turn/", method = RequestMethod.POST, headers="Accept=application/json")
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Void> add(@RequestBody Turn obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/turn/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Turn obj){
     obj.setId(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/turn/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Void> DeleteTurn(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



