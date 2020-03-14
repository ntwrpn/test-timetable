
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
import com.java.domain.Course;
import com.java.service.CourseService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping(value="/course/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Course>> getCoursePage(HttpServletRequest request, Model model) {
        List<Course> course = courseService.getAll();
        return new ResponseEntity<List<Course>>(course, HttpStatus.OK);
    }
    
    @RequestMapping(value="/course/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getCourseKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(courseService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/course/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Course> getCoursePage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<Course> course = courseService.getById(id);
        return new ResponseEntity<Course>(course.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/course/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody Course obj){
        courseService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/course/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Course obj){
        obj.setId(id);
        courseService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/course/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteCourse(HttpServletRequest request, Model model, @PathVariable UUID id) {
        courseService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


