
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
import com.java.domain.Teacher;
import com.java.service.TeacherService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value="/teacher/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Teacher>> getTeacherPage(HttpServletRequest request, Model model) {
        List<Teacher> teacher = teacherService.getAll();
        return new ResponseEntity<List<Teacher>>(teacher, HttpStatus.OK);
    }
    
    @RequestMapping(value="/teacher/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getTeacherKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(teacherService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/teacher/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Teacher> getTeacherPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<Teacher> teacher = teacherService.getById(id);
        return new ResponseEntity<Teacher>(teacher.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/teacher/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody Teacher obj){
        teacherService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/teacher/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Teacher obj){
        obj.setId(id);
        teacherService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/teacher/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteTeacher(HttpServletRequest request, Model model, @PathVariable UUID id) {
        teacherService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


