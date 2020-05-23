
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
import com.java.domain.PereodicSeverity;
import com.java.service.PereodicSeverityService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/pereodicseverity")
public class PereodicSeverityController {

    @Autowired
    private PereodicSeverityService pereodicSeverityService;

    @ExceptionHandler
    public ResponseEntity<Response> handleException(ConstraintViolationException exception) {
        return new ResponseEntity<>(ExceptionResponceCreator.createResponse(exception.getConstraintViolations()), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<PereodicSeverity>> getPereodicSeverities(HttpServletRequest request) {
        return new ResponseEntity<>(pereodicSeverityService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getPereodicSeverityKeys(HttpServletRequest request) {
        return new ResponseEntity(pereodicSeverityService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<PereodicSeverity> getPereodicSeverity(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(pereodicSeverityService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<PereodicSeverity> addPereodicSeverity(HttpServletRequest request, @Valid @RequestBody PereodicSeverity PereodicSeverity) {
        return new ResponseEntity<>(pereodicSeverityService.save(PereodicSeverity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<PereodicSeverity> updatePereodicSeverity(HttpServletRequest request, @PathVariable("id") UUID id, @Valid @RequestBody PereodicSeverity PereodicSeverity) {
        PereodicSeverity.setId(id);
        return new ResponseEntity<>(pereodicSeverityService.update(PereodicSeverity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deletePereodicSeverity(HttpServletRequest request, @PathVariable UUID id) {
        pereodicSeverityService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

