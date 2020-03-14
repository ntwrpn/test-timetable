package com.java.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
public class WebController {
   
    @RequestMapping(value="/")
    public String index(){
        return "index";
    }
    
    @RequestMapping(value="/home")
    public String home(HttpServletRequest request){
        if (request.isUserInRole("ADMIN")) {
            return "admin";
        } else if (request.isUserInRole("USER")) {
            return "user";
        } else {
            return "object";
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/objects")
    public String objects(){
        return "object";
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
