
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
import com.java.domain.StudyPlan;
import com.java.service.StudyPlanService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class StudyPlanController {
    @Autowired
    private StudyPlanService studyPlanService;

    @RequestMapping(value="/studyplan/", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<StudyPlan>> getStudyPlanPage(Model model) {
        List<StudyPlan> orders = studyPlanService.getAll();
        return new ResponseEntity<List<StudyPlan>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/studyplan/", method=RequestMethod.OPTIONS)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getStudyPlanKeys(Model model) {
        return new ResponseEntity(studyPlanService.getFields(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/studyplan/{id}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<StudyPlan> getStudyPlanPage(Model model, @PathVariable("id") UUID id) {
        Optional<StudyPlan> order = studyPlanService.getById(id);
        return new ResponseEntity<StudyPlan>(order.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/studyplan/", method = RequestMethod.POST, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> add(@RequestBody StudyPlan obj){
     studyPlanService.save(obj);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/studyplan/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody StudyPlan obj){
     obj.setId(id);
     studyPlanService.update(obj);
     return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/studyplan/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteStudyPlan(Model model, @PathVariable UUID id) {
        studyPlanService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}


