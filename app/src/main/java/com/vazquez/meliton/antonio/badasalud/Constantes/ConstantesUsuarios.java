package com.vazquez.meliton.antonio.badasalud.Constantes;

public class ConstantesUsuarios {
    /**
     * Transición Home -> Detalle
     */
    public static final int CODIGO_DETALLE = 100;

    /**
     * Transición Detalle -> Actualización
     */
    public static final int CODIGO_ACTUALIZACION = 101;

    /**
     * Puerto que utilizas para la conexión.
     * Dejalo en blanco si no has configurado esta carácteristica.
     */
    private static final String PUERTO_HOST = "";

    /**
     * Dirección IP de genymotion o AVD
     */
    private static final String IP = "http://badasalud.es/";

    //0.156
    /**
     * URLs del Web Service
     */
    public static final String GET = IP + PUERTO_HOST + "/webservice/usuarios/get_usuarios.php";
    public static final String GET_BY_ID = IP + PUERTO_HOST + "/webservice/usuarios/get_usuarios_by_id.php";
    public static final String UPDATE = IP + PUERTO_HOST + "/webservice/usuarios/update_usuarios.php";
    public static final String DELETE = IP + PUERTO_HOST + "/webservice/usuarios/delete_usuarios.php";
    public static final String INSERT = IP + PUERTO_HOST + "/webservice/usuarios/insert_usuarios.php";


    /**
     * Clave para el valor extra que representa al identificador de un mensaje
     */
    public static final String EXTRA_ID = "IDEXTRA";

}