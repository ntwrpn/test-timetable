
package com.javamaster.controller;

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
import com.google.gson.Gson;
import org.springframework.web.servlet.ModelAndView;


import java.util.HashMap;

@Controller
public class IndexController {

    @RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView getIndex() {

		ModelAndView model = new ModelAndView("index");
	
		return model;

	}
    
}



