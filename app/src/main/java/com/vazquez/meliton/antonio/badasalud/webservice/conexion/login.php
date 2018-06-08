<?php
include("conexion.php");

if(isset($_POST["email"]) && isset($_POST["password"]))
	{
    
	$email=$_POST["email"];    
	$password=$_POST["password"];


		$result = mysqli_query($conn, "select ID, NOMBRE, APELLIDOS, TELEFONO from usuarios where email='$email' && password='$password'");

	    $row = mysqli_fetch_array($result);

	    $data = $row[0];


		if(mysqli_num_rows($result) > 0)
		{	
			echo $data;
			exit;
		}			
		else
		{	
			echo "0";
			exit;
		}

	   mysqli_close($con);
}

?>