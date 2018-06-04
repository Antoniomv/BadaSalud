<?php
$hostname="qzn038.badasalud.es";
$database="qzn038";
$username="qzn038";
$password="Adrian22";
$conn = mysqli_connect($hostname, $username, $password,$database);

if(!$conn){
	die("Error Connection");
}

?>