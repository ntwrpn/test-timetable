
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.OccupationCounterCourse;
import com.java.service.OccupationCounterCourseService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/OccupationCounterCourse")
public class OccupationCounterCourseController {

    @Autowired
    private OccupationCounterCourseService occupationCounterCourseService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OccupationCounterCourse>> getOccupationCounterCourses(HttpServletRequest request) {
        return new ResponseEntity<>(occupationCounterCourseService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getOccupationCounterCourseKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(occupationCounterCourseService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<OccupationCounterCourse> getOccupationCounterCourse(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(occupationCounterCourseService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<OccupationCounterCourse> addOccupationCounterCourse(HttpServletRequest request, @RequestBody OccupationCounterCourse OccupationCounterCourse) {
        return new ResponseEntity<>(occupationCounterCourseService.save(OccupationCounterCourse), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<OccupationCounterCourse> updateOccupationCounterCourse(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody OccupationCounterCourse OccupationCounterCourse) {
        OccupationCounterCourse.setId(id);
        return new ResponseEntity<>(occupationCounterCourseService.update(OccupationCounterCourse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteOccupationCounterCourse(HttpServletRequest request, @PathVariable UUID id) {
        occupationCounterCourseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

