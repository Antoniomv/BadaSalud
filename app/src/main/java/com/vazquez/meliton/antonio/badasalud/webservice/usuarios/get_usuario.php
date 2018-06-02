<?php
//Get todos los hospitales

require 'DAOusuario.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    $usuarios = DAOusuario::getAll();
    if ($usuarios) {
        $datos["estado"] = 1;
        $datos["usuarios"] = $usuarios;
        print json_encode($datos);
    } else {
        print json_encode(array(
            "estado" => 2,
            "resultado" => "Ha ocurrido un error"
        ));
    }
}