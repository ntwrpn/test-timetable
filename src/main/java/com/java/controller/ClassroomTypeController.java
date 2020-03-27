
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.ClassroomType;
import com.java.service.ClassroomTypeService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/classroomtype")
public class ClassroomTypeController {

    @Autowired
    private ClassroomTypeService classroomTypeService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ClassroomType>> getClassroomTypes(HttpServletRequest request) {
        return new ResponseEntity<>(classroomTypeService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getClassroomTypeKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(classroomTypeService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ClassroomType> getClassroomType(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(classroomTypeService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ClassroomType> addClassroomType(HttpServletRequest request, @RequestBody ClassroomType ClassroomType) {
        return new ResponseEntity<>(classroomTypeService.save(ClassroomType), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ClassroomType> updateClassroomType(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody ClassroomType ClassroomType) {
        ClassroomType.setId(id);
        return new ResponseEntity<>(classroomTypeService.update(ClassroomType), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteClassroomType(HttpServletRequest request, @PathVariable UUID id) {
        classroomTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

