<?php
include("conexion.php");


if(isset($_POST["email"]) && isset($_POST["password"]))
{
    
$email=$_POST["email"];
    
$password=$_POST["password"];
    $result = mysqli_query($conn, "select * from usuarios where email='$email' && password='$password'");


	if(mysqli_num_rows($result) > 0)
	{	
		echo "Login";
		return $result;
		echo $result;
	}			
	else
	{	
		echo "Usuario o Contraseña no correctos";
		exit;
	}
}

?>