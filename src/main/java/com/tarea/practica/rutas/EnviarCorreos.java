package com.tarea.practica.rutas;

import com.sendgrid.*;
import com.tarea.practica.entidades.Actividad;
import com.tarea.practica.entidades.Gerente;
import com.tarea.practica.servicios.ActividadServices;
import com.tarea.practica.servicios.GerenteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EnviarCorreos {

    @Autowired
    private GerenteServices gerenteServices;

    @Autowired
    private ActividadServices actividadServices;

    @Scheduled(fixedRate = 10000)
    public void enviarCorreos() throws IOException {

        for (Gerente gerente : gerenteServices.gerenteList()) {

            for (Actividad actividad : gerente.getActividades()) {


                if (!actividad.getEnviado()) {
//
                    Email from = new Email("regiedual@gmail.com");
                    String subject = "Recordatorio de actividad";
                    Email to = new Email(gerente.getCorreos());
                    Content content = new Content("text/plain", actividad.getNombre() + " \n\n\n " + actividad.getDetalles());
                    Mail mail = new Mail(from, subject, to, content);

                    SendGrid sg = new SendGrid("");
                    Request request = new Request();
                    try {
                        request.setMethod(Method.POST);
                        request.setEndpoint("mail/send");
                        request.setBody(mail.build());
                        Response response = sg.api(request);
                        System.out.println(response.getStatusCode());
                        System.out.println(response.getBody());
                        System.out.println(response.getHeaders());
                    } catch (IOException ex) {
                        throw ex;
                    }

                  System.out.println("encontrado " + actividad.getNombre());
                  //  actividad.setEnviado(true);
                  //  actividadServices.crearActividad(actividad);

                }





            }
        }


    }
}
