package com.tarea.practica.servicios;

import com.tarea.practica.entidades.Actividad;
import com.tarea.practica.entidades.Correo;
import com.tarea.practica.entidades.Gerente;
import com.tarea.practica.repositorios.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class GerenteServices {

    @Autowired
    private GerenteRepository gerenteRepository;

    @Autowired
    private CorreoServices correoServices;

    @Autowired
    private ActividadServices actividadServices;

    @Transactional
    public void crearAdmin() {

        if (!buscarAdminDefault()) {


            Actividad actividad = new Actividad(Date.from(Instant.now()), "Demo", "detalle del demo");
            actividadServices.crearActividad(actividad);

            Gerente gerente = new Gerente("Regis", "Pimentel", Date.from(Instant.now()), true, new HashSet<>(Arrays.asList(actividad)), "admin", "admin","edwpgz@gmail.com");
            gerenteRepository.save(gerente);
        }

    }


    public Gerente login(String usuario, String password) {

        return gerenteRepository.findByUsuarioAndPassword(usuario, password);
    }

    @Transactional
    public void crearGerente(Gerente gerente) {
        gerenteRepository.save(gerente);
    }

    public boolean buscarAdminDefault() {

        Gerente gerente = gerenteRepository.findByNombre("Regis");

        return gerente != null;

    }

    public List<Gerente> gerenteList(){

        return gerenteRepository.findAllByActividades_enviado(false);
    }


}
