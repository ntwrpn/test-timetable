
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.LearningSeverityList;
import com.java.service.LearningSeverityListService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/learningseveritylist")
public class LearningSeverityListController {

    @Autowired
    private LearningSeverityListService learningSeverityListService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<LearningSeverityList>> getLearningSeverityLists(HttpServletRequest request) {
        return new ResponseEntity<>(learningSeverityListService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getLearningSeverityListKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(learningSeverityListService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<LearningSeverityList> getLearningSeverityList(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(learningSeverityListService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<LearningSeverityList> addLearningSeverityList(HttpServletRequest request, @RequestBody LearningSeverityList LearningSeverityList) {
        return new ResponseEntity<>(learningSeverityListService.save(LearningSeverityList), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<LearningSeverityList> updateLearningSeverityList(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody LearningSeverityList LearningSeverityList) {
        LearningSeverityList.setId(id);
        return new ResponseEntity<>(learningSeverityListService.update(LearningSeverityList), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteLearningSeverityList(HttpServletRequest request, @PathVariable UUID id) {
        learningSeverityListService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

