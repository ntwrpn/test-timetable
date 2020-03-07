
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
import com.java.domain.Week;
import com.java.service.WeekService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class WeekController {
    @Autowired
    private WeekService weekService;

    @RequestMapping(value="/week/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Week>> getWeekPage(Model model) {
        List<Week> orders = weekService.getAll();
        return new ResponseEntity<List<Week>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/week/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getWeekKeys(Model model) {
        return new ResponseEntity(weekService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/week/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Week> getWeekPage(Model model, @PathVariable("id") UUID id) {
        Optional<Week> order = weekService.getById(id);
        return new ResponseEntity<Week>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/week/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Week obj){
     weekService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/week/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody Week obj){
     obj.setId(id);
     weekService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/week/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteWeek(Model model, @PathVariable UUID id) {
        weekService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


