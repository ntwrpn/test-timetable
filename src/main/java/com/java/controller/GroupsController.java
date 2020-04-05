
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Groups;
import com.java.service.GroupsService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/groups")
public class GroupsController {

    @Autowired
    private GroupsService groupsService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Groups>> getGroupss(HttpServletRequest request, @RequestParam(name = "flowId", required = false) UUID uuid) {
        if(uuid != null){
            return new ResponseEntity<>(groupsService.findByFlowId(uuid), HttpStatus.OK);
        }
        return new ResponseEntity<>(groupsService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getGroupsKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(groupsService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Groups> getGroups(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(groupsService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Groups> addGroups(HttpServletRequest request, @RequestParam(name = "flowId", required = false) UUID flowId, @RequestBody Groups Groups) {
        return new ResponseEntity<>(groupsService.save(Groups, flowId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Groups> updateGroups(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Groups Groups) {
        Groups.setId(id);
        return new ResponseEntity<>(groupsService.update(Groups), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteGroups(HttpServletRequest request, @PathVariable UUID id) {
        groupsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

