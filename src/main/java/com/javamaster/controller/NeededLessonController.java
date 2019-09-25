
package com.javamaster.controller;

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
import com.google.gson.Gson;
import com.javamaster.domain.NeededLesson;

import com.javamaster.repository.NeededLessonService;


import java.util.HashMap;

@Controller
public class NeededLessonController {

    private NeededLessonService orderService = new NeededLessonService();

    @RequestMapping(value="/neededlesson/", method=RequestMethod.GET)
    public ResponseEntity<List<NeededLesson>> getNeededLessonPage(Model model) {
        List<NeededLesson> orders = orderService.getAll();
        return new ResponseEntity<List<NeededLesson>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/neededlesson/{id}", method=RequestMethod.GET)
    public ResponseEntity<List<NeededLesson>> getNeededLessonPage(Model model, @PathVariable("id") int id) {
        List<NeededLesson> orders = orderService.getById(id);
        return new ResponseEntity<List<NeededLesson>>(orders, HttpStatus.OK);
    }

    @RequestMapping(value="/neededlesson/", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<Void> add(@RequestBody NeededLesson obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/neededlesson/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody NeededLesson obj){
     obj.setId(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/neededlesson/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    public ResponseEntity<Void> DeleteNeededLesson(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



