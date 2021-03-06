
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.SeveritySubject;
import com.java.service.SeveritySubjectService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/severitysubject")
public class SeveritySubjectController {

    @Autowired
    private SeveritySubjectService severitySubjectService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<SeveritySubject>> getSeveritySubjects(HttpServletRequest request) {
        return new ResponseEntity<>(severitySubjectService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSeveritySubjectKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(severitySubjectService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<SeveritySubject> getSeveritySubject(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(severitySubjectService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<SeveritySubject> addSeveritySubject(HttpServletRequest request, @RequestBody SeveritySubject SeveritySubject) {
        return new ResponseEntity<>(severitySubjectService.save(SeveritySubject), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<SeveritySubject> updateSeveritySubject(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody SeveritySubject SeveritySubject) {
        SeveritySubject.setId(id);
        return new ResponseEntity<>(severitySubjectService.update(SeveritySubject), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteSeveritySubject(HttpServletRequest request, @PathVariable UUID id) {
        severitySubjectService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

