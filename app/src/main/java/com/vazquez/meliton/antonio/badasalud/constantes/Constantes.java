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
    public static final String GET_USUARIO = IP + PUERTO_HOST + "webservice/usuarios/get_usuario.php";
    public static final String GET_USUARIO_BY_ID = IP + PUERTO_HOST + "webservice/usuarios/get_usuario_by_id.php";
    public static final String UPDATE_USUARIO = IP + PUERTO_HOST + "webservice/usuarios/update_usuario.php";
    public static final String DELETE_USUARIO = IP + PUERTO_HOST + "webservice/usuarios/delete_usuario.php";
    public static final String INSERT_USUARIO = IP + PUERTO_HOST + "webservice/usuarios/insert_usuario.php";
    public static final String LOGIN = IP + PUERTO_HOST + "webservice/conexion/login.php";



    //CITAS
    public static final String GET_CITA = IP + PUERTO_HOST + "webservice/citas/get_cita.php";
    public static final String GET_CITA_BY_ID = IP + PUERTO_HOST + "webservice/citas/get_cita_by_id.php";
    public static final String GET_CITA_BY_USERID = IP + PUERTO_HOST + "webservice/citas/get_cita_by_usuario.php";
    public static final String UPDATE_CITA = IP + PUERTO_HOST + "webservice/citas/update_cita.php";
    public static final String DELETE_CITA = IP + PUERTO_HOST + "webservice/citas/delete_cita.php";
    public static final String INSERT_CITA = IP + PUERTO_HOST + "webservice/citas/insert_cita.php";


    //Especialidades
    public static final String GET_ESPECIALIDADES = IP + PUERTO_HOST + "webservice/especialidades/get_especialidad.php";
    //no es necesario usar más dado que las especialidades están ingresados directamente desde base de datos,
    //no hay opción de modificado, y eliminado, y si hubiera que insertar
    //se haría desde base de datos
    /*public static final String UPDATE = IP + PUERTO_HOST + "webservice/especialidades/update_especialidades.php";
    public static final String DELETE = IP + PUERTO_HOST + "webservice/especialidades/delete_especialidades.php";
    public static final String INSERT = IP + PUERTO_HOST + "webservice/especialidades/insert_especialidades.php";*/
    public static final String GET_ESPECIALIDADES_BY_ID = IP + PUERTO_HOST + "webservice/especialidades/get_especialidad_by_id.php";


    //Hospitales
    public static final String GET_HOSPITALES = IP + PUERTO_HOST + "webservice/hospitales/get_hospital.php";
    //no es necesario usar más dado que los hospitales están ingresados directamente desde base de datos,
    //no hay opción de modificado, y eliminado, y si hubiera que insertar
    //se haría desde base de datos
    /*public static final String UPDATE = IP + PUERTO_HOST + "webservice/hospitales/update_hospitales.php";
    public static final String DELETE = IP + PUERTO_HOST + "webservice/hospitales/delete_hospitales.php";
    public static final String INSERT = IP + PUERTO_HOST + "webservice/hospitales/insert_hospitales.php";*/
    public static final String GET_HOSPITALES_BY_ID = IP + PUERTO_HOST + "webservice/hospitales/get_hospital_by_id.php";

}