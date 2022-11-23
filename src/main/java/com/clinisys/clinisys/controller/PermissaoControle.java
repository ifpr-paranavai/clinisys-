package com.clinisys.clinisys.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.clinisys.clinisys.model.Permissao;
import com.clinisys.clinisys.repository.FuncaoRepositorio;
import com.clinisys.clinisys.repository.FuncionarioRepositorio;
import com.clinisys.clinisys.repository.PermissaoRepositorio;

@Controller
public class PermissaoControle {

	@Autowired
	private PermissaoRepositorio permissaoRepositorio;

	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;

	@Autowired
	private FuncaoRepositorio funcaoRepositorio;

	@GetMapping("/administrativo/permissoes/cadastrar")
	public ModelAndView cadastrar(Permissao permissao) {
		ModelAndView mv = new ModelAndView("administrativo/permissoes/cadastro");
		mv.addObject("permissao", permissao);
		mv.addObject("listaFuncionarios", funcionarioRepositorio.findAll());
		mv.addObject("listaFuncoes", funcaoRepositorio.findAll());
		return mv;
	}

	@GetMapping("/administrativo/permissoes/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/permissoes/lista");
		mv.addObject("listaPermissoes", permissaoRepositorio.findAll());
		return mv;
	}

	@GetMapping("/administrativo/permissoes/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Permissao> permissao = permissaoRepositorio.findById(id);
		return cadastrar(permissao.get());
	}

	@GetMapping("/administrativo/permissoes/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Permissao> permissao = permissaoRepositorio.findById(id);
		permissaoRepositorio.delete(permissao.get());
		return listar();
	}

	@PostMapping("/administrativo/permissoes/salvar")
	public ModelAndView salvar(@Valid Permissao permissao, BindingResult result) {
		if (result.hasErrors()) {
			return cadastrar(permissao);
		}
		List<Permissao> permissao1 = permissaoRepositorio.consultaPermissao(permissao.getFuncionario(),
				permissao.getFuncao());
		if (permissao1.isEmpty()) {
			permissaoRepositorio.saveAndFlush(permissao);
		}

		return cadastrar(new Permissao());
	}
}
