
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
import com.java.domain.SemesterNumber;
import com.java.service.SemesterNumberService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/semesternumber")
public class SemesterNumberController {

    @Autowired
    private SemesterNumberService semesterNumberService;

    @ExceptionHandler
    public ResponseEntity<Response> handleException(ConstraintViolationException exception) {
        return new ResponseEntity<>(ExceptionResponceCreator.createResponse(exception.getConstraintViolations()), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<SemesterNumber>> getSemesterNumbers(HttpServletRequest request) {
        return new ResponseEntity<>(semesterNumberService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSemesterNumberKeys(HttpServletRequest request) {
        return new ResponseEntity(semesterNumberService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<SemesterNumber> getSemesterNumber(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(semesterNumberService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<SemesterNumber> addSemesterNumber(HttpServletRequest request, @Valid @RequestBody SemesterNumber SemesterNumber) {
        return new ResponseEntity<>(semesterNumberService.save(SemesterNumber), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<SemesterNumber> updateSemesterNumber(HttpServletRequest request, @PathVariable("id") UUID id, @Valid @RequestBody SemesterNumber SemesterNumber) {
        SemesterNumber.setId(id);
        return new ResponseEntity<>(semesterNumberService.update(SemesterNumber), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteSemesterNumber(HttpServletRequest request, @PathVariable UUID id) {
        semesterNumberService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

