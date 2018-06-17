<?php

    require 'conexion.php';
    
    if(isset($_GET["titulo"]) && isset($_GET["usuario_id"]) && isset($_GET["hospital_id"]) && isset($_GET["especialidad_id"]) && isset($_GET["fecha"]) && isset($_GET["hora"])){

        $title = $_GET["titulo"];
        $u_id = $_GET["usuario_id"];
        $h_id = $_GET["hospital_id"];
        $e_id = $_GET["especialidad_id"];
        $fecha = $_GET["fecha"];
        $hora = $_GET["hora"];

        $sql = "INSERT INTO citas(titulo, usuario_id, hospital_id, especialidad_id, fecha, hora) VALUES ('$title',$u_id,$h_id,$e_id,'$fecha','$hora')";

        $result = $conn->query($sql);

        if ($result === TRUE) {
            $response["success"] = TRUE;
        } else {
            $response["success"] = false;
        }
    }

    $conn->close();

    echo json_encode($response, JSON_UNESCAPED_UNICODE);

?>