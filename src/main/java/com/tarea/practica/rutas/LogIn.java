package com.tarea.practica.rutas;

import com.tarea.practica.entidades.Gerente;
import com.tarea.practica.servicios.GerenteServices;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
public class LogIn extends VerticalLayout {

    TextField textField;
    PasswordField passwordField;
    Button button;

    public LogIn(@Autowired GerenteServices gerenteServices) {

        textField = new TextField("Usuario");
        passwordField = new PasswordField("Contraseña");
        button = new Button("Log in");

        textField.setRequired(true);
        passwordField.setRequired(true);
        passwordField.setRevealButtonVisible(true);

        button.addClickListener(event -> {

            if (textField.getValue().isEmpty()) {
                textField.setRequiredIndicatorVisible(true);
                textField.setInvalid(true);
            }

            if (passwordField.getValue().isEmpty()) {
                passwordField.setInvalid(true);
                passwordField.setRequiredIndicatorVisible(true);
            }

            if (!textField.isEmpty() && !passwordField.isEmpty()) {

                Gerente gerente = gerenteServices.login(textField.getValue(), passwordField.getValue());

                if (gerente != null) {

                    VaadinSession.getCurrent().setAttribute("usuario", gerente);
                    if (getUI().isPresent())
                        getUI().get().navigate(Principal.class);
                } else {

                    Dialog dialog = new Dialog();

                    dialog.add(new VerticalLayout(new H3("El usuario no fue encontrado!")));
                    dialog.open();



                    textField.setRequiredIndicatorVisible(true);
                    passwordField.setRequiredIndicatorVisible(true);

                }
            }

        });

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setAlignItems(Alignment.CENTER);
        horizontalLayout.setSizeFull();

        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.setAlignItems(Alignment.CENTER);
        verticalLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        verticalLayout.setAlignSelf(Alignment.CENTER, textField, passwordField);
        verticalLayout.setSizeFull();

        verticalLayout.add(textField, passwordField, button);

        horizontalLayout.add(verticalLayout);

        add(horizontalLayout);
    }

}
