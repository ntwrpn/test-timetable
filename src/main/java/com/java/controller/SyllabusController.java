
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
import com.java.domain.Syllabus;
import com.java.service.SyllabusService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class SyllabusController {
    @Autowired
    private SyllabusService syllabusService;

    @RequestMapping(value="/syllabus/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Syllabus>> getSyllabusPage(Model model) {
        List<Syllabus> orders = syllabusService.getAll();
        return new ResponseEntity<List<Syllabus>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/syllabus/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSyllabusKeys(Model model) {
        return new ResponseEntity(syllabusService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/syllabus/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Syllabus> getSyllabusPage(Model model, @PathVariable("id") UUID id) {
        Optional<Syllabus> order = syllabusService.getById(id);
        return new ResponseEntity<Syllabus>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/syllabus/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Syllabus obj){
     syllabusService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/syllabus/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody Syllabus obj){
     obj.setId(id);
     syllabusService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/syllabus/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteSyllabus(Model model, @PathVariable UUID id) {
        syllabusService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


