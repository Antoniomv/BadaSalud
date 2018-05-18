package com.vazquez.meliton.antonio.badasalud.Entidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Cita implements Serializable {

    //creamos variables
    private int id;
    private String nombre;
    private Date fechayhora;
    private int usuario_id;
    private int hospital_id;
    private ArrayList<String> hospitales;

    //llenamos constructor
    public Cita(int id, String nombre, Date fechayhora, int usuario_id, int hospital_id, ArrayList<String> hospitales) {
        this.id = id;
        this.nombre = nombre;
        this.fechayhora = fechayhora;
        this.usuario_id = usuario_id;
        this.hospital_id = hospital_id;
        this.hospitales = hospitales;
    }


    //genero constructor vacio
    public Cita() {
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

    public Date getFechayhora() {
        return fechayhora;
    }

    public void setFechayhora(Date fechayhora) {
        this.fechayhora = fechayhora;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }

    public ArrayList<String> getHospitales() {
        return hospitales;
    }

    public void setHospitales(ArrayList<String> hospitales) {
        this.hospitales = hospitales;
    }
}
