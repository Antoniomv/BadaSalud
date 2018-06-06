package com.vazquez.meliton.antonio.badasalud.entidad;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Cita implements Serializable {

    //creamos variables
    private int id;
    private String titulo;
    private String fecha;
    private String hora;
    private int usuario_id;
    private int especialidad_id;
    private int hospital_id;
    private ArrayList<String> citas;

    //llenamos constructor


    public Cita(int id, String titulo, String fecha, String hora, int usuario_id, int especialidad_id, int hospital_id, ArrayList<String> citas) {
        this.id = id;
        this.titulo = titulo;
        this.fecha = fecha;
        this.hora = hora;
        this.usuario_id = usuario_id;
        this.especialidad_id = especialidad_id;
        this.hospital_id = hospital_id;
        this.citas = citas;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getEspecialidad_id() {
        return especialidad_id;
    }

    public void setEspecialidad_id(int especialidad_id) {
        this.especialidad_id = especialidad_id;
    }

    public int getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }

    public ArrayList<String> getCitas() {
        return citas;
    }

    public void setCitas(ArrayList<String> citas) {
        this.citas = citas;
    }
}
