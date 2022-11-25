package com.clinisys.clinisys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clinisys.clinisys.model.TipoProcedimento;


public interface TipoProcedimentoRepositorio extends JpaRepository<TipoProcedimento, Long>{

	@Query("SELECT tipo FROM tipoProcedimento tipo WHERE tipo.descricaoProcedimento = :descricaoProcedimento")
	public List<TipoProcedimento> consultaTipoProcedimento(@Param("descricaoProcedimento") String descricaoProcedimento);
}
