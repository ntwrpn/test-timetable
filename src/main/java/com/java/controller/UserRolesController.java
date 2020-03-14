
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
import com.java.domain.UserRoles;
import com.java.service.UserRolesService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class UserRolesController {
    @Autowired
    private UserRolesService userrolesService;

    @RequestMapping(value="/userRoles/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserRoles>> getUserRolesPage(HttpServletRequest request, Model model) {
        List<UserRoles> userroles = userrolesService.getAll();
        return new ResponseEntity<List<UserRoles>>(userroles, HttpStatus.OK);
    }
    
    @RequestMapping(value="/userRoles/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getUserRolesKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(userrolesService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/userRoles/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserRoles> getUserRolesPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<UserRoles> userroles = userrolesService.getById(id);
        return new ResponseEntity<UserRoles>(userroles.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/userRoles/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody UserRoles obj){
        userrolesService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/userRoles/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody UserRoles obj){
        obj.setId(id);
        userrolesService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/userRoles/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteUserRoles(HttpServletRequest request, Model model, @PathVariable UUID id) {
        userrolesService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


