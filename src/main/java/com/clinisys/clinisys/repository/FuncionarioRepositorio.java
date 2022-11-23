package com.clinisys.clinisys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clinisys.clinisys.model.Funcionario;

public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long>{

	@Query("SELECT func FROM funcionario func WHERE func.cpf = :cpf")
	public List<Funcionario> consultaFuncionario(@Param("cpf") String cpf);
}
