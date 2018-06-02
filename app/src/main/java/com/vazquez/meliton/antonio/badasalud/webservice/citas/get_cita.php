<?php
//Get todos los hospitales

require 'DAOcita.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    $citas = DAOcita::getAll();
    if ($citas) {
        $datos["estado"] = 1;
        $datos["citas"] = $citas;
        print json_encode($datos);
    } else {
        print json_encode(array(
            "estado" => 2,
            "resultado" => "Ha ocurrido un error"
        ));
    }
}