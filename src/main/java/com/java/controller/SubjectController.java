
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
import com.java.domain.Subject;
import com.java.service.SubjectService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value="/subject/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Subject>> getSubjectPage(HttpServletRequest request, Model model) {
        List<Subject> subject = subjectService.getAll();
        return new ResponseEntity<List<Subject>>(subject, HttpStatus.OK);
    }
    
    @RequestMapping(value="/subject/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSubjectKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(subjectService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/subject/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Subject> getSubjectPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<Subject> subject = subjectService.getById(id);
        return new ResponseEntity<Subject>(subject.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/subject/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody Subject obj){
        subjectService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/subject/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Subject obj){
        obj.setId(id);
        subjectService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/subject/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteSubject(HttpServletRequest request, Model model, @PathVariable UUID id) {
        subjectService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


