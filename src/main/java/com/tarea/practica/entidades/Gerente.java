package com.tarea.practica.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
public class Gerente implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String apellido;

    private Date fechaNacimiento;

    private Boolean admin;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Actividad> actividades;

    private String usuario;

    private String password;

    private String correos;

    public Gerente() {
    }

    public Gerente(String nombre, String apellido, Date fechaNacimiento, Boolean admin, Set<Actividad> actividades, String usuario, String password, String correos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.admin = admin;
        this.actividades = actividades;
        this.usuario = usuario;
        this.password = password;
        this.correos = correos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Set<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(Set<Actividad> actividades) {
        this.actividades = actividades;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreos() {
        return correos;
    }

    public void setCorreos(String correos) {
        this.correos = correos;
    }
}

