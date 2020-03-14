
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
import com.java.domain.OccupationCounterCourse;
import com.java.service.OccupationCounterCourseService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class OccupationCounterCourseController {
    @Autowired
    private OccupationCounterCourseService occupationcountercourseService;

    @RequestMapping(value="/occupationcountercourse/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OccupationCounterCourse>> getOccupationCounterCoursePage(HttpServletRequest request, Model model) {
        List<OccupationCounterCourse> occupationcountercourse = occupationcountercourseService.getAll();
        return new ResponseEntity<List<OccupationCounterCourse>>(occupationcountercourse, HttpStatus.OK);
    }
    
    @RequestMapping(value="/occupationcountercourse/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getOccupationCounterCourseKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(occupationcountercourseService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/occupationcountercourse/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<OccupationCounterCourse> getOccupationCounterCoursePage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<OccupationCounterCourse> occupationcountercourse = occupationcountercourseService.getById(id);
        return new ResponseEntity<OccupationCounterCourse>(occupationcountercourse.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/occupationcountercourse/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody OccupationCounterCourse obj){
        occupationcountercourseService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/occupationcountercourse/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody OccupationCounterCourse obj){
        obj.setId(id);
        occupationcountercourseService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/occupationcountercourse/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteOccupationCounterCourse(HttpServletRequest request, Model model, @PathVariable UUID id) {
        occupationcountercourseService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


