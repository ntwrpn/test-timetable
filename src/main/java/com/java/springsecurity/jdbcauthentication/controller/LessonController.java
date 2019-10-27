
package com.java.springsecurity.jdbcauthentication.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import com.java.domain.Lesson;

import com.java.repository.LessonService;


import java.util.HashMap;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class LessonController {

    private LessonService orderService = new LessonService();

    @RequestMapping(value="/lesson/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Lesson>> getLessonPage(Model model) {
        List<Lesson> orders = orderService.getAll();
        return new ResponseEntity<List<Lesson>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/lesson/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Lesson> getCorpsKeys(Model model) {
        Lesson order = new Lesson();
        return new ResponseEntity<Lesson>(order, HttpStatus.OK);
    }
    
    @RequestMapping(value="/lesson/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Lesson> getLessonPage(Model model, @PathVariable("id") int id) {
        List<Lesson> orders = orderService.getById(id);
        Lesson order = orders.get(0);
        return new ResponseEntity<Lesson>(order, HttpStatus.OK);
    }

    @RequestMapping(value="/lesson/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Lesson obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/lesson/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Lesson obj){
     obj.setId(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/lesson/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteLesson(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



