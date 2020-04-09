
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Lectern;
import com.java.service.LecternService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/lectern")
public class LecternController {

    @Autowired
    private LecternService lecternService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Lectern>> getLecterns(HttpServletRequest request,  @RequestParam(name = "deaneryId", required = false) UUID uuid) {
		if(uuid != null){
			List<Lectern> lectern = lecternService.findLecternsByDeaneryId(uuid);
			return new ResponseEntity<List<Lectern>>(lectern, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(lecternService.getAll(), HttpStatus.OK);
		}

    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getLecternKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(lecternService.getFields(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Lectern> getLectern(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(lecternService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> addLectern(HttpServletRequest request, @RequestParam(name = "deaneryId", required = false) UUID id, @RequestBody Lectern lectern) {
        try {
            return new ResponseEntity<>(lecternService.save(lectern,id), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),new HttpHeaders(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Lectern> updateLectern(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Lectern lectern) {
        lectern.setId(id);
        return new ResponseEntity<>(lecternService.update(lectern), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteLectern(HttpServletRequest request, @PathVariable UUID id) {
        lecternService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/checkUniqLectern/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Boolean> checkUniqueName(HttpServletRequest request, @RequestParam(name = "name", required = false) String name, @RequestParam(name = "fullname", required = false) String fullname) {
        if(fullname != null){
            return new ResponseEntity<>(lecternService.findByFullname(fullname).size() == 0, HttpStatus.OK);
        }
        return new ResponseEntity<>(lecternService.findByName(name).size() == 0, HttpStatus.OK);
    }
}


