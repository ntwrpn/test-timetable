
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
import com.javamaster.domain.Classroom;

import com.javamaster.repository.ClassroomService;


import java.util.HashMap;

@Controller
public class ClassroomController {

    private ClassroomService orderService = new ClassroomService();

    @RequestMapping(value="/classroom/", method=RequestMethod.GET)
    public ResponseEntity<List<Classroom>> getClassroomPage(Model model) {
        List<Classroom> orders = orderService.getAll();
        return new ResponseEntity<List<Classroom>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/classroom/{id}", method=RequestMethod.GET)
    public ResponseEntity<List<Classroom>> getClassroomPage(Model model, @PathVariable("id") int id) {
        List<Classroom> orders = orderService.getById(id);
        return new ResponseEntity<List<Classroom>>(orders, HttpStatus.OK);
    }

    @RequestMapping(value="/classroom/", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<Void> add(@RequestBody Classroom obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/classroom/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Classroom obj){
     obj.setId(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/classroom/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    public ResponseEntity<Void> DeleteClassroom(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



