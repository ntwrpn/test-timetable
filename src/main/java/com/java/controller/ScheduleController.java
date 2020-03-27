
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Schedule;
import com.java.service.ScheduleService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Schedule>> getSchedules(HttpServletRequest request) {
        return new ResponseEntity<>(scheduleService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getScheduleKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(scheduleService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Schedule> getSchedule(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(scheduleService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Schedule> addSchedule(HttpServletRequest request, @RequestBody Schedule Schedule) {
        return new ResponseEntity<>(scheduleService.save(Schedule), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Schedule> updateSchedule(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Schedule Schedule) {
        Schedule.setId(id);
        return new ResponseEntity<>(scheduleService.update(Schedule), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteSchedule(HttpServletRequest request, @PathVariable UUID id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

