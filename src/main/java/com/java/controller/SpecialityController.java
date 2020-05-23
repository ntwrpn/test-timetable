
package com.java.controller;

import java.util.List;
import java.util.UUID;

import com.java.config.ExceptionResponceCreator;
import com.java.domain.Response;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Speciality;
import com.java.service.SpecialityService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/speciality")
public class SpecialityController {

    @Autowired
    private SpecialityService specialityService;

    @ExceptionHandler
    public ResponseEntity<Response> handleException(ConstraintViolationException exception) {
        return new ResponseEntity<>(ExceptionResponceCreator.createResponse(exception.getConstraintViolations()), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Speciality>> getSpecialities(HttpServletRequest request, @RequestParam(name = "lecternId", required = false) UUID lecternId) {
        if (lecternId != null){
            return new ResponseEntity<>(specialityService.getAllByLecternId(lecternId), HttpStatus.OK);
        }
        return new ResponseEntity<>(specialityService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSpecialityKeys(HttpServletRequest request) {
        return new ResponseEntity(specialityService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Speciality> getSpeciality(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(specialityService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Speciality> addSpeciality(HttpServletRequest request, @RequestParam(name = "lecternId") UUID lecternId, @Valid @RequestBody Speciality speciality) {
        return new ResponseEntity<>(specialityService.save(speciality, lecternId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Speciality> updateSpeciality(HttpServletRequest request, @PathVariable("id") UUID id, @Valid @RequestBody Speciality speciality) {
        speciality.setId(id);
        return new ResponseEntity<>(specialityService.update(speciality), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteSpeciality(HttpServletRequest request, @PathVariable UUID id) {
        specialityService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}


