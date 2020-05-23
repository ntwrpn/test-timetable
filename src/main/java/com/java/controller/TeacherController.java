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
import com.java.domain.Teacher;
import com.java.service.TeacherService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ExceptionHandler
    public ResponseEntity<Response> handleException(ConstraintViolationException exception) {
        return new ResponseEntity<>(ExceptionResponceCreator.createResponse(exception.getConstraintViolations()), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Teacher>> getTeachers(HttpServletRequest request, @RequestParam(name = "lecternId", required = false) UUID uuid) {
        if (uuid != null) {
            return new ResponseEntity<>(teacherService.findByLectern(uuid), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(teacherService.getAll(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getTeacherKeys(HttpServletRequest request) {
        return new ResponseEntity(teacherService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Teacher> getTeacher(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(teacherService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Teacher> addTeacher(HttpServletRequest request, @RequestParam(name = "lecternId") UUID lectern_id, @Valid @RequestBody Teacher teacher) {
        return new ResponseEntity<>(teacherService.save(teacher, lectern_id), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Teacher> updateTeacher(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Teacher teacher) {
        teacher.setId(id);
        return new ResponseEntity<>(teacherService.update(teacher), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteTeacher(HttpServletRequest request, @PathVariable UUID id) {
        teacherService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
