
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
import com.java.domain.Corps;
import com.java.service.CorpsService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class CorpsController {
    @Autowired
    private CorpsService corpsService;

    @RequestMapping(value="/corps/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Corps>> getCorpsPage(HttpServletRequest request, Model model) {
        List<Corps> corps = corpsService.getAll();
        return new ResponseEntity<List<Corps>>(corps, HttpStatus.OK);
    }
    
    @RequestMapping(value="/corps/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getCorpsKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(corpsService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/corps/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Corps> getCorpsPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<Corps> corps = corpsService.getById(id);
        return new ResponseEntity<Corps>(corps.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/corps/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody Corps obj){
        corpsService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/corps/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Corps obj){
        obj.setId(id);
        corpsService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/corps/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteCorps(HttpServletRequest request, Model model, @PathVariable UUID id) {
        corpsService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


