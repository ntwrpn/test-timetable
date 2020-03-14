
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
import com.java.domain.Speciality;
import com.java.service.SpecialityService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class SpecialityController {
    @Autowired
    private SpecialityService specialityService;

    @RequestMapping(value="/speciality/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Speciality>> getSpecialityPage(HttpServletRequest request, Model model) {
        List<Speciality> speciality = specialityService.getAll();
        return new ResponseEntity<List<Speciality>>(speciality, HttpStatus.OK);
    }
    
    @RequestMapping(value="/speciality/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSpecialityKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(specialityService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/speciality/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Speciality> getSpecialityPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<Speciality> speciality = specialityService.getById(id);
        return new ResponseEntity<Speciality>(speciality.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/speciality/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody Speciality obj){
        specialityService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/speciality/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Speciality obj){
        obj.setId(id);
        specialityService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/speciality/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteSpeciality(HttpServletRequest request, Model model, @PathVariable UUID id) {
        specialityService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


