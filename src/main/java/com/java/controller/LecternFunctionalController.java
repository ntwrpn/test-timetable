package com.java.controller;

import com.java.config.JwtProvider;
import com.java.domain.Employee;
import com.java.domain.Lectern;
import com.java.domain.Teacher;
import com.java.domain.Users;
import com.java.payload.JwtAuthenticationResponse;
import com.java.payload.LoginRequest;
import com.java.service.EmployeeService;
import com.java.service.LecternService;
import com.java.service.TeacherService;
import com.java.service.UsersService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LecternFunctionalController {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private LecternService lecternService;

    @Autowired
    private UsersService userService;

    @Autowired
    private TeacherService teacherService;
    
    @Autowired
    private EmployeeService employeeService;


    @GetMapping(value = "/lectern/{id}/addusertoteacher")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ModelAndView getusertoteacher(HttpServletRequest request, @PathVariable("id") UUID id, ModelMap map) {
        Lectern lectern = lecternService.getById(id).get();
        ModelAndView model = new ModelAndView("addusertoteacher");
        model.addObject("lectern", lectern.getName());
        model.addObject("lectern(Id", lectern.getId());
        return model;
    }

    @PostMapping(value = "/lectern/{id}/addusertoteacher")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addusertoteacher(HttpServletRequest request, @PathVariable("id") UUID id,
            @RequestParam(required = false) UUID userId,
            @RequestParam(required = false) UUID teacherId) {
        if (userService.getByTeacherId(teacherId).isEmpty()) {
            Users user = userService.getById(userId).get();
            Teacher teacher = teacherService.getById(teacherId).get();
            user.setTeacher(teacher);
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Этот преподаватель уже назначен юзеру", HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/lectern/{id}/deleteuserfromteacher")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addusertoteacher(HttpServletRequest request, @PathVariable("id") UUID id,
            @RequestParam(required = false) UUID teacherId) {
        if (userService.getByTeacherId(teacherId).isEmpty()) {
            return new ResponseEntity<>("Этот преподаватель никому не назначен", HttpStatus.CONFLICT);
        } else {
            Users user = userService.getByTeacherId(teacherId).get();
            user.setTeacher(null);
            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        }
    }
    
    
    @GetMapping(value = "/deanery/{id}/addusertoemployee")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public String getusertoemployee(HttpServletRequest request, @PathVariable("id") UUID id, ModelMap map) {
        return "addusertoemployee";
    }

    @PostMapping(value = "/deanery/{id}/addusertoemployee")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addusertoemployee(HttpServletRequest request, @PathVariable("id") UUID id,
            @RequestParam(required = false) UUID userId,
            @RequestParam(required = false) UUID EmployeeId) {
        if (userService.getByEmployeeId(EmployeeId).isEmpty()) {
            Users user = userService.getById(userId).get();
            Employee employee = employeeService.getById(EmployeeId).get();
            user.setEmployee(employee);
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Этот работник уже назначен юзеру", HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/deanery/{id}/deleteuseremployee")
    @PreAuthorize("@CustomSecurityService.hasPermission(authentication, #request) or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addusertoemployee(HttpServletRequest request, @PathVariable("id") UUID id,
            @RequestParam(required = false) UUID EmployeeId) {
        if (userService.getByEmployeeId(EmployeeId).isEmpty()) {
            return new ResponseEntity<>("Этот работник никому не назначен", HttpStatus.CONFLICT);
        } else {
            Users user = userService.getByEmployeeId(EmployeeId).get();
            user.setEmployee(null);
            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        }
    }

    private String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }

}
