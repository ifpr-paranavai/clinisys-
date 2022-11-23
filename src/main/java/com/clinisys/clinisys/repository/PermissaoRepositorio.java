package com.clinisys.clinisys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clinisys.clinisys.model.Funcao;
import com.clinisys.clinisys.model.Funcionario;
import com.clinisys.clinisys.model.Permissao;

public interface PermissaoRepositorio extends JpaRepository<Permissao, Long>{
	
	@Query("SELECT per FROM permissao per WHERE per.funcionario = :funcionario AND per.funcao = :funcao")
	public List<Permissao> consultaPermissao(@Param("funcionario") Funcionario funcionario , @Param("funcao") Funcao funcao);
}
