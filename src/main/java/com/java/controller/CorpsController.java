
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Corps;
import com.java.service.CorpsService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/corps")
public class CorpsController {

    @Autowired
    private CorpsService corpsService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Corps>> getCorpss(HttpServletRequest request) {
        return new ResponseEntity<>(corpsService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getCorpsKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(corpsService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Corps> getCorps(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(corpsService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Corps> addCorps(HttpServletRequest request, @RequestBody Corps Corps) {
        return new ResponseEntity<>(corpsService.save(Corps), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Corps> updateCorps(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Corps Corps) {
        Corps.setId(id);
        return new ResponseEntity<>(corpsService.update(Corps), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteCorps(HttpServletRequest request, @PathVariable UUID id) {
        corpsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

