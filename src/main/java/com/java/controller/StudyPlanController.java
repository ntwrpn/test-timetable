
package com.java.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.StudyPlan;
import com.java.service.StudyPlanService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/studyplan")
public class StudyPlanController {

    @Autowired
    private StudyPlanService studyplanService;

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<StudyPlan>> getStudyPlans(HttpServletRequest request, @RequestParam(name = "lecternId", required = false) UUID uuid) {
        if(uuid != null){
            return new ResponseEntity<>(studyplanService.findStudyplansByLecternId(uuid), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(studyplanService.getAll(), HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getStudyPlanKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity<>(studyplanService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<StudyPlan> getStudyPlan(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(studyplanService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<StudyPlan> addStudyPlan(HttpServletRequest request, @RequestBody StudyPlan studyPlan) {
        return new ResponseEntity<>(studyplanService.save(studyPlan), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<StudyPlan> updateStudyPlan(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody StudyPlan studyPlan) {
        studyPlan.setId(id);
        return new ResponseEntity<>(studyplanService.update(studyPlan), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteStudyPlan(HttpServletRequest request, @PathVariable UUID id) {
        studyplanService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
	}
}