package com.tarea.practica.servicios;

import com.tarea.practica.entidades.Actividad;
import com.tarea.practica.repositorios.ActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ActividadServices {

    @Autowired
    private ActividadRepository actividadRepository;

    @Transactional
    public void crearDefault() {

        Actividad actividad = new Actividad(Date.from(Instant.now()), "Demo", "prueba para video");


        actividadRepository.save(actividad);
    }

    public List<Actividad> buscarTodos() {
        return actividadRepository.findAll();
    }

    @Transactional
    public void crearActividad(Actividad actividad) {

        actividadRepository.save(actividad);
    }

    public Actividad buscarId(Long id) {

        Optional<Actividad> actividad = actividadRepository.findById(id);

        return actividad.orElse(null);
    }

    public void eliminarActividad(Actividad actividad){
        actividadRepository.delete(actividad);
    }
}
