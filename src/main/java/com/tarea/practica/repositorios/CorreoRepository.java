package com.tarea.practica.repositorios;

import com.tarea.practica.entidades.Correo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorreoRepository extends JpaRepository<Correo, Long> {
}
