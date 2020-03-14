
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
import com.java.domain.LecternType;
import com.java.service.LecternTypeService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class LecternTypeController {
    @Autowired
    private LecternTypeService lecterntypeService;

    @RequestMapping(value="/lecterntype/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<LecternType>> getLecternTypePage(HttpServletRequest request, Model model) {
        List<LecternType> lecterntype = lecterntypeService.getAll();
        return new ResponseEntity<List<LecternType>>(lecterntype, HttpStatus.OK);
    }
    
    @RequestMapping(value="/lecterntype/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getLecternTypeKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(lecterntypeService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/lecterntype/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<LecternType> getLecternTypePage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<LecternType> lecterntype = lecterntypeService.getById(id);
        return new ResponseEntity<LecternType>(lecterntype.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/lecterntype/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody LecternType obj){
        lecterntypeService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/lecterntype/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody LecternType obj){
        obj.setId(id);
        lecterntypeService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/lecterntype/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteLecternType(HttpServletRequest request, Model model, @PathVariable UUID id) {
        lecterntypeService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


