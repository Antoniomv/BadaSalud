package com.vazquez.meliton.antonio.badasalud.entidad;

import java.io.Serializable;
import java.util.ArrayList;

public class Hospital implements Serializable {


    //creo variables
    private int id;
    private String nombre;
    private String direccion;
    private int telefono;
    private int especialidad_id;
    private ArrayList<String> hospitales;

    //lleno constructor
    public Hospital(int id, String nombre, String direccion, int telefono, int especialidad_id, ArrayList<String> hospitales) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.especialidad_id = especialidad_id;
        this.hospitales = hospitales;
    }

    //creo constructor vacio
    public Hospital() {

    }

    //Getters & Setters
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getEspecialidad_id() {
        return especialidad_id;
    }

    public void setEspecialidad_id(int especialidad_id) {
        this.especialidad_id = especialidad_id;
    }

    public ArrayList<String> getHospitales() {
        return hospitales;
    }

    public void setHospitales(ArrayList<String> hospitales) {
        this.hospitales = hospitales;
    }
}
