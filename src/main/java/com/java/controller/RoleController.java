
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
import com.java.domain.Role;
import com.java.service.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value="/role/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Role>> getRolePage(HttpServletRequest request, Model model) {
        List<Role> role = roleService.getAll();
        return new ResponseEntity<List<Role>>(role, HttpStatus.OK);
    }
    
    @RequestMapping(value="/role/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getRoleKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(roleService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/role/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Role> getRolePage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<Role> role = roleService.getById(id);
        return new ResponseEntity<Role>(role.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/role/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody Role obj){
        roleService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/role/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Role obj){
        obj.setId(id);
        roleService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/role/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteRole(HttpServletRequest request, Model model, @PathVariable UUID id) {
        roleService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}

