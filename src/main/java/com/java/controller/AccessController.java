
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Access;
import com.java.service.AccessService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/access")
public class AccessController {

    @Autowired
    private AccessService accessService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Access>> getAccesss(HttpServletRequest request) {
        return new ResponseEntity<>(accessService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getAccessKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(accessService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Access> getAccess(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(accessService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Access> addAccess(HttpServletRequest request, @RequestBody Access Access) {
        return new ResponseEntity<>(accessService.save(Access), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Access> updateAccess(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Access Access) {
        Access.setId(id);
        return new ResponseEntity<>(accessService.update(Access), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteAccess(HttpServletRequest request, @PathVariable UUID id) {
        accessService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

