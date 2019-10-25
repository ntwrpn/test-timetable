package com.java.springsecurity.jdbcauthentication.controller;

import javax.annotation.security.RolesAllowed;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class WebController {
   
    @RequestMapping(value="/")
    public String home(){
        return "home";
    }
    
    @RequestMapping(value="/home")
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public String index(){
	//ModelAndView model = new ModelAndView("index");
        return "index";
    }
   
    
    @RequestMapping(value="/user")
    @RolesAllowed("ROLE_USER")
    public String user(){
        return "user";
    }
  
    
    @RequestMapping(value="/admin")
    @RolesAllowed("ROLE_ADMIN")
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
