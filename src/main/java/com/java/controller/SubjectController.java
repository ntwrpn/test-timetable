
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
import com.java.domain.Subject;
import com.java.service.SubjectService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value="/subject/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Subject>> getSubjectPage(Model model) {
        List<Subject> orders = subjectService.getAll();
        return new ResponseEntity<List<Subject>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/subject/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSubjectKeys(Model model) {
        return new ResponseEntity(subjectService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/subject/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Subject> getSubjectPage(Model model, @PathVariable("id") UUID id) {
        Optional<Subject> order = subjectService.getById(id);
        return new ResponseEntity<Subject>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/subject/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Subject obj){
     subjectService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/subject/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody Subject obj){
     obj.setId(id);
     subjectService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/subject/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteSubject(Model model, @PathVariable UUID id) {
        subjectService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


