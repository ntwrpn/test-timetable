
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
import com.java.domain.NeededLesson;
import com.java.service.NeededLessonService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class NeededLessonController {
    @Autowired
    private NeededLessonService neededlessonService;

    @RequestMapping(value="/neededlesson/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<NeededLesson>> getNeededLessonPage(HttpServletRequest request, Model model) {
        List<NeededLesson> neededlesson = neededlessonService.getAll();
        return new ResponseEntity<List<NeededLesson>>(neededlesson, HttpStatus.OK);
    }
    
    @RequestMapping(value="/neededlesson/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getNeededLessonKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(neededlessonService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/neededlesson/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<NeededLesson> getNeededLessonPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<NeededLesson> neededlesson = neededlessonService.getById(id);
        return new ResponseEntity<NeededLesson>(neededlesson.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/neededlesson/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody NeededLesson obj){
        neededlessonService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/neededlesson/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody NeededLesson obj){
        obj.setId(id);
        neededlessonService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/neededlesson/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteNeededLesson(HttpServletRequest request, Model model, @PathVariable UUID id) {
        neededlessonService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


