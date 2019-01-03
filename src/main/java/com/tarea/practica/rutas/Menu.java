package com.tarea.practica.rutas;

import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;

public class Menu extends HorizontalLayout {

    public Menu() {

        ContextMenu menu = new ContextMenu();

        Label menuTitulo = new Label("Menu");




        RouterLink calendario1 = new RouterLink("Calendario", Principal.class);
        Label h2 = new Label("Menu");

        menu.setTarget(menuTitulo);
//        contextMenu.setTarget(h2);
        menu.setOpenOnClick(true);


        VerticalLayout verticalLayout1 = new VerticalLayout();
        verticalLayout1.setSizeFull();

        menu.addItem("Calendario", event -> {

            if (getUI().isPresent())
                getUI().get().navigate(Principal.class);
        });

        menu.addItem("Usuarios", event -> {
            if (getUI().isPresent())
                getUI().get().navigate(Usuarios.class);
        });

        menu.addItem("Opciones", event -> {
            if (getUI().isPresent())
                getUI().get().navigate(Opciones.class);
        });

        menu.addItem("Salir", event -> {
            if (getUI().isPresent())
                getUI().get().navigate(Principal.class);
        });

        add(menuTitulo);
//        add(calendario1, verticalLayout1, h2);
        setSizeFull();

    }
}
