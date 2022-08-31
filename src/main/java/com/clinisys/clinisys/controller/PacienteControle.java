package com.clinisys.clinisys.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.clinisys.clinisys.model.Paciente;
import com.clinisys.clinisys.repository.PacienteRepositorio;


@Controller
public class PacienteControle {
	
	@Autowired
	private PacienteRepositorio pacienteRepositorio;
	
	@GetMapping("/paciente/pacientes/cadastrar")
	public ModelAndView cadastrar(Paciente paciente) {
		ModelAndView mv = new ModelAndView("paciente/pacientes/cadastro");
		mv.addObject("paciente", paciente);
		return mv;	
	}
	
	@GetMapping("/paciente/pacientes/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("/paciente/pacientes/lista");
		mv.addObject("listaPacientes", pacienteRepositorio.findAll());
		return mv;
	}
	
	@GetMapping("/paciente/pacientes/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Paciente> paciente = pacienteRepositorio.findById(id);
		return cadastrar(paciente.get());
	}
	
	@GetMapping("/paciente/pacientes/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Paciente> paciente = pacienteRepositorio.findById(id);
		pacienteRepositorio.delete(paciente.get());
		return listar();
	}
	
	@PostMapping("/paciente/pacientes/salvar")
	public ModelAndView salvar(@Validated Paciente paciente, BindingResult result) {
		if(result.hasErrors()) {
			return cadastrar(paciente);
		}
		paciente.setSenha(new BCryptPasswordEncoder().encode(paciente.getSenha()));
		pacienteRepositorio.saveAndFlush(paciente);
		
		return cadastrar(new Paciente());
	}
}
