
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
import com.java.domain.SemesterNumber;
import com.java.service.SemesterNumberService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class SemesterNumberController {
    @Autowired
    private SemesterNumberService semesterNumberService;

    @RequestMapping(value="/semesternumber/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<SemesterNumber>> getSemesterNumberPage(Model model) {
        List<SemesterNumber> orders = semesterNumberService.getAll();
        return new ResponseEntity<List<SemesterNumber>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/semesternumber/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSemesterNumberKeys(Model model) {
        return new ResponseEntity(semesterNumberService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/semesternumber/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<SemesterNumber> getSemesterNumberPage(Model model, @PathVariable("id") UUID id) {
        Optional<SemesterNumber> order = semesterNumberService.getById(id);
        return new ResponseEntity<SemesterNumber>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/semesternumber/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody SemesterNumber obj){
     semesterNumberService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/semesternumber/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody SemesterNumber obj){
     obj.setId(id);
     semesterNumberService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/semesternumber/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteSemesterNumber(Model model, @PathVariable UUID id) {
        semesterNumberService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


