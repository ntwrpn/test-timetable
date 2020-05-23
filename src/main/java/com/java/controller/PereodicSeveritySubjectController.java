
package com.java.controller;

import java.util.List;
import java.util.UUID;

import com.java.config.ExceptionResponceCreator;
import com.java.domain.Response;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.PereodicSeveritySubject;
import com.java.service.PereodicSeveritySubjectService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/pereodicseveritysubject")
public class PereodicSeveritySubjectController {

    @Autowired
    private PereodicSeveritySubjectService pereodicSeveritySubjectService;

    @ExceptionHandler
    public ResponseEntity<Response> handleException(ConstraintViolationException exception) {
        return new ResponseEntity<>(ExceptionResponceCreator.createResponse(exception.getConstraintViolations()), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<PereodicSeveritySubject>> getPereodicSeveritySubjects(HttpServletRequest request) {
        return new ResponseEntity<>(pereodicSeveritySubjectService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getPereodicSeveritySubjectKeys(HttpServletRequest request) {
        return new ResponseEntity(pereodicSeveritySubjectService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<PereodicSeveritySubject> getPereodicSeveritySubject(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(pereodicSeveritySubjectService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<PereodicSeveritySubject> addPereodicSeveritySubject(HttpServletRequest request, @Valid @RequestBody PereodicSeveritySubject PereodicSeveritySubject) {
        return new ResponseEntity<>(pereodicSeveritySubjectService.save(PereodicSeveritySubject), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<PereodicSeveritySubject> updatePereodicSeveritySubject(HttpServletRequest request, @PathVariable("id") UUID id, @Valid @RequestBody PereodicSeveritySubject PereodicSeveritySubject) {
        PereodicSeveritySubject.setId(id);
        return new ResponseEntity<>(pereodicSeveritySubjectService.update(PereodicSeveritySubject), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deletePereodicSeveritySubject(HttpServletRequest request, @PathVariable UUID id) {
        pereodicSeveritySubjectService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

