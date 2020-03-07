
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
import com.java.domain.WeekCount;
import com.java.service.WeekCountService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class WeekCountController {
    @Autowired
    private WeekCountService weekCountService;

    @RequestMapping(value="/weekcount/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<WeekCount>> getWeekCountPage(Model model) {
        List<WeekCount> orders = weekCountService.getAll();
        return new ResponseEntity<List<WeekCount>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/weekcount/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getWeekCountKeys(Model model) {
        return new ResponseEntity(weekCountService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/weekcount/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<WeekCount> getWeekCountPage(Model model, @PathVariable("id") UUID id) {
        Optional<WeekCount> order = weekCountService.getById(id);
        return new ResponseEntity<WeekCount>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/weekcount/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody WeekCount obj){
     weekCountService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/weekcount/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody WeekCount obj){
     obj.setId(id);
     weekCountService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/weekcount/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteWeekCount(Model model, @PathVariable UUID id) {
        weekCountService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


