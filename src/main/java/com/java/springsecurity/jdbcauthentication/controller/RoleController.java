
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
import com.java.domain.Role;

import com.java.repository.RoleService;


import java.util.HashMap;

@Controller
public class RoleController {

    private RoleService orderService = new RoleService();

    @RequestMapping(value="/role/", method=RequestMethod.GET)
    public ResponseEntity<List<Role>> getRolePage(Model model) {
        List<Role> orders = orderService.getAll();
        return new ResponseEntity<List<Role>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/role/", method=RequestMethod.OPTIONS)
    public ResponseEntity<Role> getCorpsKeys(Model model) {
        Role order = new Role();
        return new ResponseEntity<Role>(order, HttpStatus.OK);
    }
    
    @RequestMapping(value="/role/{id}", method=RequestMethod.GET)
    public ResponseEntity<Role> getRolePage(Model model, @PathVariable("id") int id) {
        List<Role> orders = orderService.getById(id);
        Role order = orders.get(0);
        return new ResponseEntity<Role>(order, HttpStatus.OK);
    }

    @RequestMapping(value="/role/", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<Void> add(@RequestBody Role obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/role/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Role obj){
     obj.setId(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/role/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    public ResponseEntity<Void> DeleteRole(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



