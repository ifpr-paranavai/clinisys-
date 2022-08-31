package com.clinisys.clinisys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalControle {
	
	@GetMapping("/administrativo") // mapping serve para mapear a url
	public String acessarPrincipal() {
		return "/administrativo/index"; //retorna as pastas contendo o layout
	}
	
//	@GetMapping("/login")
//	public String acessarLogin() {
//		return "/login";
//	}
	
	@GetMapping("/administrativo/layout")
	public String acessarLayout() {
		return "/administrativo/fragments/layout";
	}
	
	@GetMapping("/recuperar-senha")
	public String esqueceuSenha() {
		return "/forgot-password.html";
	}
}
