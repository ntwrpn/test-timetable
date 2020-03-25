
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Week;
import com.java.service.WeekService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/week")
public class WeekController {

    @Autowired
    private WeekService weekService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Week>> getWeeks(HttpServletRequest request) {
        return new ResponseEntity<>(weekService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getWeekKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(weekService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Week> getWeek(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(weekService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Week> addWeek(HttpServletRequest request, @RequestBody Week Week) {
        return new ResponseEntity<>(weekService.save(Week), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Week> updateWeek(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Week Week) {
        Week.setId(id);
        return new ResponseEntity<>(weekService.update(Week), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteWeek(HttpServletRequest request, @PathVariable UUID id) {
        weekService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

