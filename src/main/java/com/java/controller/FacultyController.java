
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Faculty;
import com.java.service.FacultyService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Faculty>> getFacultys(HttpServletRequest request) {
        return new ResponseEntity<>(facultyService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getFacultyKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(facultyService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Faculty> getFaculty(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(facultyService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Faculty> addFaculty(HttpServletRequest request, @RequestBody Faculty Faculty) {
        return new ResponseEntity<>(facultyService.save(Faculty), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Faculty> updateFaculty(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Faculty Faculty) {
        Faculty.setId(id);
        return new ResponseEntity<>(facultyService.update(Faculty), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteFaculty(HttpServletRequest request, @PathVariable UUID id) {
        facultyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

