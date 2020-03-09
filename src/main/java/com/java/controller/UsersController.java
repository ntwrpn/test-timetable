
package com.java.controller;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Users;
import com.java.service.UsersService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;
    
    @RequestMapping(value="/users/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Users>> getUsersPage(@RequestParam(required = false) Boolean enabled, Model model) {
        List<Users> orders;
        if (enabled!=null){
            orders = usersService.getByEnabled(enabled);
        } else{
            orders = usersService.getAll();
        }
        return new ResponseEntity<List<Users>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/users/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity getUsersKeys(Model model) {
        return new ResponseEntity(usersService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/users/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Users> getUsersPage(Model model, @PathVariable("id") UUID id) {
        Optional<Users> order = usersService.getById(id);
        return new ResponseEntity<Users>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/users/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody Users obj){
     if (usersService.getByName(obj.getUsername()).isEmpty()){
        usersService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
     }
        return new ResponseEntity<Void>(HttpStatus.FOUND);
}
    @RequestMapping(value="/users/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody Users obj){
     obj.setId(id);
     usersService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteUsers(Model model, @PathVariable UUID id) {
        usersService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value="/users/accept/{id}", method=RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> AcceptUsers(@PathVariable UUID id) {
        Users user = usersService.getById(id).get();
        user.setEnabled(true);
        usersService.update(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}


