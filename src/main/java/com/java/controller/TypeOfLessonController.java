
package com.java.controller;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.TypeOfLesson;
import com.java.service.TypeOfLessonService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class TypeOfLessonController {
    @Autowired
    private TypeOfLessonService typeOfLessonService;

    @RequestMapping(value="/typeoflesson/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<TypeOfLesson>> getTypeOfLessonPage(Model model) {
        List<TypeOfLesson> orders = typeOfLessonService.getAll();
        return new ResponseEntity<List<TypeOfLesson>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/typeoflesson/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getTypeOfLessonKeys(Model model) {
        return new ResponseEntity(typeOfLessonService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/typeoflesson/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<TypeOfLesson> getTypeOfLessonPage(Model model, @PathVariable("id") UUID id) {
        Optional<TypeOfLesson> order = typeOfLessonService.getById(id);
        return new ResponseEntity<TypeOfLesson>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/typeoflesson/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody TypeOfLesson obj){
     typeOfLessonService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/typeoflesson/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody TypeOfLesson obj){
     obj.setId(id);
     typeOfLessonService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/typeoflesson/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteTypeOfLesson(Model model, @PathVariable UUID id) {
        typeOfLessonService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


