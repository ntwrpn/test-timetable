
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.TypeOfLesson;
import com.java.service.TypeOfLessonService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/typeoflesson")
public class TypeOfLessonController {

    @Autowired
    private TypeOfLessonService typeOfLessonService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<TypeOfLesson>> getTypeOfLessons(HttpServletRequest request) {
        return new ResponseEntity<>(typeOfLessonService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getTypeOfLessonKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(typeOfLessonService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<TypeOfLesson> getTypeOfLesson(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(typeOfLessonService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<TypeOfLesson> addTypeOfLesson(HttpServletRequest request, @RequestBody TypeOfLesson TypeOfLesson) {
        return new ResponseEntity<>(typeOfLessonService.save(TypeOfLesson), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<TypeOfLesson> updateTypeOfLesson(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody TypeOfLesson TypeOfLesson) {
        TypeOfLesson.setId(id);
        return new ResponseEntity<>(typeOfLessonService.update(TypeOfLesson), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteTypeOfLesson(HttpServletRequest request, @PathVariable UUID id) {
        typeOfLessonService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

