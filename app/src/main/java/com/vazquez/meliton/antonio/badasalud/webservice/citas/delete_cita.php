<?php

require 'conexion.php';

if(isset($_POST["id"])){
$id = $_POST["id"];
$sql = "DELETE * FROM citas Where id = $id";
$conn->query($sql);

$response["success"] = true;

$conn->close();

echo json_encode($response, JSON_UNESCAPED_UNICODE);

?>