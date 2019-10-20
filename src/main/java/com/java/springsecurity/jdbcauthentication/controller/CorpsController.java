
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
import com.java.domain.Corps;

import com.java.repository.CorpsService;


import java.util.HashMap;

@Controller
public class CorpsController {

    private CorpsService orderService = new CorpsService();

    @RequestMapping(value="/corps/", method=RequestMethod.GET)
    public ResponseEntity<List<Corps>> getCorpsPage(Model model) {
        List<Corps> orders = orderService.getAll();
        return new ResponseEntity<List<Corps>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/corps/", method=RequestMethod.OPTIONS)
    public ResponseEntity<Corps> getCorpsKeys(Model model) {
        Corps order = new Corps();
        return new ResponseEntity<Corps>(order, HttpStatus.OK);
    }
    
    @RequestMapping(value="/corps/{id}", method=RequestMethod.GET)
    public ResponseEntity<Corps> getCorpsPage(Model model, @PathVariable("id") int id) {
        List<Corps> orders = orderService.getById(id);
        Corps order = orders.get(0);
        return new ResponseEntity<Corps>(order, HttpStatus.OK);
    }

    @RequestMapping(value="/corps/", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<Void> add(@RequestBody Corps obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/corps/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Corps obj){
     obj.setId(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/corps/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    public ResponseEntity<Void> DeleteCorps(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



