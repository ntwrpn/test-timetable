
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Deanery;
import com.java.service.DeaneryService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/deanery")
public class DeaneryController {

    @Autowired
    private DeaneryService deaneryService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Deanery>> getDeanerys(HttpServletRequest request) {
        return new ResponseEntity<>(deaneryService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getDeaneryKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(deaneryService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Deanery> getDeanery(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(deaneryService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Deanery> addDeanery(HttpServletRequest request, @RequestBody Deanery Deanery) {
        return new ResponseEntity<>(deaneryService.save(Deanery), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Deanery> updateDeanery(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Deanery Deanery) {
        Deanery.setId(id);
        return new ResponseEntity<>(deaneryService.update(Deanery), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteDeanery(HttpServletRequest request, @PathVariable UUID id) {
        deaneryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

