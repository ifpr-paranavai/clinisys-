package com.clinisys.clinisys.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.clinisys.clinisys.model.Mensagem;
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
	public ModelAndView salvar(@Valid Paciente paciente, BindingResult result, HttpSession session) {
		
		try {
			paciente.setSenha(new BCryptPasswordEncoder().encode(paciente.getSenha()));
			List<Paciente> paciente1 = pacienteRepositorio.consultaPaciente(paciente.getCpf());
			if(paciente1.isEmpty() ) {
				pacienteRepositorio.saveAndFlush(paciente);
				session.setAttribute("mensagem", new Mensagem("PACIENTE cadastrado com SUCESSO!!!", "success"));
			} else {
				session.setAttribute("mensagem", new Mensagem("Ops! Algo deu ERRADO..., tente novamente!", "danger"));
			}
		} catch (Exception e) {
			if(result.hasErrors()) {
				return cadastrar(paciente);
			}
		}
		
		
		
		return cadastrar(new Paciente());
	}
}
