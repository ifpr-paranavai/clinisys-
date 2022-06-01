package com.clinisys.clinisys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalControle {
	
	@GetMapping("/administrativo")
	public String acessarPrincipal() {
		return "administrativo/indexfuncionario";
	}
	
	//
	@GetMapping("/login")
	public String acessarLogin() {
		return "/login";
	}
}
