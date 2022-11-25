package com.clinisys.clinisys.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.clinisys.clinisys.model.Agenda;
import com.clinisys.clinisys.model.Mensagem;
import com.clinisys.clinisys.repository.AgendaRepositorio;
import com.clinisys.clinisys.repository.FuncionarioRepositorio;
import com.clinisys.clinisys.repository.PacienteRepositorio;
import com.clinisys.clinisys.repository.TipoProcedimentoRepositorio;

@Controller
public class AgendaControle {

	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;

	@Autowired
	private PacienteRepositorio pacienteRepositorio;

	@Autowired
	private AgendaRepositorio agendaRepositorio;
	
	@Autowired 
	private TipoProcedimentoRepositorio tipoProcedimentoRepositorio;

	@GetMapping("/administrativo/agenda/cadastrar")
	public ModelAndView cadastrar(Agenda agenda) {
		ModelAndView mv = new ModelAndView("administrativo/agenda/cadastro");
		mv.addObject("agenda", agenda);
		mv.addObject("listaFuncionarios", funcionarioRepositorio.findAll());
		mv.addObject("listaPacientes", pacienteRepositorio.findAll());
		mv.addObject("listaTipoProcedimentos", tipoProcedimentoRepositorio.findAll());
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
	public ModelAndView salvar(@Valid Agenda agenda, BindingResult result, HttpSession session) throws ParseException {
		try {
			Date dataAgendamento = agenda.getDataAgendamento();
			String horaAgendamento = agenda.getHoraAgendamento();
			Date dataAtual = new Date();
			
			//Obrigado CNM e  Cardoso
	        GregorianCalendar gcHoraAgendamento = new GregorianCalendar();
	        gcHoraAgendamento.setTime(dataAgendamento);
	        String[] horaAgenda = horaAgendamento.split(":");
	        gcHoraAgendamento.set(GregorianCalendar.HOUR_OF_DAY, Integer.parseInt(horaAgenda[0]));
	        gcHoraAgendamento.set(GregorianCalendar.MINUTE, Integer.parseInt(horaAgenda[1]));
	        Date dataAgendamentoComHora = gcHoraAgendamento.getTime();
	        
	        List<Agenda> agenda1 = agendaRepositorio.consultaAgendamento(agenda.getDataAgendamento(), agenda.getHoraAgendamento());
	        if (dataAgendamentoComHora.getTime() > dataAtual.getTime() && agenda1.isEmpty()) {
	        	agendaRepositorio.saveAndFlush(agenda);
	        	session.setAttribute("mensagem", new Mensagem("AGENDAMENTO cadastrado com SUCESSO!!!", "success"));
	        } else {
	        	session.setAttribute("mensagem", new Mensagem("Ops! Algo deu errado.., tente novamente!", "danger"));
	        }
		} catch (Exception e) {
			if (result.hasErrors()) {
				return cadastrar(agenda);
			}
		}
		return cadastrar(new Agenda());
	}

}
