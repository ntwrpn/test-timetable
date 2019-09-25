
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
import com.javamaster.domain.Speciality;

import com.javamaster.repository.SpecialityService;


import java.util.HashMap;

@Controller
public class SpecialityController {

    private SpecialityService orderService = new SpecialityService();

    @RequestMapping(value="/speciality/", method=RequestMethod.GET)
    public ResponseEntity<List<Speciality>> getSpecialityPage(Model model) {
        List<Speciality> orders = orderService.getAll();
        return new ResponseEntity<List<Speciality>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/speciality/{id}", method=RequestMethod.GET)
    public ResponseEntity<List<Speciality>> getSpecialityPage(Model model, @PathVariable("id") int id) {
        List<Speciality> orders = orderService.getById(id);
        return new ResponseEntity<List<Speciality>>(orders, HttpStatus.OK);
    }

    @RequestMapping(value="/speciality/", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<Void> add(@RequestBody Speciality obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/speciality/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Speciality obj){
     obj.setId(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/speciality/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    public ResponseEntity<Void> DeleteSpeciality(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



