package com.clinisys.clinisys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clinisys.clinisys.model.Estado;


public interface EstadoRepositorio extends JpaRepository<Estado, Long>{

	@Query("SELECT est FROM estado est WHERE est.sigla = :sigla AND est.nome = :nome")
	public List<Estado> consultaEstado(@Param("sigla") String sigla , @Param("nome") String nome);
}
