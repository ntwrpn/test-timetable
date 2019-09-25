
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
import com.javamaster.domain.LecternType;

import com.javamaster.repository.LecternTypeService;


import java.util.HashMap;

@Controller
public class LecternTypeController {

    private LecternTypeService orderService = new LecternTypeService();

    @RequestMapping(value="/lecterntype/", method=RequestMethod.GET)
    public ResponseEntity<List<LecternType>> getLecternTypePage(Model model) {
        List<LecternType> orders = orderService.getAll();
        return new ResponseEntity<List<LecternType>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/lecterntype/{id}", method=RequestMethod.GET)
    public ResponseEntity<List<LecternType>> getLecternTypePage(Model model, @PathVariable("id") int id) {
        List<LecternType> orders = orderService.getById(id);
        return new ResponseEntity<List<LecternType>>(orders, HttpStatus.OK);
    }

    @RequestMapping(value="/lecterntype/", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<Void> add(@RequestBody LecternType obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/lecterntype/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody LecternType obj){
     obj.setId(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/lecterntype/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    public ResponseEntity<Void> DeleteLecternType(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



