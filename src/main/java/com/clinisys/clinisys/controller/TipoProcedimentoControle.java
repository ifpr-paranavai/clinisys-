package com.clinisys.clinisys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.clinisys.clinisys.model.TipoProcedimento;
import com.clinisys.clinisys.repository.TipoProcedimentoRepositorio;
import com.clinisys.clinisys.repository.FuncionarioRepositorio;

import java.util.Optional;

import javax.validation.Valid;

@Controller
public class TipoProcedimentoControle {
	
	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;
	
	@Autowired
	private TipoProcedimentoRepositorio tipoProcedimentoRepositorio;

	@GetMapping("/administrativo/tipoProcedimento/cadastrar")
	public ModelAndView cadastrar(TipoProcedimento tipoProcedimento) {
		ModelAndView mv = new ModelAndView("administrativo/tipoProcedimento/cadastro");
		mv.addObject("tipoProcedimento", tipoProcedimento);
		mv.addObject("listaFuncionarios", funcionarioRepositorio.findAll());
		return mv;
	}

	@GetMapping("/administrativo/tipoProcedimento/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/tipoProcedimento/lista");
		mv.addObject("listaTipoProcedimentos", tipoProcedimentoRepositorio.findAll());
		return mv;
	}

	@GetMapping("/administrativo/tipoProcedimento/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<TipoProcedimento> tipoProcedimento = tipoProcedimentoRepositorio.findById(id);
		return cadastrar(tipoProcedimento.get());
	}

	@GetMapping("/administrativo/tipoProcedimento/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<TipoProcedimento> tipoProcedimento = tipoProcedimentoRepositorio.findById(id);
		tipoProcedimentoRepositorio.delete(tipoProcedimento.get());
		return listar();
	}

	@PostMapping("/administrativo/tipoProcedimento/salvar")
	public ModelAndView salvar(@Valid TipoProcedimento tipoProcedimento, BindingResult result) {
		if (result.hasErrors()) {
			return cadastrar(tipoProcedimento);
		}
		tipoProcedimentoRepositorio.saveAndFlush(tipoProcedimento);

		return cadastrar(new TipoProcedimento());
	}
}
