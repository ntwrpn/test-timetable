
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Teacher>> getTeachers(HttpServletRequest request, @RequestParam(name = "lecternId", required = false) UUID uuid) {
        if(uuid != null){
            return new ResponseEntity<>(teacherService.findByLectern(uuid), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(teacherService.getAll(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getTeacherKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(teacherService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Teacher> getTeacher(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(teacherService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Teacher> addTeacher(HttpServletRequest request, @RequestBody Teacher Teacher) {
        return new ResponseEntity<>(teacherService.save(Teacher), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Teacher> updateTeacher(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Teacher Teacher) {
        Teacher.setId(id);
        return new ResponseEntity<>(teacherService.update(Teacher), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteTeacher(HttpServletRequest request, @PathVariable UUID id) {
        teacherService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

