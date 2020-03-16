
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
import com.java.domain.Deanery;
import com.java.service.DeaneryService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class DeaneryController {
    @Autowired
    private DeaneryService deaneryService;

    @RequestMapping(value="/deanery/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Deanery>> getDeaneryPage(HttpServletRequest request, Model model) {
        List<Deanery> deanery = deaneryService.getAll();
        return new ResponseEntity<List<Deanery>>(deanery, HttpStatus.OK);
    }
    
    @RequestMapping(value="/deanery/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getDeaneryKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(deaneryService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/deanery/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Deanery> getDeaneryPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<Deanery> deanery = deaneryService.getById(id);
        return new ResponseEntity<Deanery>(deanery.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/deanery/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody Deanery obj){
        deaneryService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/deanery/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Deanery obj){
        obj.setId(id);
        deaneryService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/deanery/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteDeanery(HttpServletRequest request, Model model, @PathVariable UUID id) {
        deaneryService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


