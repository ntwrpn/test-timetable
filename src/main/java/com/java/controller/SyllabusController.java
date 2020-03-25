
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Syllabus;
import com.java.service.SyllabusService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/syllabus")
public class SyllabusController {

    @Autowired
    private SyllabusService syllabusService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Syllabus>> getSyllabuss(HttpServletRequest request) {
        return new ResponseEntity<>(syllabusService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSyllabusKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(syllabusService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Syllabus> getSyllabus(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(syllabusService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Syllabus> addSyllabus(HttpServletRequest request, @RequestBody Syllabus Syllabus) {
        return new ResponseEntity<>(syllabusService.save(Syllabus), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Syllabus> updateSyllabus(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Syllabus Syllabus) {
        Syllabus.setId(id);
        return new ResponseEntity<>(syllabusService.update(Syllabus), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteSyllabus(HttpServletRequest request, @PathVariable UUID id) {
        syllabusService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

