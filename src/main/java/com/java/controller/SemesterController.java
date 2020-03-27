
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Semester;
import com.java.service.SemesterService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/semester")
public class SemesterController {

    @Autowired
    private SemesterService semesterService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Semester>> getSemesters(HttpServletRequest request) {
        return new ResponseEntity<>(semesterService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSemesterKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(semesterService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Semester> getSemester(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(semesterService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Semester> addSemester(HttpServletRequest request, @RequestBody Semester Semester) {
        return new ResponseEntity<>(semesterService.save(Semester), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Semester> updateSemester(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Semester Semester) {
        Semester.setId(id);
        return new ResponseEntity<>(semesterService.update(Semester), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteSemester(HttpServletRequest request, @PathVariable UUID id) {
        semesterService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

