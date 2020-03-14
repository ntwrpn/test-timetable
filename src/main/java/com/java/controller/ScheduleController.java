
package com.java.controller;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import java.util.HashMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import com.java.domain.Schedule;
import com.java.service.ScheduleService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping(value="/schedule/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Schedule>> getSchedulePage(HttpServletRequest request, Model model) {
        List<Schedule> schedule = scheduleService.getAll();
        return new ResponseEntity<List<Schedule>>(schedule, HttpStatus.OK);
    }
    
    @RequestMapping(value="/schedule/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getScheduleKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(scheduleService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/schedule/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Schedule> getSchedulePage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<Schedule> schedule = scheduleService.getById(id);
        return new ResponseEntity<Schedule>(schedule.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/schedule/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody Schedule obj){
        scheduleService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/schedule/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Schedule obj){
        obj.setId(id);
        scheduleService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/schedule/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteSchedule(HttpServletRequest request, Model model, @PathVariable UUID id) {
        scheduleService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


