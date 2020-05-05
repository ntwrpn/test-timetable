
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Timetable;
import com.java.service.TimetableService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/timetable")
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Timetable>> getTimetables(HttpServletRequest request) {
        return new ResponseEntity<>(timetableService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getTimetableKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(timetableService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Timetable> getTimetable(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(timetableService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Timetable> addTimetable(HttpServletRequest request, @RequestBody Timetable Timetable) {
        return new ResponseEntity<>(timetableService.save(Timetable), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Timetable> updateTimetable(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Timetable Timetable) {
        Timetable newtime = timetableService.getById(id).get();
        newtime.setFlow(Timetable.getFlow());
        newtime.setStatus(Timetable.getStatus());
        newtime.setRegisterNumber(Timetable.getRegisterNumber());
        return new ResponseEntity<>(timetableService.update(newtime), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteTimetable(HttpServletRequest request, @PathVariable UUID id) {
        timetableService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

