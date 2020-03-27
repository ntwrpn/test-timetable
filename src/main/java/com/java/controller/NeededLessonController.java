
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.NeededLesson;
import com.java.service.NeededLessonService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/neededlesson")
public class NeededLessonController {

    @Autowired
    private NeededLessonService neededLessonService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<NeededLesson>> getNeededLessons(HttpServletRequest request) {
        return new ResponseEntity<>(neededLessonService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getNeededLessonKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(neededLessonService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<NeededLesson> getNeededLesson(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(neededLessonService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<NeededLesson> addNeededLesson(HttpServletRequest request, @RequestBody NeededLesson NeededLesson) {
        return new ResponseEntity<>(neededLessonService.save(NeededLesson), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<NeededLesson> updateNeededLesson(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody NeededLesson NeededLesson) {
        NeededLesson.setId(id);
        return new ResponseEntity<>(neededLessonService.update(NeededLesson), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteNeededLesson(HttpServletRequest request, @PathVariable UUID id) {
        neededLessonService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

