
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
import com.java.domain.Subgroup;
import com.java.service.SubgroupService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class SubgroupController {
    @Autowired
    private SubgroupService subgroupService;

    @RequestMapping(value="/subgroup/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Subgroup>> getSubgroupPage(HttpServletRequest request, Model model) {
        List<Subgroup> subgroup = subgroupService.getAll();
        return new ResponseEntity<List<Subgroup>>(subgroup, HttpStatus.OK);
    }
    
    @RequestMapping(value="/subgroup/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getSubgroupKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(subgroupService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/subgroup/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Subgroup> getSubgroupPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<Subgroup> subgroup = subgroupService.getById(id);
        return new ResponseEntity<Subgroup>(subgroup.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/subgroup/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody Subgroup obj){
        subgroupService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/subgroup/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Subgroup obj){
        obj.setId(id);
        subgroupService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/subgroup/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteSubgroup(HttpServletRequest request, Model model, @PathVariable UUID id) {
        subgroupService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


