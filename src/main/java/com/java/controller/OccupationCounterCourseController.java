
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
import com.java.domain.OccupationCounterCourse;
import com.java.service.OccupationCounterCourseService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class OccupationCounterCourseController {
    @Autowired
    private OccupationCounterCourseService occupationCounterCourseService;

    @RequestMapping(value="/occupationcountercourse/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OccupationCounterCourse>> getOccupationCounterCoursePage(Model model) {
        List<OccupationCounterCourse> orders = occupationCounterCourseService.getAll();
        return new ResponseEntity<List<OccupationCounterCourse>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/occupationcountercourse/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getOccupationCounterCourseKeys(Model model) {
        return new ResponseEntity(occupationCounterCourseService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/occupationcountercourse/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<OccupationCounterCourse> getOccupationCounterCoursePage(Model model, @PathVariable("id") UUID id) {
        Optional<OccupationCounterCourse> order = occupationCounterCourseService.getById(id);
        return new ResponseEntity<OccupationCounterCourse>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/occupationcountercourse/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody OccupationCounterCourse obj){
     occupationCounterCourseService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/occupationcountercourse/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody OccupationCounterCourse obj){
     obj.setId(id);
     occupationCounterCourseService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/occupationcountercourse/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteOccupationCounterCourse(Model model, @PathVariable UUID id) {
        occupationCounterCourseService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


