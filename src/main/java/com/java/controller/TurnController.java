
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
import com.java.domain.Turn;
import com.java.service.TurnService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class TurnController {
    @Autowired
    private TurnService turnService;

    @RequestMapping(value="/turn/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Turn>> getTurnPage(Model model) {
        List<Turn> orders = turnService.getAll();
        return new ResponseEntity<List<Turn>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/turn/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getTurnKeys(Model model) {
        return new ResponseEntity(turnService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/turn/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Turn> getTurnPage(Model model, @PathVariable("id") UUID id) {
        Optional<Turn> order = turnService.getById(id);
        return new ResponseEntity<Turn>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/turn/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Turn obj){
     turnService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/turn/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody Turn obj){
     obj.setId(id);
     turnService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/turn/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteTurn(Model model, @PathVariable UUID id) {
        turnService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


