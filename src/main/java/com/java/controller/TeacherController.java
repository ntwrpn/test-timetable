
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
import com.java.domain.Teacher;
import com.java.service.TeacherService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value="/teacher/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Teacher>> getTeacherPage(Model model) {
        List<Teacher> orders = teacherService.getAll();
        return new ResponseEntity<List<Teacher>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/teacher/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getTeacherKeys(Model model) {
        return new ResponseEntity(teacherService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/teacher/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Teacher> getTeacherPage(Model model, @PathVariable("id") UUID id) {
        Optional<Teacher> order = teacherService.getById(id);
        return new ResponseEntity<Teacher>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/teacher/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Teacher obj){
     teacherService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/teacher/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody Teacher obj){
     obj.setId(id);
     teacherService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/teacher/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteTeacher(Model model, @PathVariable UUID id) {
        teacherService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


