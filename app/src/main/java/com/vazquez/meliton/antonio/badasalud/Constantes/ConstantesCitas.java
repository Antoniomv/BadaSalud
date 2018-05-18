package com.vazquez.meliton.antonio.badasalud.Constantes;

public class ConstantesCitas {
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
    public static final String GET = IP + PUERTO_HOST + "/webservice/citas/get_citas.php";
    public static final String GET_BY_ID = IP + PUERTO_HOST + "/webservice/citas/get_citas_by_id.php";
    public static final String UPDATE = IP + PUERTO_HOST + "/webservice/citas/update_citas.php";
    public static final String DELETE = IP + PUERTO_HOST + "/webservice/citas/delete_citas.php";
    public static final String INSERT = IP + PUERTO_HOST + "/webservice/citas/insert_citas.php";


    /**
     * Clave para el valor extra que representa al identificador de un mensaje
     */
    public static final String EXTRA_ID = "IDEXTRA";
}
