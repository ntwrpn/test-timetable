package com.java.controller;

import com.java.domain.Schedule;
import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Year;
import com.java.service.ScheduleService;
import com.java.service.YearService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/year")
public class YearController {

    @Autowired
    private YearService yearService;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Year>> getYears(HttpServletRequest request) {
        return new ResponseEntity<>(yearService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getYearKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(yearService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Year> getYear(HttpServletRequest request, @PathVariable("id") UUID id) {
            return new ResponseEntity<>(yearService.getById(id).get(), HttpStatus.OK);

    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Year> addYear(HttpServletRequest request, @RequestBody Year Year) {
        Year year = yearService.save(Year);
        year.setSchedule(Year.getSchedule());
        return new ResponseEntity<>(yearService.save(year) , HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Year> updateYear(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Year Year,
            @RequestParam(required = false) UUID SheduleId) {
        if (SheduleId != null) {
            Schedule schedule = scheduleService.getById(SheduleId).get();
            List<Schedule> scheduleList = yearService.getById(id).get().getSchedule();
            scheduleList.add(schedule);
            yearService.getById(id).get().setSchedule(scheduleList);
            return new ResponseEntity<>(yearService.getById(id).get(), HttpStatus.OK);
        } else {
            Year.setId(id);
            return new ResponseEntity<>(yearService.update(Year), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteYear(HttpServletRequest request, @PathVariable UUID id) {
        yearService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
