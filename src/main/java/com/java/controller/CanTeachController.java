
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.CanTeach;
import com.java.service.CanTeachService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/canteach")
public class CanTeachController {

    @Autowired
    private CanTeachService canTeachService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<CanTeach>> getCanTeachs(HttpServletRequest request) {
        return new ResponseEntity<>(canTeachService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getCanTeachKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(canTeachService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<CanTeach> getCanTeach(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(canTeachService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<CanTeach> addCanTeach(HttpServletRequest request, @RequestBody CanTeach CanTeach) {
        return new ResponseEntity<>(canTeachService.save(CanTeach), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<CanTeach> updateCanTeach(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody CanTeach CanTeach) {
        CanTeach.setId(id);
        return new ResponseEntity<>(canTeachService.update(CanTeach), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteCanTeach(HttpServletRequest request, @PathVariable UUID id) {
        canTeachService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

