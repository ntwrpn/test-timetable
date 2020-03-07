
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
import com.java.domain.NeededLesson;
import com.java.service.NeededLessonService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class NeededLessonController {
    @Autowired
    private NeededLessonService neededLessonService;

    @RequestMapping(value="/neededlesson/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<NeededLesson>> getNeededLessonPage(Model model) {
        List<NeededLesson> orders = neededLessonService.getAll();
        return new ResponseEntity<List<NeededLesson>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/neededlesson/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getNeededLessonKeys(Model model) {
        return new ResponseEntity(neededLessonService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/neededlesson/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<NeededLesson> getNeededLessonPage(Model model, @PathVariable("id") UUID id) {
        Optional<NeededLesson> order = neededLessonService.getById(id);
        return new ResponseEntity<NeededLesson>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/neededlesson/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody NeededLesson obj){
     neededLessonService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/neededlesson/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody NeededLesson obj){
     obj.setId(id);
     neededLessonService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/neededlesson/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteNeededLesson(Model model, @PathVariable UUID id) {
        neededLessonService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


