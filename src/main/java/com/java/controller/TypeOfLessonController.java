
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
import com.java.domain.TypeOfLesson;
import com.java.service.TypeOfLessonService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class TypeOfLessonController {
    @Autowired
    private TypeOfLessonService typeoflessonService;

    @RequestMapping(value="/typeoflesson/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<TypeOfLesson>> getTypeOfLessonPage(HttpServletRequest request, Model model) {
        List<TypeOfLesson> typeoflesson = typeoflessonService.getAll();
        return new ResponseEntity<List<TypeOfLesson>>(typeoflesson, HttpStatus.OK);
    }
    
    @RequestMapping(value="/typeoflesson/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getTypeOfLessonKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(typeoflessonService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/typeoflesson/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<TypeOfLesson> getTypeOfLessonPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<TypeOfLesson> typeoflesson = typeoflessonService.getById(id);
        return new ResponseEntity<TypeOfLesson>(typeoflesson.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/typeoflesson/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody TypeOfLesson obj){
        typeoflessonService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/typeoflesson/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody TypeOfLesson obj){
        obj.setId(id);
        typeoflessonService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/typeoflesson/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteTypeOfLesson(HttpServletRequest request, Model model, @PathVariable UUID id) {
        typeoflessonService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


