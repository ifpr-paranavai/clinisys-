package com.clinisys.clinisys.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "tipoProcedimento")
@Table(name = "tipoProcedimento")
public class TipoProcedimento implements Serializable {

	public TipoProcedimento() {
		super();
	}

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String descricaoProcedimento;
	private String valorProcedimento;
	@ManyToOne
	private Funcionario funcionario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoProcedimento() {
		return descricaoProcedimento;
	}

	public void setDescricaoProcedimento(String descricaoProcedimento) {
		this.descricaoProcedimento = descricaoProcedimento;
	}

	public String getValorProcedimento() {
		return valorProcedimento;
	}

	public void setValorProcedimento(String valorProcedimento) {
		this.valorProcedimento = valorProcedimento;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		return descricaoProcedimento + "-"+funcionario.getNome();
	}
	
}
