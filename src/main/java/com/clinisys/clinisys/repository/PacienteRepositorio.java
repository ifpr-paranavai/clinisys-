package com.clinisys.clinisys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clinisys.clinisys.model.Paciente;

public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {
	
	@Query("SELECT pac FROM paciente pac WHERE pac.sexo = '1'")
	List<Paciente> listaPacienteMasculino(@Param("1") String sexo);
	
	@Query("SELECT pac FROM paciente pac WHERE pac.sexo = '2'")
	List<Paciente> listaPacienteFeminino(@Param("2") String sexo);
	
	@Query("SELECT pac FROM paciente pac WHERE pac.cpf = :cpf")
	public List<Paciente> consultaPaciente(@Param("cpf") String cpf);
	
	@Query("FROM paciente pac where pac.nome like %?1% ")
    List<Paciente> findByNomePaciente(String nome);

}
