
package com.java.controller;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import java.util.HashMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import com.java.domain.Lesson;
import com.java.service.LessonService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @RequestMapping(value="/lesson/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Lesson>> getLessonPage(HttpServletRequest request, Model model) {
        List<Lesson> lesson = lessonService.getAll();
        return new ResponseEntity<List<Lesson>>(lesson, HttpStatus.OK);
    }
    
    @RequestMapping(value="/lesson/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getLessonKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(lessonService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/lesson/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Lesson> getLessonPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<Lesson> lesson = lessonService.getById(id);
        return new ResponseEntity<Lesson>(lesson.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/lesson/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody Lesson obj){
        lessonService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/lesson/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Lesson obj){
        obj.setId(id);
        lessonService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/lesson/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteLesson(HttpServletRequest request, Model model, @PathVariable UUID id) {
        lessonService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


