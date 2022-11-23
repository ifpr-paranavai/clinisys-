package com.clinisys.clinisys.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.clinisys.clinisys.model.Agenda;
import com.clinisys.clinisys.model.Paciente;
import com.clinisys.clinisys.repository.AgendaRepositorio;
import com.clinisys.clinisys.repository.FuncaoRepositorio;
import com.clinisys.clinisys.repository.FuncionarioRepositorio;
import com.clinisys.clinisys.repository.PacienteRepositorio;

@Controller
public class DashboardControle {
	
	@Autowired
	private PacienteRepositorio pacienteRepositorio;
	
	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;
	
	@Autowired
	private FuncaoRepositorio funcaoRepositorio;
	
	@Autowired
	private AgendaRepositorio agendaRepositorio;
	
	@GetMapping("/dashboard")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("/dashboard");
		
		var numeroPacientes = pacienteRepositorio.count();
		var numeroFuncionarios = funcionarioRepositorio.count();
		var listaPacienteMasculino = quantidadePacienteMasculino();
		var listaPacienteFeminino = quantidadePacienteFeminino();
		
		mv.addObject("quantidadePacientes", pacienteRepositorio.count());
		mv.addObject("quantidadeFuncionarios", funcionarioRepositorio.count());
		mv.addObject("quantidadeFuncoes", funcaoRepositorio.count());
		mv.addObject("quantidadeAgendaHoje", quantidadeAgendamentosHoje());
		mv.addObject("quantidadesAtendimentoTotal", agendaRepositorio.count());
		mv.addObject("numeroPacientes", numeroPacientes);
		mv.addObject("numeroFuncionarios", numeroFuncionarios);
		mv.addObject("listaPacienteMasculino", quantidadePacienteMasculino());
		mv.addObject("listaPacienteFeminino", quantidadePacienteFeminino());
		return mv;
	}
	
	public int quantidadeAgendamentosHoje() {
		List<Agenda> agenda = agendaRepositorio.findAll();
		
		Date dataAtual = new Date();
		
		List<Agenda> agendamentoHoje = agendaRepositorio.consultaAgendaHoje(dataAtual);
		
		return agendamentoHoje.size();
		
	}
	
	public int quantidadePacienteMasculino() {
		List<Paciente> paciente = pacienteRepositorio.findAll();
		
		String sexoMasculino = "1";
		
		List<Paciente> listaPacienteMasculino = pacienteRepositorio.listaPacienteMasculino(sexoMasculino);
		
		return listaPacienteMasculino.size();
	}
	
	public int quantidadePacienteFeminino() {
		List<Paciente> paciente = pacienteRepositorio.findAll();
		
		String sexoFeminino = "2";
		
		List<Paciente> listaPacienteFeminino = pacienteRepositorio.listaPacienteFeminino(sexoFeminino);
		
		return listaPacienteFeminino.size();
	}
	
}
