<?php
include("conexion.php");


if(isset($_POST["email"]) && isset($_POST["password"]))
{

$email=$_POST["email"];

$password=$_POST["password"];


     // Obtener parámetro
    $result = mysqli_query($conn, "select * from usuarios where email='$email' && password='$password'");


    if(mysqli_num_rows($result) > 0)
    {
        echo "Login";
        exit;
    }
    else
    {
        echo "Usuario o Contraseña no correctos";
        exit;
    }
}

?>