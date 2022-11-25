package com.clinisys.clinisys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.clinisys.clinisys.model.Cidade;
import com.clinisys.clinisys.model.Mensagem;
import com.clinisys.clinisys.repository.CidadeRepositorio;
import com.clinisys.clinisys.repository.EstadoRepositorio;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class CidadeControle {

	@Autowired
	private CidadeRepositorio cidadeRepositorio;

	@Autowired
	private EstadoRepositorio estadoRepositorio;

	@GetMapping("/administrativo/cidades/cadastrar")
	public ModelAndView cadastrar(Cidade cidade) {
		ModelAndView mv = new ModelAndView("administrativo/cidades/cadastro");
		mv.addObject("cidade", cidade);
		mv.addObject("listaEstados", estadoRepositorio.findAll());
		return mv;
	}

	@GetMapping("/administrativo/cidades/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/cidades/lista");
		mv.addObject("listaCidades", cidadeRepositorio.findAll());
		return mv;
	}

	@GetMapping("/administrativo/cidades/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Cidade> cidade = cidadeRepositorio.findById(id);
		return cadastrar(cidade.get());
	}

	@GetMapping("/administrativo/cidades/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Cidade> cidade = cidadeRepositorio.findById(id);
		cidadeRepositorio.delete(cidade.get());
		return listar();
	}

	@PostMapping("/administrativo/cidades/salvar")
	public ModelAndView salvar(@Valid Cidade cidade, BindingResult result, HttpSession session) {
		
		try {
			List<Cidade> cidade1 = cidadeRepositorio.consultaCidade(cidade.getEstado(), cidade.getNome());
			if(cidade1.isEmpty() ) {
				 cidadeRepositorio.saveAndFlush(cidade);
				 session.setAttribute("mensagem", new Mensagem("CIDADE cadastrada com SUCESSO!!!", "success"));
			}else {
				session.setAttribute("mensagem", new Mensagem("Ops! Algo deu ERRADO.., tente novamente!", "danger"));
			}
		} catch (Exception e) {
			if (result.hasErrors()) {
				return cadastrar(cidade);
			}
		}
		

		return cadastrar(new Cidade());
	}
}
