
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
import com.java.domain.StudyPlan;
import com.java.service.StudyPlanService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class StudyPlanController {
    @Autowired
    private StudyPlanService studyplanService;

    @RequestMapping(value="/studyplan/", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<StudyPlan>> getStudyPlanPage(HttpServletRequest request, Model model) {
        List<StudyPlan> studyplan = studyplanService.getAll();
        return new ResponseEntity<List<StudyPlan>>(studyplan, HttpStatus.OK);
    }
    
    @RequestMapping(value="/studyplan/", method=RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getStudyPlanKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(studyplanService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/studyplan/{id}", method=RequestMethod.GET)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<StudyPlan> getStudyPlanPage(HttpServletRequest request, Model model, @PathVariable("id") UUID id) {
        Optional<StudyPlan> studyplan = studyplanService.getById(id);
        return new ResponseEntity<StudyPlan>(studyplan.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/studyplan/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(HttpServletRequest request, @RequestBody StudyPlan obj){
        studyplanService.save(obj);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/studyplan/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody StudyPlan obj){
        obj.setId(id);
        studyplanService.update(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/studyplan/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteStudyPlan(HttpServletRequest request, Model model, @PathVariable UUID id) {
        studyplanService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


