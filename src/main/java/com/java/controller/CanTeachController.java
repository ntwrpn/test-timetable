
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
import com.java.domain.CanTeach;
import com.java.service.CanTeachService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class CanTeachController {
    @Autowired
    private CanTeachService canteachService;

    @RequestMapping(value="/canteach/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<CanTeach>> getCanTeachPage(HttpServletRequest request, Model model) {
        List<CanTeach> canteach = canteachService.getAll();
        return new ResponseEntity<List<CanTeach>>(canteach, HttpStatus.OK);
    }
    
    @RequestMapping(value="/canteach/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getCanTeachKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(canteachService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/canteach/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<CanTeach> getCanTeachPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<CanTeach> canteach = canteachService.getById(id);
        return new ResponseEntity<CanTeach>(canteach.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/canteach/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody CanTeach obj){
        canteachService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/canteach/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody CanTeach obj){
        obj.setId(id);
        canteachService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/canteach/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteCanTeach(HttpServletRequest request, Model model, @PathVariable UUID id) {
        canteachService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


