package com.tarea.practica.rutas;

import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;

public class  Menu extends HorizontalLayout {

    public Menu() {


        RouterLink calendario1 = new RouterLink("Calendario", Principal.class);
        Label h2 = new Label("Opciones");

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.setTarget(h2);
        contextMenu.setOpenOnClick(true);
        contextMenu.addItem("Configurar", event -> {

            if (getUI().isPresent())
                getUI().get().navigate(Opciones.class);
        });
        contextMenu.addItem("Salir", event -> {

            VaadinSession.getCurrent().setAttribute("usuario", null);
            if (getUI().isPresent())
                getUI().get().navigate(LogIn.class);

        });


        VerticalLayout verticalLayout1 = new VerticalLayout();
        verticalLayout1.setSizeFull();
        add(calendario1, verticalLayout1, h2);
        setSizeFull();

    }
}
