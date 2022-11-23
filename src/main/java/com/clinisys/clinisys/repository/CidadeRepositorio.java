package com.clinisys.clinisys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clinisys.clinisys.model.Cidade;
import com.clinisys.clinisys.model.Estado;


public interface CidadeRepositorio extends JpaRepository<Cidade, Long>{
	
	@Query("SELECT cid FROM cidade cid WHERE cid.estado = :estado AND cid.nome = :nome")
	public List<Cidade> consultaCidade(@Param("estado") Estado estado , @Param("nome") String nomeCidade);
}
