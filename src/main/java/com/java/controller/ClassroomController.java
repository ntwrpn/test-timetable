
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Classroom;
import com.java.service.ClassroomService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/classroom")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Classroom>> getClassrooms(HttpServletRequest request) {
        return new ResponseEntity<>(classroomService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getClassroomKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(classroomService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Classroom> getClassroom(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(classroomService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Classroom> addClassroom(HttpServletRequest request, @RequestBody Classroom Classroom) {
        return new ResponseEntity<>(classroomService.save(Classroom), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Classroom> updateClassroom(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Classroom Classroom) {
        Classroom.setId(id);
        return new ResponseEntity<>(classroomService.update(Classroom), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteClassroom(HttpServletRequest request, @PathVariable UUID id) {
        classroomService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

