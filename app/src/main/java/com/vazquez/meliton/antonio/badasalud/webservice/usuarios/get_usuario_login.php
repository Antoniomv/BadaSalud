<?php
/**
 * Created by PhpStorm.
 * User: antonio
 * Date: 23/05/18
 * Time: 23:03
 */

require 'DAOusuario.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    if (isset($_GET['email']) &&  (isset($_GET['password']))) {
        $parametro1 = $_GET['email'];
        $parametro2 = $_GET['password'];

        $resultado = DAOusuario::getByLogin($parametro1,$parametro2);
        if ($resultado) {
            $dato["estado"] = "1";
            $dato["usuarios"] = $resultado;
            print json_encode($dato);
        } else {  //error
            print json_encode(
                array(
                    'estado' => '2',
                    'mensaje' => 'No se obtuvo el registro'
                )
            );
        }

    } else {
        print json_encode( //Otro error
            array(
                'estado' => '3',
                'mensaje' => 'Se necesita un identificador'
            )
        );
    }
}
