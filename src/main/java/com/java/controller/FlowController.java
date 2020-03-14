
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
import com.java.domain.Flow;
import com.java.service.FlowService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class FlowController {
    @Autowired
    private FlowService flowService;

    @RequestMapping(value="/flow/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Flow>> getFlowPage(HttpServletRequest request, Model model) {
        List<Flow> flow = flowService.getAll();
        return new ResponseEntity<List<Flow>>(flow, HttpStatus.OK);
    }
    
    @RequestMapping(value="/flow/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getFlowKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(flowService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/flow/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Flow> getFlowPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<Flow> flow = flowService.getById(id);
        return new ResponseEntity<Flow>(flow.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/flow/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody Flow obj){
        flowService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/flow/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Flow obj){
        obj.setId(id);
        flowService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/flow/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteFlow(HttpServletRequest request, Model model, @PathVariable UUID id) {
        flowService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


