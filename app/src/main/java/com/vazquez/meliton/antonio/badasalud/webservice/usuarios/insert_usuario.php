<?php

require 'DAOusuario.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $body = json_decode(file_get_contents("php://input"), true);

    if($pruebaemail== mysqli_query($conn, "select email from usuarios where email='$email'")){
        print json_encode(
            array(
                'estado' => '2',
                'mensaje' => 'Email en uso, utilice otro para registrarse')
            );
        echo "error";
        exit;
    }else{
        $resultado = DAOusuario::insert(

            $body['nombre'],
            $body['apellidos'],
            $body['telefono'],
            $body['email'],
            $body['password']);

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
}