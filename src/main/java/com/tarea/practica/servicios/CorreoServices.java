package com.tarea.practica.servicios;

import com.tarea.practica.entidades.Correo;
import com.tarea.practica.repositorios.CorreoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CorreoServices {

    @Autowired
    private CorreoRepository correoRepository;

    @Transactional
    public void crearCorreo(Correo correo){

        correoRepository.save(correo);
    }

    public void eliminarCorreo(Correo correo){

        correoRepository.delete(correo);
    }
}
