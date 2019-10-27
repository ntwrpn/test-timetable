
package com.java.springsecurity.jdbcauthentication.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import com.java.domain.Subgroup;

import com.java.repository.SubgroupService;


import java.util.HashMap;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class SubgroupController {

    private SubgroupService orderService = new SubgroupService();

    @RequestMapping(value="/subgroup/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Subgroup>> getSubgroupPage(Model model) {
        List<Subgroup> orders = orderService.getAll();
        return new ResponseEntity<List<Subgroup>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/subgroup/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Subgroup> getCorpsKeys(Model model) {
        Subgroup order = new Subgroup();
        return new ResponseEntity<Subgroup>(order, HttpStatus.OK);
    }
    
    @RequestMapping(value="/subgroup/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Subgroup> getSubgroupPage(Model model, @PathVariable("id") int id) {
        List<Subgroup> orders = orderService.getById(id);
        Subgroup order = orders.get(0);
        return new ResponseEntity<Subgroup>(order, HttpStatus.OK);
    }

    @RequestMapping(value="/subgroup/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Subgroup obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/subgroup/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Subgroup obj){
     obj.setId(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/subgroup/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteSubgroup(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



