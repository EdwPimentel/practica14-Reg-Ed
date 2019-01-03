package com.tarea.practica.rutas;

import com.sun.xml.internal.ws.api.pipe.Fiber;
import com.tarea.practica.entidades.Actividad;
import com.tarea.practica.servicios.ActividadServices;
import com.tarea.practica.servicios.GerenteServices;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.LocalDateToDateConverter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.Command;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DateTimeField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.vaadin.addon.calendar.Calendar;
import org.vaadin.calendar.CalendarComponent;
import org.vaadin.calendar.data.AbstractCalendarDataProvider;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;


@Route("calendario")
public class Principal extends VerticalLayout {

    Binder<Actividad> actividadBinder;

    private ActividadServices actividadServices;
    private GerenteServices gerenteServices;

    private TextField nombre;
    private TextArea detalles;
    private DatePicker fecha;
    private DateTimeField hora;


    private Button ok;
    private Button actualizar;
    private Button eliminar;


    public Principal(@Autowired ActividadServices actividadServices, @Autowired GerenteServices gerenteServices) {

        this.gerenteServices = gerenteServices;
        this.actividadServices = actividadServices;

        nombre = new TextField("Nombre");
        detalles = new TextArea("Detalles");
        fecha = new DatePicker("Fecha de actividad");

        eliminar = new Button("Eliminar", new Icon(VaadinIcon.MINUS));
        actividadBinder = new Binder<>();

        Menu menu = new Menu();

        actividadBinder.forField(nombre).asRequired("El nombre es necesario").bind(Actividad::getNombre, Actividad::setNombre);
        actividadBinder.forField(detalles).bind(Actividad::getDetalles, Actividad::setDetalles);
        actividadBinder.forField(fecha).withConverter(new LocalDateToDateConverter()).bind(Actividad::getInicio, Actividad::setInicio);


        CalendarComponent<Actividad> calendario = new CalendarComponent<>();

        calendario.withItemDateGenerator(Actividad::getInicio);
        calendario.withItemLabelGenerator(Actividad::getNombre);

        gerenteServices.crearAdmin();

        calendario.setItems(actividadServices.buscarTodos());

        Dialog dialog = new Dialog();

        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        ok = new Button("OK", event -> {

            try {


                Actividad actividad = new Actividad();

                actividadBinder.writeBean(actividad);

                actividadServices.crearActividad(actividad);
                calendario.setItems(actividadServices.buscarTodos());

                new Notification("Nueva actividad creada", 3000, Notification.Position.BOTTOM_END).open();

                if (dialog.isOpened())
                    dialog.close();


            } catch (Exception e) {

            }
        });
        actualizar = new Button("Actualizar", new Icon(VaadinIcon.REFRESH));
        actualizar.setVisible(false);
        eliminar.setVisible(false);
        horizontalLayout.add(ok, actualizar, eliminar);
        horizontalLayout.setAlignItems(Alignment.END);
        Label label = new Label("Nueva actividad");
        verticalLayout.add(label, nombre, detalles, fecha, horizontalLayout);


        dialog.add(verticalLayout);

        calendario.addEventClickListener(event -> {

            label.setVisible(false);
            Actividad actividadVieja = actividadServices.buscarId(event.getDetail().getId());

            nombre.setValue(event.getDetail().getNombre());
            detalles.setValue(event.getDetail().getDetalles());
            LocalDate date = event.getDetail().getInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            fecha.setValue(date);

            actualizar.setVisible(true);
            eliminar.setVisible(true);
            ok.setVisible(false);

            dialog.open();
//            new Notification(event.getDetail().getNombre(), 2000, Notification.Position.TOP_END).open();

            actualizar.addClickListener(e -> {

                Date fechaVieja = Date.from(fecha.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

                actividadVieja.setDetalles(detalles.getValue());
                actividadVieja.setNombre(nombre.getValue());
                actividadVieja.setInicio(fechaVieja);

                actividadServices.crearActividad(actividadVieja);

                dialog.close();

                calendario.setItems(actividadServices.buscarTodos());


            });

            eliminar.addClickListener(event1 -> {

                actividadServices.eliminarActividad(actividadVieja);
                new Notification("Actividad eliminada").open();
                dialog.close();

                calendario.setItems(actividadServices.buscarTodos());


            });


        });


        Button button = new Button("Nueva actividad", event -> {

            if (dialog.isOpened()) {

                dialog.close();
            } else {

                nombre.clear();
                detalles.clear();
                fecha.clear();
                label.setVisible(true);
                ok.setVisible(true);
                actualizar.setVisible(false);
                eliminar.setVisible(false);
                dialog.open();
            }
        });

        HorizontalLayout nav = new HorizontalLayout();


        nav.add(button);
        add(menu);
        add(nav);
        add(calendario);


    }


}
