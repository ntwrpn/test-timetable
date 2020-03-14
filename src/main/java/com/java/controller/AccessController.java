
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
import com.java.domain.Access;
import com.java.service.AccessService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class AccessController {
    @Autowired
    private AccessService accessService;

    @RequestMapping(value="/access/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Access>> getAccessPage(HttpServletRequest request, Model model) {
        List<Access> access = accessService.getAll();
        return new ResponseEntity<List<Access>>(access, HttpStatus.OK);
    }
    
    @RequestMapping(value="/access/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getAccessKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(accessService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/access/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Access> getAccessPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<Access> access = accessService.getById(id);
        return new ResponseEntity<Access>(access.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/access/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody Access obj){
        accessService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/access/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Access obj){
        obj.setId(id);
        accessService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/access/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteAccess(HttpServletRequest request, Model model, @PathVariable UUID id) {
        accessService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


