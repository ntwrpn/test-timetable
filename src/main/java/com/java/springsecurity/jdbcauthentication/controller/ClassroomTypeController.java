
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
import com.java.domain.ClassroomType;

import com.java.repository.ClassroomTypeService;


import java.util.HashMap;

@Controller
public class ClassroomTypeController {

    private ClassroomTypeService orderService = new ClassroomTypeService();

    @RequestMapping(value="/classroomtype/", method=RequestMethod.GET)
    public ResponseEntity<List<ClassroomType>> getClassroomTypePage(Model model) {
        List<ClassroomType> orders = orderService.getAll();
        return new ResponseEntity<List<ClassroomType>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/classroomtype/", method=RequestMethod.OPTIONS)
    public ResponseEntity<ClassroomType> getCorpsKeys(Model model) {
        ClassroomType order = new ClassroomType();
        return new ResponseEntity<ClassroomType>(order, HttpStatus.OK);
    }
    
    @RequestMapping(value="/classroomtype/{id}", method=RequestMethod.GET)
    public ResponseEntity<ClassroomType> getClassroomTypePage(Model model, @PathVariable("id") int id) {
        List<ClassroomType> orders = orderService.getById(id);
        ClassroomType order = orders.get(0);
        return new ResponseEntity<ClassroomType>(order, HttpStatus.OK);
    }

    @RequestMapping(value="/classroomtype/", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<Void> add(@RequestBody ClassroomType obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/classroomtype/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody ClassroomType obj){
     obj.setId(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/classroomtype/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    public ResponseEntity<Void> DeleteClassroomType(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



