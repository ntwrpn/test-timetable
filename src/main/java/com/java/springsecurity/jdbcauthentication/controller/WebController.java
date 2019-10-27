package com.java.springsecurity.jdbcauthentication.controller;

import javax.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class WebController {
   
    @RequestMapping(value="/")
    public String home(){
        return "home";
    }
    
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/home")
    public String index(){
	//ModelAndView model = new ModelAndView("index");
        return "index";
    }
   
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value="/user")
    public String user(){
        return "user";
    }
  
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/admin")
    public String admin(){
        return "admin";
    }
   
    @RequestMapping(value="/login")
    public String login(){
        return "login";
    }
   
    @RequestMapping(value="/403")
    public String Error403(){
        return "403";
    }
}
