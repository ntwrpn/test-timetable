
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Role;
import com.java.service.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Role>> getRoles(HttpServletRequest request) {
        return new ResponseEntity<>(roleService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getRoleKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(roleService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Role> getRole(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(roleService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Role> addRole(HttpServletRequest request, @RequestBody Role Role) {
        return new ResponseEntity<>(roleService.save(Role), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Role> updateRole(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Role Role) {
        Role.setId(id);
        return new ResponseEntity<>(roleService.update(Role), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteRole(HttpServletRequest request, @PathVariable UUID id) {
        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

