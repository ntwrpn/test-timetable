
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
import com.java.domain.WeekCount;
import com.java.service.WeekCountService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class WeekCountController {
    @Autowired
    private WeekCountService weekcountService;

    @RequestMapping(value="/weekcount/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<WeekCount>> getWeekCountPage(HttpServletRequest request, Model model) {
        List<WeekCount> weekcount = weekcountService.getAll();
        return new ResponseEntity<List<WeekCount>>(weekcount, HttpStatus.OK);
    }
    
    @RequestMapping(value="/weekcount/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getWeekCountKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(weekcountService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/weekcount/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<WeekCount> getWeekCountPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<WeekCount> weekcount = weekcountService.getById(id);
        return new ResponseEntity<WeekCount>(weekcount.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/weekcount/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody WeekCount obj){
        weekcountService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/weekcount/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody WeekCount obj){
        obj.setId(id);
        weekcountService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/weekcount/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteWeekCount(HttpServletRequest request, Model model, @PathVariable UUID id) {
        weekcountService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


