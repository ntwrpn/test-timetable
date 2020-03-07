
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
import com.java.domain.Course;
import com.java.service.CourseService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping(value="/course/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Course>> getCoursePage(Model model) {
        List<Course> orders = courseService.getAll();
        return new ResponseEntity<List<Course>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/course/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getCourseKeys(Model model) {
        return new ResponseEntity(courseService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/course/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Course> getCoursePage(Model model, @PathVariable("id") UUID id) {
        Optional<Course> order = courseService.getById(id);
        return new ResponseEntity<Course>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/course/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Course obj){
     courseService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/course/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody Course obj){
     obj.setId(id);
     courseService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/course/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteCourse(Model model, @PathVariable UUID id) {
        courseService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


