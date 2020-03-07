
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
import com.java.domain.Semester;
import com.java.service.SemesterService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class SemesterController {
    @Autowired
    private SemesterService semesterService;

    @RequestMapping(value="/semester/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Semester>> getSemesterPage(Model model) {
        List<Semester> orders = semesterService.getAll();
        return new ResponseEntity<List<Semester>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/semester/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSemesterKeys(Model model) {
        return new ResponseEntity(semesterService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/semester/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Semester> getSemesterPage(Model model, @PathVariable("id") UUID id) {
        Optional<Semester> order = semesterService.getById(id);
        return new ResponseEntity<Semester>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/semester/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Semester obj){
     semesterService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/semester/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody Semester obj){
     obj.setId(id);
     semesterService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/semester/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteSemester(Model model, @PathVariable UUID id) {
        semesterService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


