
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.LearningSeverity;
import com.java.service.LearningSeverityService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/learningseverity")
public class LearningSeverityController {

    @Autowired
    private LearningSeverityService learningSeverityService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<LearningSeverity>> getLearningSeveritys(HttpServletRequest request) {
        return new ResponseEntity<>(learningSeverityService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getLearningSeverityKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(learningSeverityService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<LearningSeverity> getLearningSeverity(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(learningSeverityService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<LearningSeverity> addLearningSeverity(HttpServletRequest request, @RequestBody LearningSeverity LearningSeverity) {
        return new ResponseEntity<>(learningSeverityService.save(LearningSeverity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<LearningSeverity> updateLearningSeverity(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody LearningSeverity LearningSeverity) {
        LearningSeverity.setId(id);
        return new ResponseEntity<>(learningSeverityService.update(LearningSeverity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteLearningSeverity(HttpServletRequest request, @PathVariable UUID id) {
        learningSeverityService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

