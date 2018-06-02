<?php
//Get todos los hospitales

require 'DAOespecialidad.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    $especialidades = DAOespecialidad::getAll();
    if ($especialidades) {
        $datos["estado"] = 1;
        $datos["especialidades"] = $especialidades;
        print json_encode($datos);
    } else {
        print json_encode(array(
            "estado" => 2,
            "resultado" => "Ha ocurrido un error"
        ));
    }
}