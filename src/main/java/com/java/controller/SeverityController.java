
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Severity;
import com.java.service.SeverityService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/severity")
public class SeverityController {

    @Autowired
    private SeverityService severityService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Severity>> getSeverities(HttpServletRequest request) {
        return new ResponseEntity<>(severityService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSeverityKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(severityService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Severity> getSeverity(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(severityService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Severity> addSeverity(HttpServletRequest request, @RequestBody Severity Severity) {
        return new ResponseEntity<>(severityService.save(Severity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Severity> updateSeverity(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Severity Severity) {
        Severity.setId(id);
        return new ResponseEntity<>(severityService.update(Severity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteSeverity(HttpServletRequest request, @PathVariable UUID id) {
        severityService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

