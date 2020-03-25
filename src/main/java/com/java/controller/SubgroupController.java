
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Subgroup;
import com.java.service.SubgroupService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/subgroup")
public class SubgroupController {

    @Autowired
    private SubgroupService subgroupService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Subgroup>> getSubgroups(HttpServletRequest request) {
        return new ResponseEntity<>(subgroupService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSubgroupKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(subgroupService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Subgroup> getSubgroup(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(subgroupService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Subgroup> addSubgroup(HttpServletRequest request, @RequestBody Subgroup Subgroup) {
        return new ResponseEntity<>(subgroupService.save(Subgroup), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Subgroup> updateSubgroup(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Subgroup Subgroup) {
        Subgroup.setId(id);
        return new ResponseEntity<>(subgroupService.update(Subgroup), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteSubgroup(HttpServletRequest request, @PathVariable UUID id) {
        subgroupService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

