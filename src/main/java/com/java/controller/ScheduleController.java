
package com.java.controller;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Schedule;
import com.java.service.ScheduleService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping(value="/schedule/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Schedule>> getSchedulePage(Model model) {
        List<Schedule> orders = scheduleService.getAll();
        return new ResponseEntity<List<Schedule>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/schedule/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getScheduleKeys(Model model) {
        return new ResponseEntity(scheduleService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/schedule/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Schedule> getSchedulePage(Model model, @PathVariable("id") UUID id) {
        Optional<Schedule> order = scheduleService.getById(id);
        return new ResponseEntity<Schedule>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/schedule/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Schedule obj){
     scheduleService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/schedule/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody Schedule obj){
     obj.setId(id);
     scheduleService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/schedule/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteSchedule(Model model, @PathVariable UUID id) {
        scheduleService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


