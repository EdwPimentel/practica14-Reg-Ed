package com.tarea.practica.rutas;

import com.tarea.practica.entidades.Correo;
import com.tarea.practica.entidades.Gerente;
import com.tarea.practica.servicios.CorreoServices;
import com.tarea.practica.servicios.GerenteServices;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

@Route("opciones")
public class Opciones extends HorizontalLayout {

    Binder<Gerente> binderGerente;
    Button button;
    Button editarCorreo;
    Button actualizarNombres;
    Button actualizarCorreo;
    Button nuevoCorreo;
    TextField nombre;
    TextField apellido;
    TextField correoTextfield;


    Grid<Correo> tabla;
    Gerente gerente;
    GerenteServices gerenteServices;
    CorreoServices correoServices;
    DataProvider<Correo, Void> dataProvider;

    Binder<Correo> correoBinder;

    public Opciones(@Autowired GerenteServices gerenteServices, @Autowired CorreoServices correoServices) {
        this.gerenteServices = gerenteServices;
        this.correoServices = correoServices;

        correoBinder = new Binder<>();
        binderGerente = new Binder<>();
        Menu menu = new Menu();
        gerente = (Gerente) VaadinSession.getCurrent().getAttribute("usuario");
        correoTextfield = new TextField("Correo");

//        nuevoCorreo = new Button("Agregar correo", new Icon(VaadinIcon.PLUS));

//        tabla = new Grid<>();
//        tabla.addColumn(Correo::getCorreo).setHeader("Correos");
//        tabla.setDataProvider(dataProvider);
//        tabla.setItems(gerente.getCorreos());

//        actualizarCorreo = new Button("OK");
//        tabla.addColumn(new ComponentRenderer<>(correo -> editarCorreo = new Button(new Icon(VaadinIcon.EDIT), event -> {
//
//            Dialog dialog = new Dialog();
//
//            dialog.add(new VerticalLayout(new H3("Editar correo"), correoTextfield, actualizarCorreo));
//            correoTextfield.setValue(correo.getCorreo());
//            dialog.open();
//
//            actualizarCorreo.addClickListener(editar -> {
//
//                if (correoTextfield.isEmpty()) {
//
//                    correoTextfield.setRequiredIndicatorVisible(true);
//                } else {
//
//                    correo.setCorreo(correoTextfield.getValue());
//                    correoServices.crearCorreo(correo);
//                    dialog.close();
//
//                    new Notification("Correo actualizado").open();
//
//                    dataProvider.refreshAll();
//                }
//
//
//            });
//
//
//        }))).setHeader("Editar");
//
//        tabla.addColumn(new ComponentRenderer<>(correo -> new Button(new Icon(VaadinIcon.TRASH), event -> {
//
//            gerente.getCorreos().remove(correo);
//            gerenteServices.crearGerente(gerente);
//
//            correoServices.eliminarCorreo(correo);
//
//            new Notification("Correo eliminado").open();
//
//            dataProvider.refreshAll();
//
//
//        }))).setHeader("Eliminar");

        actualizarNombres = new Button("Actualizar", new Icon(VaadinIcon.REFRESH));
        actualizarNombres.addClickListener(event -> {


        });
        VerticalLayout verticalLayout = new VerticalLayout();

        nombre = new TextField("Nombre");
        apellido = new TextField("Apellido");

        verticalLayout.add(nombre, apellido, correoTextfield,actualizarNombres);

        actualizarNombres.addClickListener(event -> {


        });

        button = new Button("Actualizar");


//        HorizontalLayout horizontalLayout1 = new HorizontalLayout(tabla);
//        horizontalLayout1.setSizeFull();

        cargarDatos();
        add(menu);

        add(verticalLayout);

    }

    public void cargarDatos() {

        nombre.setValue(gerente.getNombre());
        apellido.setValue(gerente.getApellido());
        correoTextfield.setValue(gerente.getCorreos());

    }

}
