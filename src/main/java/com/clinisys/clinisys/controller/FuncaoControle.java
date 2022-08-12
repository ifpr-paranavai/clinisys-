package com.clinisys.clinisys.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.clinisys.clinisys.model.Funcao;
import com.clinisys.clinisys.repository.FuncaoRepositorio;



@Controller
public class FuncaoControle {
	
	@Autowired
	private FuncaoRepositorio funcaoRepositorio;
	
	@GetMapping("/administrativo/funcoes/cadastrar")
	public ModelAndView cadastrar(Funcao funcao) {
		ModelAndView mv = new ModelAndView("administrativo/funcoes/cadastro");
		mv.addObject("funcao", funcao);
		return mv;
	}
		
	@GetMapping("/administrativo/funcoes/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/funcoes/lista");
		mv.addObject("listaFuncoes", funcaoRepositorio.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/funcoes/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Funcao> funcao = funcaoRepositorio.findById(id);
		return cadastrar(funcao.get());
	}

	
	@GetMapping("/administrativo/funcoes/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Funcao> funcao = funcaoRepositorio.findById(id);
		funcaoRepositorio.delete(funcao.get());
		return listar();
	}
	
	@PostMapping("/administrativo/funcoes/salvar")
	public ModelAndView salvar(@Valid Funcao funcao, BindingResult result) {
		if(result.hasErrors()) {
			return cadastrar(funcao);
		}
		funcaoRepositorio.saveAndFlush(funcao);
		
		return cadastrar(new Funcao());
	}
}
