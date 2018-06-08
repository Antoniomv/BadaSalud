<?php

$hostname="qzn038.badasalud.es";
$database="qzn038";
$username="qzn038";
$password="Adrian22";
$conn = mysqli_connect($hostname, $username, $password,$database);


    if(isset($_POST["id"]))
    {
    
    $id=$_POST["id"];    


        $result = mysqli_query($conn, "select ID, TITULO, FECHA, HORA, HOSPITAL_ID, ESPECIALIDAD_ID, USUARIO_ID from citas where USUARIO_ID='$id'");

        if ($_SERVER['REQUEST_METHOD'] == 'GET') {


        if ($result) {
            $dato["estado"] = "1";
            $dato["citas"] = $result;
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