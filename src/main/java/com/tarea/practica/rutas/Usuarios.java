package com.tarea.practica.rutas;

import com.tarea.practica.entidades.Correo;
import com.tarea.practica.entidades.Gerente;
import com.tarea.practica.servicios.CorreoServices;
import com.tarea.practica.servicios.GerenteServices;
import com.vaadin.flow.component.button.Button;
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
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

@Route("usuarios")
public class Usuarios extends HorizontalLayout {

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

    Binder<Correo> correoBinder;

    public Usuarios(@Autowired GerenteServices gerenteServices) {
        this.gerenteServices = gerenteServices;

        correoBinder = new Binder<>();
        binderGerente = new Binder<>();
        Menu menu = new Menu();
        gerente = (Gerente) VaadinSession.getCurrent().getAttribute("usuario");
        correoTextfield = new TextField("Correo");


        actualizarNombres = new Button("Actualizar", new Icon(VaadinIcon.REFRESH));
        actualizarNombres.addClickListener(event -> {

            gerente.setNombre(nombre.getValue());
            gerente.setApellido(apellido.getValue());
            gerente.setCorreos(correoTextfield.getValue());

            gerenteServices.crearGerente(gerente);

            new Notification("Usuario actualizado!").open();

        });




        tabla = new Grid<>();
        tabla.addColumn(Correo::getCorreo).setHeader("Correos");

        HorizontalLayout verticalLayout = new HorizontalLayout();
        VerticalLayout verticalLayout1 = new VerticalLayout(new H3("Nuevo usuario"), new HorizontalLayout(tabla));

        nombre = new TextField("Nombre");
        apellido = new TextField("Apellido");

        verticalLayout.add(nombre, apellido, correoTextfield,actualizarNombres);

        button = new Button("Actualizar");


        cargarDatos();
        add(menu);
        add(verticalLayout1);
    }

    public void cargarDatos() {



    }

}
