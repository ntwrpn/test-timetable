
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
import com.java.domain.ClassroomType;
import com.java.service.ClassroomTypeService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class ClassroomTypeController {
    @Autowired
    private ClassroomTypeService classroomtypeService;

    @RequestMapping(value="/classroomtype/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ClassroomType>> getClassroomTypePage(HttpServletRequest request, Model model) {
        List<ClassroomType> classroomtype = classroomtypeService.getAll();
        return new ResponseEntity<List<ClassroomType>>(classroomtype, HttpStatus.OK);
    }
    
    @RequestMapping(value="/classroomtype/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getClassroomTypeKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(classroomtypeService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/classroomtype/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ClassroomType> getClassroomTypePage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<ClassroomType> classroomtype = classroomtypeService.getById(id);
        return new ResponseEntity<ClassroomType>(classroomtype.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/classroomtype/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody ClassroomType obj){
        classroomtypeService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/classroomtype/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody ClassroomType obj){
        obj.setId(id);
        classroomtypeService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/classroomtype/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteClassroomType(HttpServletRequest request, Model model, @PathVariable UUID id) {
        classroomtypeService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


