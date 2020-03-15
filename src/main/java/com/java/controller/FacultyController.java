
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
import com.java.domain.Faculty;
import com.java.service.FacultyService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @RequestMapping(value="/faculty/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Faculty>> getFacultyPage(HttpServletRequest request, Model model) {
        List<Faculty> faculty = facultyService.getAll();
        return new ResponseEntity<List<Faculty>>(faculty, HttpStatus.OK);
    }
    
    @RequestMapping(value="/faculty/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getFacultyKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(facultyService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/faculty/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Faculty> getFacultyPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<Faculty> faculty = facultyService.getById(id);
        return new ResponseEntity<Faculty>(faculty.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/faculty/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody Faculty obj){
        facultyService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/faculty/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Faculty obj){
        obj.setId(id);
        facultyService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/faculty/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteFaculty(HttpServletRequest request, Model model, @PathVariable UUID id) {
        facultyService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}

