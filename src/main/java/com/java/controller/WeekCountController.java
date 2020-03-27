
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.WeekCount;
import com.java.service.WeekCountService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/weekcount")
public class WeekCountController {

    @Autowired
    private WeekCountService weekCountService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<WeekCount>> getWeekCounts(HttpServletRequest request) {
        return new ResponseEntity<>(weekCountService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getWeekCountKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(weekCountService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<WeekCount> getWeekCount(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(weekCountService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<WeekCount> addWeekCount(HttpServletRequest request, @RequestBody WeekCount WeekCount) {
        return new ResponseEntity<>(weekCountService.save(WeekCount), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<WeekCount> updateWeekCount(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody WeekCount WeekCount) {
        WeekCount.setId(id);
        return new ResponseEntity<>(weekCountService.update(WeekCount), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteWeekCount(HttpServletRequest request, @PathVariable UUID id) {
        weekCountService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

