
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
import com.java.domain.LearningSeverityList;
import com.java.service.LearningSeverityListService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class LearningSeverityListController {
    @Autowired
    private LearningSeverityListService learningseveritylistService;

    @RequestMapping(value="/learningseveritylist/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<LearningSeverityList>> getLearningSeverityListPage(HttpServletRequest request, Model model) {
        List<LearningSeverityList> learningseveritylist = learningseveritylistService.getAll();
        return new ResponseEntity<List<LearningSeverityList>>(learningseveritylist, HttpStatus.OK);
    }
    
    @RequestMapping(value="/learningseveritylist/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getLearningSeverityListKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(learningseveritylistService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/learningseveritylist/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<LearningSeverityList> getLearningSeverityListPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<LearningSeverityList> learningseveritylist = learningseveritylistService.getById(id);
        return new ResponseEntity<LearningSeverityList>(learningseveritylist.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/learningseveritylist/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody LearningSeverityList obj){
        learningseveritylistService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/learningseveritylist/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody LearningSeverityList obj){
        obj.setId(id);
        learningseveritylistService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/learningseveritylist/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteLearningSeverityList(HttpServletRequest request, Model model, @PathVariable UUID id) {
        learningseveritylistService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


