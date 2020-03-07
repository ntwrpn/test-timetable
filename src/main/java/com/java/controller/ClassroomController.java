
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
import com.java.domain.Classroom;
import com.java.service.ClassroomService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;

    @RequestMapping(value="/classroom/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Classroom>> getClassroomPage(Model model) {
        List<Classroom> orders = classroomService.getAll();
        return new ResponseEntity<List<Classroom>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/classroom/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getClassroomKeys(Model model) {
        return new ResponseEntity(classroomService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/classroom/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Classroom> getClassroomPage(Model model, @PathVariable("id") UUID id) {
        Optional<Classroom> order = classroomService.getById(id);
        return new ResponseEntity<Classroom>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/classroom/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Classroom obj){
     classroomService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/classroom/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody Classroom obj){
     obj.setId(id);
     classroomService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/classroom/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteClassroom(Model model, @PathVariable UUID id) {
        classroomService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


