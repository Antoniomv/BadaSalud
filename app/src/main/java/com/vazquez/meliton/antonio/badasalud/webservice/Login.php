<?php

    $hostname="qzn038.badasalud.es";
    $database="qzn038";
    $username="qzn038";
    $password="Adrian22";

    $username = $_POST["email"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM usuarios WHERE email = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $email, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement,$id, $nombre, $apellidos, $telefono, $email, $password);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["id"] = $id;
        $response["nombre"] = $nombre;
        $response["apellidos"] = $apellidos;
        $response["telefono"] = $telefono;
        $response["email"] = $email;
        $response["password"] = $password;
    }
    
    echo json_encode($response);
?>