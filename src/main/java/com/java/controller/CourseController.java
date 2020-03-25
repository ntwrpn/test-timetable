
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Course;
import com.java.service.CourseService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Course>> getCourses(HttpServletRequest request) {
        return new ResponseEntity<>(courseService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getCourseKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(courseService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Course> getCourse(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(courseService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Course> addCourse(HttpServletRequest request, @RequestBody Course Course) {
        return new ResponseEntity<>(courseService.save(Course), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Course> updateCourse(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Course Course) {
        Course.setId(id);
        return new ResponseEntity<>(courseService.update(Course), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteCourse(HttpServletRequest request, @PathVariable UUID id) {
        courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

