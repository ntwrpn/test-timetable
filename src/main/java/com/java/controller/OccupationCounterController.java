
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
import com.java.domain.OccupationCounter;
import com.java.service.OccupationCounterService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class OccupationCounterController {
    @Autowired
    private OccupationCounterService occupationcounterService;

    @RequestMapping(value="/occupationcounter/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OccupationCounter>> getOccupationCounterPage(HttpServletRequest request, Model model) {
        List<OccupationCounter> occupationcounter = occupationcounterService.getAll();
        return new ResponseEntity<List<OccupationCounter>>(occupationcounter, HttpStatus.OK);
    }
    
    @RequestMapping(value="/occupationcounter/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getOccupationCounterKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(occupationcounterService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/occupationcounter/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<OccupationCounter> getOccupationCounterPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<OccupationCounter> occupationcounter = occupationcounterService.getById(id);
        return new ResponseEntity<OccupationCounter>(occupationcounter.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/occupationcounter/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody OccupationCounter obj){
        occupationcounterService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/occupationcounter/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody OccupationCounter obj){
        obj.setId(id);
        occupationcounterService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/occupationcounter/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteOccupationCounter(HttpServletRequest request, Model model, @PathVariable UUID id) {
        occupationcounterService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


