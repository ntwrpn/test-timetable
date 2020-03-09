
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
import com.java.domain.UserRoles;
import com.java.service.UserRolesService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class UserRolesController {
    @Autowired
    private UserRolesService userRolesService;

    @RequestMapping(value="/userRoles/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserRoles>> getUserRolesPage(Model model) {
        List<UserRoles> orders = userRolesService.getAll();
        return new ResponseEntity<List<UserRoles>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/userRoles/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getUserRolesKeys(Model model) {
        return new ResponseEntity(userRolesService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/userRoles/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserRoles> getUserRolesPage(Model model, @PathVariable("id") UUID id) {
        Optional<UserRoles> order = userRolesService.getById(id);
        return new ResponseEntity<UserRoles>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/userRoles/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody UserRoles obj){
     userRolesService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/userRoles/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody UserRoles obj){
     obj.setId(id);
     userRolesService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/userRoles/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteUserRoles(Model model, @PathVariable UUID id) {
        userRolesService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


