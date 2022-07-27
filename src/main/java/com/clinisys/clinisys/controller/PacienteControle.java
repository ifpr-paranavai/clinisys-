package com.clinisys.clinisys.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.clinisys.clinisys.model.Paciente;
import com.clinisys.clinisys.repository.PacienteRepositorio;


@Controller
public class PacienteControle {
	
	@Autowired
	private PacienteRepositorio pacienteRepositorio;
	
	@GetMapping("/pacientes/cadastrar")
	public ModelAndView cadastrar(Paciente paciente) {
		ModelAndView mv = new ModelAndView("pacientes/cadastro");
		mv.addObject("paciente", paciente);
		return mv;	
	}
	
	@GetMapping("/pacientes/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("pacientes/lista");
		mv.addObject("listaPacientes", pacienteRepositorio.findAll());
		return mv;
	}
	
	public ModelAndView salavr(@Validated Paciente paciente, BindingResult result) {
		if(result.hasErrors()) {
			return cadastrar(paciente);
		}
		pacienteRepositorio.saveAndFlush(paciente);
		
		return cadastrar(new Paciente());
	}
}
