
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.OccupationCounter;
import com.java.service.OccupationCounterService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/occupationcounter")
public class OccupationCounterController {

    @Autowired
    private OccupationCounterService occupationCounterService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OccupationCounter>> getOccupationCounters(HttpServletRequest request) {
        return new ResponseEntity<>(occupationCounterService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getOccupationCounterKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(occupationCounterService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<OccupationCounter> getOccupationCounter(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(occupationCounterService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<OccupationCounter> addOccupationCounter(HttpServletRequest request, @RequestBody OccupationCounter OccupationCounter) {
        return new ResponseEntity<>(occupationCounterService.save(OccupationCounter), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<OccupationCounter> updateOccupationCounter(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody OccupationCounter OccupationCounter) {
        OccupationCounter.setId(id);
        return new ResponseEntity<>(occupationCounterService.update(OccupationCounter), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteOccupationCounter(HttpServletRequest request, @PathVariable UUID id) {
        occupationCounterService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

