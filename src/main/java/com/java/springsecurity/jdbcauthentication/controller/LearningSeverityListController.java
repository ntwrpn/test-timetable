
package com.java.springsecurity.jdbcauthentication.controller;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import com.java.domain.LearningSeverityList;

import com.java.repository.LearningSeverityListService;


import java.util.HashMap;

@Controller
public class LearningSeverityListController {

    private LearningSeverityListService orderService = new LearningSeverityListService();

    @RequestMapping(value="/learningseveritylist/", method=RequestMethod.GET)
    public ResponseEntity<List<LearningSeverityList>> getLearningSeverityListPage(Model model) {
        List<LearningSeverityList> orders = orderService.getAll();
        return new ResponseEntity<List<LearningSeverityList>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value="/learningseveritylist/{id}", method=RequestMethod.GET)
    public ResponseEntity<List<LearningSeverityList>> getLearningSeverityListPage(Model model, @PathVariable("id") int id) {
        List<LearningSeverityList> orders = orderService.getById(id);
        return new ResponseEntity<List<LearningSeverityList>>(orders, HttpStatus.OK);
    }

    @RequestMapping(value="/learningseveritylist/", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<LearningSeverityList> add(@RequestBody LearningSeverityList obj){
     orderService.add(obj);
     HttpHeaders headers = new HttpHeaders();
     List<LearningSeverityList> orders = orderService.getLastId();
     LearningSeverityList order = orders.get(0);
     return new ResponseEntity<LearningSeverityList>(order, HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/learningseveritylist/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody LearningSeverityList obj){
     obj.setId(id);
     orderService.update(obj);
     HttpHeaders headers = new HttpHeaders();
     return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(value="/learningseveritylist/bylist", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<List<LearningSeverityList>> update(@RequestBody List<Integer> obj){
     List<LearningSeverityList> orders = orderService.getAllInArray(obj);
     return new ResponseEntity<List<LearningSeverityList>>(orders, HttpStatus.OK);
    }

    @RequestMapping(value = "/learningseveritylist/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    public ResponseEntity<Void> DeleteLearningSeverityList(Model model, @PathVariable Integer id) {
        orderService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}



