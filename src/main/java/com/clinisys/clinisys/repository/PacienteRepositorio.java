package com.clinisys.clinisys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinisys.clinisys.model.Paciente;

public interface PacienteRepositorio extends JpaRepository<Paciente, Long>{

}
