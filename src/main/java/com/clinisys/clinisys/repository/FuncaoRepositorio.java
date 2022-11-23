package com.clinisys.clinisys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clinisys.clinisys.model.Funcao;

public interface FuncaoRepositorio extends JpaRepository<Funcao, Long>{

	@Query("SELECT fun FROM funcao fun WHERE fun.nome = :nome")
	public List<Funcao> consultaFuncao(@Param("nome") String nome);
}
