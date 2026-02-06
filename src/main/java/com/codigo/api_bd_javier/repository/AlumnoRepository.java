package com.codigo.api_bd_javier.repository;

import com.codigo.api_bd_javier.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlumnoRepository extends JpaRepository<Alumno, UUID> {

}
