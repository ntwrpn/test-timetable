
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Occupation;
import com.java.service.OccupationService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/occupation")
public class OccupationController {

    @Autowired
    private OccupationService occupationService;

    @ExceptionHandler
    public ResponseEntity<Response> handleException(ConstraintViolationException exception) {
        return  new ResponseEntity<>(ExceptionResponceCreator.createResponse(exception.getConstraintViolations()),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Occupation>> getOccupations(HttpServletRequest request) {
        return new ResponseEntity<>(occupationService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getOccupationKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(occupationService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Occupation> getOccupation(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(occupationService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Occupation> addOccupation(HttpServletRequest request, @RequestBody Occupation Occupation) {
        return new ResponseEntity<>(occupationService.save(Occupation), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Occupation> updateOccupation(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Occupation Occupation) {
        Occupation.setId(id);
        return new ResponseEntity<>(occupationService.update(Occupation), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteOccupation(HttpServletRequest request, @PathVariable UUID id) {
        occupationService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/checkUniqOccupation/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Boolean> checkUniqueName(HttpServletRequest request, @RequestParam(name = "symbol", required = false) String symbol, @RequestParam(name = "value", required = false) String value) {
        if(symbol != null){
            return new ResponseEntity<>(occupationService.findBySymbol(symbol).size() == 0, HttpStatus.OK);
        }
        return new ResponseEntity<>(occupationService.findByValue(value).size() == 0, HttpStatus.OK);
    }

}

