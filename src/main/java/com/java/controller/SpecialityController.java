
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
import com.java.domain.Speciality;
import com.java.service.SpecialityService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class SpecialityController {
    @Autowired
    private SpecialityService specialityService;

    @RequestMapping(value="/speciality/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Speciality>> getSpecialityPage(Model model) {
        List<Speciality> orders = specialityService.getAll();
        return new ResponseEntity<List<Speciality>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/speciality/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSpecialityKeys(Model model) {
        return new ResponseEntity(specialityService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/speciality/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Speciality> getSpecialityPage(Model model, @PathVariable("id") UUID id) {
        Optional<Speciality> order = specialityService.getById(id);
        return new ResponseEntity<Speciality>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/speciality/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Speciality obj){
     specialityService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/speciality/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody Speciality obj){
     obj.setId(id);
     specialityService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/speciality/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteSpeciality(Model model, @PathVariable UUID id) {
        specialityService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


