<?php

require 'conexion.php';

if(isset($_GET["id"])){
$id = $_GET["id"];
$sql = "DELETE FROM citas Where id = $id";
$conn->query($sql);

$response["success"] = true;
}
$conn->close();

echo json_encode($response, JSON_UNESCAPED_UNICODE);

?>