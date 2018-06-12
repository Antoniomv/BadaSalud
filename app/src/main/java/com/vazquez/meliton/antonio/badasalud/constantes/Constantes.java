package com.vazquez.meliton.antonio.badasalud.constantes;

public class Constantes {
    /**
     * Transición Home -> Detalle
     */
    public static final int CODIGO_DETALLE = 100;

    /**
     * Transición Detalle -> Actualización
     */
    public static final int CODIGO_ACTUALIZACION = 101;
    /**
     * Clave para el valor extra que representa al identificador de un mensaje
     */
    public static final String EXTRA_ID = "IDEXTRA";
    /**
     * Puerto que utilizas para la conexión.
     * Dejalo en blanco si no has configurado esta carácteristica.
     */
    private static final String PUERTO_HOST = "";

    //0.156
    /**
     * Dirección IP de genymotion o AVD
     */
    private static final String IP = "http://badasalud.es/";
    /**
     * URLs del Web Service
     */

    //usuarios
    public static final String UPDATE_USUARIO = IP + PUERTO_HOST + "webservice/conexion/update_usuario.php";
    public static final String INSERT_USUARIO = IP + PUERTO_HOST + "webservice/conexion/registro.php";
    public static final String LOGIN = IP + PUERTO_HOST + "webservice/conexion/login.php";



    //CITAS
    public static final String GET_CITA = IP + PUERTO_HOST + "webservice/citas/get_cita.php";
    public static final String GET_CITA_BY_ID = IP + PUERTO_HOST + "webservice/citas/get_cita_by_id.php?id=";
        public static final String DELETE_CITA = IP + PUERTO_HOST + "webservice/citas/delete_cita.php";
    public static final String INSERT_CITA = IP + PUERTO_HOST + "webservice/citas/insert_cita.php";


    //Especialidades
    public static final String GET_ESPECIALIDADES = IP + PUERTO_HOST + "webservice/especialidades/get_especialidad.php";
    public static final String GET_ESPECIALIDADES_BY_ID = IP + PUERTO_HOST + "webservice/especialidades/get_especialidad_by_id.php";


    //Hospitales
    public static final String GET_HOSPITALES = IP + PUERTO_HOST + "webservice/hospitales/get_hospital.php";
    public static final String GET_HOSPITALES_BY_ID = IP + PUERTO_HOST + "webservice/hospitales/get_hospital_by_id.php";

}