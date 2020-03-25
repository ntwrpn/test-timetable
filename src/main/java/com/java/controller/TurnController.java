
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Turn;
import com.java.service.TurnService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/turn")
public class TurnController {

    @Autowired
    private TurnService turnService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Turn>> getTurns(HttpServletRequest request) {
        return new ResponseEntity<>(turnService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getTurnKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(turnService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Turn> getTurn(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(turnService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Turn> addTurn(HttpServletRequest request, @RequestBody Turn Turn) {
        return new ResponseEntity<>(turnService.save(Turn), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Turn> updateTurn(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Turn Turn) {
        Turn.setId(id);
        return new ResponseEntity<>(turnService.update(Turn), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteTurn(HttpServletRequest request, @PathVariable UUID id) {
        turnService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

