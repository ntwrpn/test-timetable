
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Subject;
import com.java.service.SubjectService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Subject>> getSubjects(@RequestParam(name = "lecternId", required = false)  UUID lecternId, HttpServletRequest request) {
        return new ResponseEntity<>(subjectService.getSubjects(lecternId), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSubjectKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(subjectService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Subject> getSubjectById(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(subjectService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Subject> addSubject(HttpServletRequest request, @RequestBody Subject obj) {
        return new ResponseEntity<>(subjectService.save(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Subject> updateSubject(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Subject obj) {
        obj.setId(id);
        return new ResponseEntity<>(subjectService.update(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteSubjectById(HttpServletRequest request, @PathVariable UUID id) {
        subjectService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}


