<?php

    require 'conexion.php';
if(isset($_POST["titulo"]) && isset($_POST["usuario_id"]) && isset($_POST["hospital_id"]) && isset($_POST["especialidad_id"]) && isset($_POST["fecha"]) && isset($_POST["hora"])){
    $title = $_POST["titulo"];
    $u_id = $_POST["usuario_id"];
    $h_id = $_POST["hospital_id"];
    $e_id = $_POST["especialidad_id"];
    $fecha = $_POST["fecha"];
    $hora = $_POST["hora"];

    $sql = "INSERT INTO `citas`(`titulo`, `usuario_id`, `hospital_id`, `especialidad_id`, `fecha`, `hora`) VALUES ('$title',$u_id,$h_id,$e_id,'$fecha','$hora')";

    if ($conn->query($sql) === TRUE) {
        $response["success"] = TRUE;
    } else {
        $response["success"] = false;
        $response["error"] = "ERROR_FATAL";
    }
 }

     $conn->close();

    echo json_encode($response, JSON_UNESCAPED_UNICODE);

?>