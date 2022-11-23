package com.clinisys.clinisys.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clinisys.clinisys.model.Agenda;


public interface AgendaRepositorio extends JpaRepository<Agenda, Long>{
	
	@Query("SELECT ag FROM agenda ag WHERE ag.dataAgendamento = :dataHoje")
	public List<Agenda> consultaAgendaHoje(@Param("dataHoje") Date dataHoje);
	
}
