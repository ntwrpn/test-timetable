
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
import com.java.domain.Schedule;

import com.java.repository.ScheduleService;


import java.util.HashMap;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class ScheduleController {

    private ScheduleService orderService = new ScheduleService();

    @RequestMapping(value="/schedule/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Schedule>> getSchedulePage(Model model) {
        List<Schedule> orders = orderService.getAll();
        return new ResponseEntity<List<Schedule>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/schedule/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getScheduleKeys(Model model) {
        return new ResponseEntity(orderService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/schedule/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Schedule> getSchedulePage(Model model, @PathVariable("id") int id) {
        List<Schedule> orders = orderService.getById(id);
        Schedule order = orders.get(0);
        return new ResponseEntity<Schedule>(order, HttpStatus.OK);
    }

    @RequestMapping(value="/schedule/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Schedule obj){
     orderService.add(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/schedule/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Schedule obj){
     obj.setId(id);
     orderService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/schedule/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteSchedule(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}



