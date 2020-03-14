
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
import com.java.domain.SemesterNumber;
import com.java.service.SemesterNumberService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class SemesterNumberController {
    @Autowired
    private SemesterNumberService semesternumberService;

    @RequestMapping(value="/semesternumber/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<SemesterNumber>> getSemesterNumberPage(HttpServletRequest request, Model model) {
        List<SemesterNumber> semesternumber = semesternumberService.getAll();
        return new ResponseEntity<List<SemesterNumber>>(semesternumber, HttpStatus.OK);
    }
    
    @RequestMapping(value="/semesternumber/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSemesterNumberKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(semesternumberService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/semesternumber/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<SemesterNumber> getSemesterNumberPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<SemesterNumber> semesternumber = semesternumberService.getById(id);
        return new ResponseEntity<SemesterNumber>(semesternumber.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/semesternumber/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody SemesterNumber obj){
        semesternumberService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/semesternumber/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody SemesterNumber obj){
        obj.setId(id);
        semesternumberService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/semesternumber/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteSemesterNumber(HttpServletRequest request, Model model, @PathVariable UUID id) {
        semesternumberService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


