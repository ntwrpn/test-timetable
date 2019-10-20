
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
import com.java.domain.TypeOfLesson;

import com.java.repository.TypeOfLessonService;


import java.util.HashMap;

@Controller
public class TypeOfLessonController {

    private TypeOfLessonService orderService = new TypeOfLessonService();

    @RequestMapping(value="/typeoflesson/", method=RequestMethod.GET)
    public ResponseEntity<List<TypeOfLesson>> getTypeOfLessonPage(Model model) {
        List<TypeOfLesson> orders = orderService.getAll();
        return new ResponseEntity<List<TypeOfLesson>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/typeoflesson/", method=RequestMethod.OPTIONS)
    public ResponseEntity<TypeOfLesson> getCorpsKeys(Model model) {
        TypeOfLesson order = new TypeOfLesson();
        return new ResponseEntity<TypeOfLesson>(order, HttpStatus.OK);
    }
    
    @RequestMapping(value="/typeoflesson/{id}", method=RequestMethod.GET)
    public ResponseEntity<TypeOfLesson> getTypeOfLessonPage(Model model, @PathVariable("id") int id) {
        List<TypeOfLesson> orders = orderService.getById(id);
        TypeOfLesson order = orders.get(0);
        return new ResponseEntity<TypeOfLesson>(order, HttpStatus.OK);
    }

    @RequestMapping(value="/typeoflesson/", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<Void> add(@RequestBody TypeOfLesson obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/typeoflesson/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody TypeOfLesson obj){
     obj.setId(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/typeoflesson/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    public ResponseEntity<Void> DeleteTypeOfLesson(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



