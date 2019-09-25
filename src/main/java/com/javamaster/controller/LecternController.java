
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
import com.javamaster.domain.Lectern;

import com.javamaster.repository.LecternService;


import java.util.HashMap;

@Controller
public class LecternController {

    private LecternService orderService = new LecternService();

    @RequestMapping(value="/lectern/", method=RequestMethod.GET)
    public ResponseEntity<List<Lectern>> getLecternPage(Model model) {
        List<Lectern> orders = orderService.getAll();
        return new ResponseEntity<List<Lectern>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/lectern/{id}", method=RequestMethod.GET)
    public ResponseEntity<List<Lectern>> getLecternPage(Model model, @PathVariable("id") int id) {
        List<Lectern> orders = orderService.getById(id);
        return new ResponseEntity<List<Lectern>>(orders, HttpStatus.OK);
    }

    @RequestMapping(value="/lectern/", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<Void> add(@RequestBody Lectern obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/lectern/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Lectern obj){
     obj.setId(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/lectern/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    public ResponseEntity<Void> DeleteLectern(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



