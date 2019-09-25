
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
import com.javamaster.domain.Subject;

import com.javamaster.repository.SubjectService;


import java.util.HashMap;

@Controller
public class SubjectController {

    private SubjectService orderService = new SubjectService();

    @RequestMapping(value="/subject/", method=RequestMethod.GET)
    public ResponseEntity<List<Subject>> getSubjectPage(Model model) {
        List<Subject> orders = orderService.getAll();
        return new ResponseEntity<List<Subject>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/subject/{id}", method=RequestMethod.GET)
    public ResponseEntity<List<Subject>> getSubjectPage(Model model, @PathVariable("id") int id) {
        List<Subject> orders = orderService.getById(id);
        return new ResponseEntity<List<Subject>>(orders, HttpStatus.OK);
    }

    @RequestMapping(value="/subject/", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<Void> add(@RequestBody Subject obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/subject/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Subject obj){
     obj.setId(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/subject/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    public ResponseEntity<Void> DeleteSubject(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



