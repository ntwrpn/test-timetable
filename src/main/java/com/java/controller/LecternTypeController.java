
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
import com.java.domain.LecternType;
import com.java.service.LecternTypeService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class LecternTypeController {
    @Autowired
    private LecternTypeService lecternTypeService;

    @RequestMapping(value="/lecterntype/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<LecternType>> getLecternTypePage(Model model) {
        List<LecternType> orders = lecternTypeService.getAll();
        return new ResponseEntity<List<LecternType>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/lecterntype/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getLecternTypeKeys(Model model) {
        return new ResponseEntity(lecternTypeService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/lecterntype/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<LecternType> getLecternTypePage(Model model, @PathVariable("id") UUID id) {
        Optional<LecternType> order = lecternTypeService.getById(id);
        return new ResponseEntity<LecternType>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/lecterntype/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody LecternType obj){
     lecternTypeService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/lecterntype/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody LecternType obj){
     obj.setId(id);
     lecternTypeService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/lecterntype/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteLecternType(Model model, @PathVariable UUID id) {
        lecternTypeService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


