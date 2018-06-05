package com.vazquez.meliton.antonio.badasalud.entidad;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {

    //inicializamos variables
    private int id;
    private String nombre;
    private String apellidos;
    private int telefono;
    private String email;
    private String password;
    private ArrayList<String> usuarios;


    //creo constructor
    public Usuario(int id, String nombre, String apellidos, int telefono, String email, String password, ArrayList<String> usuarios) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.usuarios = usuarios;
    }

    //genero constructor vacio
    public Usuario() {

    }


    //getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<String> usuarios) {
        this.usuarios = usuarios;
    }
}
