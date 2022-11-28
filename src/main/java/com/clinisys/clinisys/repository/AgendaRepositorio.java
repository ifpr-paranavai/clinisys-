package com.clinisys.clinisys.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clinisys.clinisys.model.Agenda;
import com.clinisys.clinisys.model.Funcionario;



public interface AgendaRepositorio extends JpaRepository<Agenda, Long>{
	
	@Query("SELECT ag FROM agenda ag WHERE ag.dataAgendamento = :dataHoje")
	public List<Agenda> consultaAgendaHoje(@Param("dataHoje") Date dataHoje);
	
	@Query("SELECT ag FROM agenda ag WHERE ag.dataAgendamento = :dataAgendamento AND ag.horaAgendamento = :horaAgendamento")
	public List<Agenda> consultaAgendamento(@Param("dataAgendamento") Date dataAgendamento , @Param("horaAgendamento") String horaAgendamento);
	
	@Query("SELECT ag FROM agenda ag WHERE ag.funcionario =: funcionario")
	public List<Agenda> consultaPorTerapeuta(@Param("funcionario") Funcionario funcionario);
	
	@Query("FROM agenda ag where ag.horaAgendamento like %?1% ")
    List<Agenda> findByHora(String horaAgendamento);
	
}
