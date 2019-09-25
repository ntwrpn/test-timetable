
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
import com.javamaster.domain.LearningSeverity;

import com.javamaster.repository.LearningSeverityService;


import java.util.HashMap;

@Controller
public class LearningSeverityController {

    private LearningSeverityService orderService = new LearningSeverityService();

    @RequestMapping(value="/learningseverity/", method=RequestMethod.GET)
    public ResponseEntity<List<LearningSeverity>> getLearningSeverityPage(Model model) {
        List<LearningSeverity> orders = orderService.getAll();
        return new ResponseEntity<List<LearningSeverity>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/learningseverity/{id}", method=RequestMethod.GET)
    public ResponseEntity<List<LearningSeverity>> getLearningSeverityPage(Model model, @PathVariable("id") int id) {
        List<LearningSeverity> orders = orderService.getById(id);
        return new ResponseEntity<List<LearningSeverity>>(orders, HttpStatus.OK);
    }

    @RequestMapping(value="/learningseverity/", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<Void> add(@RequestBody LearningSeverity obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/learningseverity/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody LearningSeverity obj){
     obj.setId(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/learningseverity/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    public ResponseEntity<Void> DeleteLearningSeverity(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



