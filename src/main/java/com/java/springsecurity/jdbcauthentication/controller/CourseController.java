
package com.java.springsecurity.jdbcauthentication.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import com.java.domain.Course;

import com.java.repository.CourseService;


import java.util.HashMap;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class CourseController {

    private CourseService orderService = new CourseService();

    @RequestMapping(value="/course/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Course>> getCoursePage(Model model) {
        List<Course> orders = orderService.getAll();
        return new ResponseEntity<List<Course>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/course/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getCourseKeys(Model model) {
        return new ResponseEntity(orderService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/course/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Course> getCoursePage(Model model, @PathVariable("id") int id) {
        List<Course> orders = orderService.getById(id);
        Course order = orders.get(0);
        return new ResponseEntity<Course>(order, HttpStatus.OK);
    }

    @RequestMapping(value="/course/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Course obj){
     orderService.add(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/course/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Course obj){
     obj.setId(id);
     orderService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/course/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteCourse(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


