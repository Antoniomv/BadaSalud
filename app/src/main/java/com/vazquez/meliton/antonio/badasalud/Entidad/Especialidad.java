package com.vazquez.meliton.antonio.badasalud.Entidad;

import java.io.Serializable;
import java.util.ArrayList;

public class Especialidad implements Serializable {

    //creo variables
    private int id;
    private String especialidad;
    private ArrayList<String> especialidades;

    //lleno constructor
    public Especialidad(int id, String especialidad, ArrayList<String> especialidades) {
        this.id = id;
        this.especialidad = especialidad;
        this.especialidades = especialidades;
    }

    //creo constructor vacio
    public Especialidad() {

    }


    //Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public ArrayList<String> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(ArrayList<String> especialidades) {
        this.especialidades = especialidades;
    }
}
