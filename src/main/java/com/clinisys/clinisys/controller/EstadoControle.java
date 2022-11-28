package com.clinisys.clinisys.controller;

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

import com.clinisys.clinisys.model.Estado;
import com.clinisys.clinisys.model.Mensagem;
import com.clinisys.clinisys.repository.EstadoRepositorio;

	
@Controller
public class EstadoControle {
	
	@Autowired
	private EstadoRepositorio estadoRepositorio;
	
	@GetMapping("/administrativo/estados/cadastrar")
	public ModelAndView cadastrar(Estado estado) {
		ModelAndView mv = new ModelAndView("administrativo/estados/cadastro");
		mv.addObject("estado", estado);
		return mv;  
	}
	
	@GetMapping("/administrativo/estados/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/estados/lista");
		mv.addObject("listaEstados", estadoRepositorio.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/estados/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Estado> estado = estadoRepositorio.findById(id);
		return cadastrar(estado.get());
	}
	
	@GetMapping("/administrativo/estados/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Estado> estado = estadoRepositorio.findById(id);
		estadoRepositorio.delete(estado.get());
		return listar();
	}
	
	@GetMapping("/administrativo/estados/listar/nome")
    public ModelAndView listarPorNomeEstado(String nome) {
        ModelAndView mv = new ModelAndView("administrativo/estados/lista");
        mv.addObject("listaEstados", estadoRepositorio.findByNomeEstado(nome));
        return mv;
    }
	
	@PostMapping("/administrativo/estados/salvar")
	public ModelAndView salvar(@Valid Estado estado, BindingResult result, HttpSession session) {
		
		try {
			List<Estado> estado1 = estadoRepositorio.consultaEstado(estado.getSigla(), estado.getNome());
			if(estado1.isEmpty() ) {
				 estadoRepositorio.saveAndFlush(estado);
				 session.setAttribute("mensagem", new Mensagem("ESTADO cadastrado com SUCESSO!!!", "success"));
			} else {
				session.setAttribute("mensagem", new Mensagem("Ops! Algo deu ERRADO..., tente novamente!", "danger"));
			}
			
		} catch (Exception e) {
			if(result.hasErrors()) {
				return cadastrar(estado);
			}
		}
		
		return cadastrar(new Estado());
	}
	

}
