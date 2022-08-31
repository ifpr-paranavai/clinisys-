package com.clinisys.clinisys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginControle {
	
	
	@GetMapping("/login")
	public ModelAndView acessarLogin() {
		ModelAndView mv = new ModelAndView("/login");
		return mv;  
	}
	
	
}
