
package com.java.controller;

import java.util.List;
import java.util.UUID;

import com.java.config.ExceptionResponceCreator;
import com.java.domain.Response;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.java.domain.Employee;
import com.java.service.EmployeeService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ExceptionHandler
    public ResponseEntity<Response> handleException(ConstraintViolationException exception) {
        return  new ResponseEntity<>(ExceptionResponceCreator.createResponse(exception.getConstraintViolations()),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Employee>> getEmployees(HttpServletRequest request,
                                                       @RequestParam(name = "deaneryId", required = false) UUID uuid) {
        if(uuid != null){
            return new ResponseEntity<>(employeeService.findByDeanery(uuid), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity getEmployeeKeys(HttpServletRequest request, Model model) {
        return new ResponseEntity(employeeService.getFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Employee> getEmployee(HttpServletRequest request, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(employeeService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Employee> addEmployee(HttpServletRequest request,
                                                @RequestParam(name = "deaneryId", required = false) UUID id,
                                                @RequestBody Employee Employee) {
        return new ResponseEntity<>(employeeService.save(Employee, id), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Employee> updateEmployee(HttpServletRequest request,
                                                   @PathVariable("id") UUID id, @RequestBody Employee Employee) {
        Employee.setId(id);
        return new ResponseEntity<>(employeeService.update(Employee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteEmployee(HttpServletRequest request, @PathVariable UUID id) {
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

