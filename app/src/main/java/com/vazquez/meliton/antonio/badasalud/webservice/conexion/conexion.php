<?php
$hostname="qzn038.badasalud.es";
$database="qzn038";
$username="qzn038";
$password="4AynoI1hO18I/4TEV28VW3e73Qr3B6h/ltv1z2R6TIqeujH/dKeUy5ll14m3WrJA";
$conn = mysqli_connect($hostname, $username, $password,$database);

if(!$conn){
	die("Error Connection");
}

?>