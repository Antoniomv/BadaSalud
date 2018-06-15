<?php

require 'conexion.php';

if(isset($_GET["id"])){
$id = $_GET["id"];
$sql = "DELETE * FROM citas Where id = $id";
$conn->query($sql);

$conn->close();

?>