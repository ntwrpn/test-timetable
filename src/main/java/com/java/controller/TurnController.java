
package com.java.controller;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import java.util.HashMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import com.java.domain.Turn;
import com.java.service.TurnService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class TurnController {
    @Autowired
    private TurnService turnService;

    @RequestMapping(value="/turn/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Turn>> getTurnPage(HttpServletRequest request, Model model) {
        List<Turn> turn = turnService.getAll();
        return new ResponseEntity<List<Turn>>(turn, HttpStatus.OK);
    }
    
    @RequestMapping(value="/turn/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getTurnKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(turnService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/turn/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Turn> getTurnPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<Turn> turn = turnService.getById(id);
        return new ResponseEntity<Turn>(turn.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/turn/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody Turn obj){
        turnService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/turn/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Turn obj){
        obj.setId(id);
        turnService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/turn/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteTurn(HttpServletRequest request, Model model, @PathVariable UUID id) {
        turnService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


