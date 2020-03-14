
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
import com.java.domain.Week;
import com.java.service.WeekService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class WeekController {
    @Autowired
    private WeekService weekService;

    @RequestMapping(value="/week/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Week>> getWeekPage(HttpServletRequest request, Model model) {
        List<Week> week = weekService.getAll();
        return new ResponseEntity<List<Week>>(week, HttpStatus.OK);
    }
    
    @RequestMapping(value="/week/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getWeekKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(weekService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/week/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Week> getWeekPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<Week> week = weekService.getById(id);
        return new ResponseEntity<Week>(week.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/week/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody Week obj){
        weekService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/week/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Week obj){
        obj.setId(id);
        weekService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/week/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteWeek(HttpServletRequest request, Model model, @PathVariable UUID id) {
        weekService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


