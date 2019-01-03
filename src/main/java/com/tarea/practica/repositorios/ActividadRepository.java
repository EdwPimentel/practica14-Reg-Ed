package com.tarea.practica.repositorios;

import com.tarea.practica.entidades.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {

    Optional<Actividad> findById(Long id);

}
