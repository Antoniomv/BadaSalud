package com.vazquez.meliton.antonio.badasalud.Constantes;

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

    //Usuarios
    public static final String GET_USUARIO = IP + PUERTO_HOST + "/webservice/usuarios/get_usuarios.php";
    public static final String GET_USUARIO_BY_ID = IP + PUERTO_HOST + "/webservice/usuarios/get_usuarios_by_id.php";
    public static final String UPDATE_USUARIO = IP + PUERTO_HOST + "/webservice/usuarios/update_usuarios.php";
    public static final String DELETE_USUARIO = IP + PUERTO_HOST + "/webservice/usuarios/delete_usuarios.php";
    public static final String INSERT_USUARIO = IP + PUERTO_HOST + "/webservice/usuarios/insert_usuarios.php";


    //Citas
    public static final String GET_CITAS = IP + PUERTO_HOST + "/webservice/citas/get_citas.php";
    public static final String GET_CITAS_BY_ID = IP + PUERTO_HOST + "/webservice/citas/get_citas_by_id.php";
    public static final String UPDATE_CITAS = IP + PUERTO_HOST + "/webservice/citas/update_citas.php";
    public static final String DELETE_CITAS = IP + PUERTO_HOST + "/webservice/citas/delete_citas.php";
    public static final String INSERT_CITAS = IP + PUERTO_HOST + "/webservice/citas/insert_citas.php";


    //Especialidades
    public static final String GET_ESPECIALIDADES = IP + PUERTO_HOST + "/webservice/especialidades/get_especialidades.php";
    //no es necesario usar más dado que las especialidades están ingresados directamente desde base de datos,
    //no hay opción de modificado, y eliminado, y si hubiera que insertar
    //se haría desde base de datos
    /*public static final String UPDATE = IP + PUERTO_HOST + "/webservice/citas/update_especialidades.php";
    public static final String DELETE = IP + PUERTO_HOST + "/webservice/citas/delete_especialidades.php";
    public static final String INSERT = IP + PUERTO_HOST + "/webservice/citas/insert_especialidades.php";*/
    public static final String GET_ESPECIALIDADES_BY_ID = IP + PUERTO_HOST + "/webservice/especialidades/get_especialidades_by_id.php";


    //Hospitales
    public static final String GET_HOSPITALES = IP + PUERTO_HOST + "/webservice/hospitales/get_hospitales.php";
    //no es necesario usar más dado que los hospitales están ingresados directamente desde base de datos,
    //no hay opción de modificado, y eliminado, y si hubiera que insertar
    //se haría desde base de datos
    /*public static final String UPDATE = IP + PUERTO_HOST + "/webservice/citas/update_hospitales.php";
    public static final String DELETE = IP + PUERTO_HOST + "/webservice/citas/delete_hospitales.php";
    public static final String INSERT = IP + PUERTO_HOST + "/webservice/citas/insert_hospitales.php";*/
    public static final String GET_HOSPITALES_BY_ID = IP + PUERTO_HOST + "/webservice/hospitales/get_hospitales_by_id.php";

}