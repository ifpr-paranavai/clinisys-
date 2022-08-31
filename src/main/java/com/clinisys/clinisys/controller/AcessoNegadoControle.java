package com.clinisys.clinisys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AcessoNegadoControle {
	
	
	@GetMapping("/acessoNegado")
	public ModelAndView acessoNegado() {
		ModelAndView mv = new ModelAndView("/acessoNegado");
		return mv;  
	}
	
	
	
}
