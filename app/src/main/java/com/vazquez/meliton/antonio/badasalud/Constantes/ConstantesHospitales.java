package com.vazquez.meliton.antonio.badasalud.Constantes;

public class ConstantesHospitales {
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
    public static final String GET = IP + PUERTO_HOST + "/webservice/hospitales/get_hospitales.php";
    public static final String GET_BY_ID = IP + PUERTO_HOST + "/webservice/hospitales/get_hospitales_by_id.php";
    //no es necesario usar más dado que los hospitales están ingresados directamente desde base de datos,
    //no hay opción de modificado, y eliminado, y si hubiera que insertar
    //se haría desde base de datos
    /*public static final String UPDATE = IP + PUERTO_HOST + "/webservice/citas/update_hospitales.php";
    public static final String DELETE = IP + PUERTO_HOST + "/webservice/citas/delete_hospitales.php";
    public static final String INSERT = IP + PUERTO_HOST + "/webservice/citas/insert_hospitales.php";*/


    /**
     * Clave para el valor extra que representa al identificador de un mensaje
     */
    public static final String EXTRA_ID = "IDEXTRA";
}
