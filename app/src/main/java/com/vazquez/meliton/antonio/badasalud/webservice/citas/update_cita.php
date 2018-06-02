<?php

require 'DAOcita.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $body = json_decode(file_get_contents("php://input"), true);

    $resultado = DAOcita::update(
        $body['titulo'],
        $body['fecha'],
        $body['hora'],
        $body['hospital_id'],
        $body['especialidad_id']);

    if ($resultado) {
        print json_encode(
            array(
                'estado' => '1',
                'mensaje' => 'Operación correcta')
        );
    } else {
        print json_encode(
            array(
                'estado' => '2',
                'mensaje' => 'Error en la operación')
        );
    }
}