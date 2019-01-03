package com.tarea.practica.repositorios;

import com.tarea.practica.entidades.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Long> {

    Gerente findByNombre(String s);
    Gerente findByUsuarioAndPassword(String usuario, String password);
    List<Gerente> findAllByActividades_enviado(Boolean enviado);
}
