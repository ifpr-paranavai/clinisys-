package com.clinisys.clinisys.controller;

import java.text.ParseException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.clinisys.clinisys.model.Agenda;
import com.clinisys.clinisys.repository.AgendaRepositorio;
import com.clinisys.clinisys.repository.FuncionarioRepositorio;
import com.clinisys.clinisys.repository.PacienteRepositorio;

	
@Controller
public class AgendaControle {
	
	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;
	
	@Autowired
	private PacienteRepositorio pacienteRepositorio;
	
	@Autowired
	private AgendaRepositorio agendaRepositorio;
	
	@GetMapping("/administrativo/agenda/cadastrar")
	public ModelAndView cadastrar(Agenda agenda) {
		ModelAndView mv = new ModelAndView("administrativo/agenda/cadastro");
		mv.addObject("agenda", agenda);
		mv.addObject("listaFuncionarios", funcionarioRepositorio.findAll());
		mv.addObject("listaPacientes", pacienteRepositorio.findAll());
		return mv;  
	}
	
	@GetMapping("/administrativo/agenda/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/agenda/lista");
		mv.addObject("listaAgenda", agendaRepositorio.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/agenda/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Agenda> agenda = agendaRepositorio.findById(id);
		return cadastrar(agenda.get());
	}
	
	@GetMapping("/administrativo/agenda/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Agenda> agenda = agendaRepositorio.findById(id);
		agendaRepositorio.delete(agenda.get());
		return listar();
	}

	@PostMapping("/administrativo/agenda/salvar")
	public ModelAndView salvar(@Valid Agenda agenda, BindingResult result) throws ParseException {
		
		System.out.println(result.getAllErrors());
		if (result.hasErrors()) {
			return cadastrar(agenda);
		} 
		
		agendaRepositorio.saveAndFlush(agenda);

		return cadastrar(new Agenda());
	}
	
	
}
