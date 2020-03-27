
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.LecternType;
import com.java.service.LecternTypeService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/lecterntype")
public class LecternTypeController {

    @Autowired
    private LecternTypeService lecternTypeService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<LecternType>> getLecternTypes(HttpServletRequest request) {
        return new ResponseEntity<>(lecternTypeService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getLecternTypeKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(lecternTypeService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<LecternType> getLecternType(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(lecternTypeService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<LecternType> addLecternType(HttpServletRequest request, @RequestBody LecternType LecternType) {
        return new ResponseEntity<>(lecternTypeService.save(LecternType), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<LecternType> updateLecternType(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody LecternType LecternType) {
        LecternType.setId(id);
        return new ResponseEntity<>(lecternTypeService.update(LecternType), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteLecternType(HttpServletRequest request, @PathVariable UUID id) {
        lecternTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

