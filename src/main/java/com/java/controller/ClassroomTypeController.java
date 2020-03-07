
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
import com.java.domain.ClassroomType;
import com.java.service.ClassroomTypeService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class ClassroomTypeController {
    @Autowired
    private ClassroomTypeService classroomTypeService;

    @RequestMapping(value="/classroomtype/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ClassroomType>> getClassroomTypePage(Model model) {
        List<ClassroomType> orders = classroomTypeService.getAll();
        return new ResponseEntity<List<ClassroomType>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/classroomtype/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getClassroomTypeKeys(Model model) {
        return new ResponseEntity(classroomTypeService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/classroomtype/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ClassroomType> getClassroomTypePage(Model model, @PathVariable("id") UUID id) {
        Optional<ClassroomType> order = classroomTypeService.getById(id);
        return new ResponseEntity<ClassroomType>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/classroomtype/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody ClassroomType obj){
     classroomTypeService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/classroomtype/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody ClassroomType obj){
     obj.setId(id);
     classroomTypeService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/classroomtype/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteClassroomType(Model model, @PathVariable UUID id) {
        classroomTypeService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


