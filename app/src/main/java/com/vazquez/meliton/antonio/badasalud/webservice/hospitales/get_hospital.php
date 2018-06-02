<?php
//Get todos los hospitales

require 'DAOhospital.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    $hospitales = DAOhospital::getAll();
    if ($hospitales) {
        $datos["estado"] = 1;
        $datos["hospitales"] = $hospitales;
        print json_encode($datos);
    } else {
        print json_encode(array(
            "estado" => 2,
            "resultado" => "Ha ocurrido un error"
        ));
    }
}