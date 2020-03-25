
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.UserRoles;
import com.java.service.UserRolesService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/userroles")
public class UserRolesController {

    @Autowired
    private UserRolesService userRolesService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserRoles>> getUserRoless(HttpServletRequest request) {
        return new ResponseEntity<>(userRolesService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getUserRolesKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(userRolesService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserRoles> getUserRoles(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(userRolesService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserRoles> addUserRoles(HttpServletRequest request, @RequestBody UserRoles UserRoles) {
        return new ResponseEntity<>(userRolesService.save(UserRoles), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserRoles> updateUserRoles(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody UserRoles UserRoles) {
        UserRoles.setId(id);
        return new ResponseEntity<>(userRolesService.update(UserRoles), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUserRoles(HttpServletRequest request, @PathVariable UUID id) {
        userRolesService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

