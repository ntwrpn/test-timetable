
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Lesson;
import com.java.service.LessonService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/lesson")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Lesson>> getLessons(HttpServletRequest request, @RequestParam(name = "timetableId", required = false) UUID uuid) {
        if(uuid != null){
            return new ResponseEntity<>(lessonService.findByTimetableId(uuid), HttpStatus.OK);
        }
        return new ResponseEntity<>(lessonService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getLessonKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(lessonService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Lesson> getLesson(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(lessonService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Lesson> addLesson(HttpServletRequest request, @RequestBody Lesson Lesson) {
        return new ResponseEntity<>(lessonService.save(Lesson), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Lesson> updateLesson(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Lesson Lesson) {
        Lesson.setId(id);
        return new ResponseEntity<>(lessonService.update(Lesson), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteLesson(HttpServletRequest request, @PathVariable UUID id) {
        lessonService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

