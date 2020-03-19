
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
import com.java.domain.Users;
import com.java.service.UsersService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;
    
    @Autowired
    PasswordEncoder passwordEncoder;    

    @RequestMapping(value="/users/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Users>> getUsersPage(HttpServletRequest request, @RequestParam(required = false) Boolean enabled,
            @RequestParam(required = false) String lectern_name,
            @RequestParam(required = false) String deanery_name,
            @RequestParam(required = false) UUID lectern,
            @RequestParam(required = false) UUID deanery,
            Model model) {
        List<Users> users;
        if (enabled!=null){
            users = usersService.getByEnabled(enabled);
        } else if (deanery_name!=null){
            users = usersService.getByDeaneryName(deanery_name);
        } else if (lectern_name!=null){
            users = usersService.getByLecternName(lectern_name);
        } else if (deanery!=null){
            users = usersService.getByDeanery(deanery);
        } else if (lectern!=null){
            users = usersService.getByLectern(lectern);
        }
        else{
            users = usersService.getAll();
        }
        
        return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
    }
    
    @RequestMapping(value="/users/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getUsersKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(usersService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/users/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Users> getUsersPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<Users> users = usersService.getById(id);
        return new ResponseEntity<Users>(users.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/users/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody Users obj){
        obj.setPassword(passwordEncoder.encode(obj.getPassword()));
        usersService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/users/accept/{id}", method=RequestMethod.POST)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Users> accept(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Users user = usersService.getById(id).get();
        user.setEnabled(true);
        usersService.update(user);
        return new ResponseEntity<Users>(user, HttpStatus.OK);
    }
 
    @RequestMapping(value="/users/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Users obj){
        obj.setId(id);
        Users user = usersService.getById(id).get();
        obj.setPassword(user.getPassword());
        usersService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteUsers(HttpServletRequest request, Model model, @PathVariable UUID id) {
        usersService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


