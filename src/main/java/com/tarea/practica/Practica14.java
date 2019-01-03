package com.tarea.practica;

import com.tarea.practica.servicios.GerenteServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Practica14 {

    public static void main(String[] args) {
     ApplicationContext context = SpringApplication.run(Practica14.class, args);

        GerenteServices gerenteServices = (GerenteServices) context.getBean("gerenteServices");
        gerenteServices.crearAdmin();
    }
}
