
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Flow;
import com.java.service.FlowService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/flow")
public class FlowController {

    @Autowired
    private FlowService flowService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Flow>> getFlows(HttpServletRequest request) {
        return new ResponseEntity<>(flowService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getFlowKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(flowService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Flow> getFlow(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(flowService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Flow> addFlow(HttpServletRequest request, @RequestBody Flow Flow) {
        Flow flow = flowService.save(Flow);
        flow.setGroups(Flow.getGroups());
        flowService.save(flow);
        return new ResponseEntity<>(flow, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Flow> updateFlow(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody Flow Flow) {
        Flow.setId(id);
        return new ResponseEntity<>(flowService.update(Flow), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteFlow(HttpServletRequest request, @PathVariable UUID id) {
        flowService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

