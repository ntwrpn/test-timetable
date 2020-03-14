
package com.java.controller;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import java.util.HashMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import com.java.domain.Groups;
import com.java.service.GroupsService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class GroupsController {
    @Autowired
    private GroupsService groupsService;

    @RequestMapping(value="/groups/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Groups>> getGroupsPage(HttpServletRequest request, Model model) {
        List<Groups> groups = groupsService.getAll();
        return new ResponseEntity<List<Groups>>(groups, HttpStatus.OK);
    }
    
    @RequestMapping(value="/groups/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getGroupsKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(groupsService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/groups/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Groups> getGroupsPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<Groups> groups = groupsService.getById(id);
        return new ResponseEntity<Groups>(groups.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/groups/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody Groups obj){
        groupsService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/groups/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Groups obj){
        obj.setId(id);
        groupsService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/groups/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteGroups(HttpServletRequest request, Model model, @PathVariable UUID id) {
        groupsService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


