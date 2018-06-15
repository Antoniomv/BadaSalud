<?php

    require 'conexion.php';
    
    if(isset($_GET["id"])){
        $id = $_GET["id"];
        $sql = "SELECT c.id, c.titulo, c.fecha, c.hora, h.nombre, e.especialidad FROM citas c, usuarios u, hospitales h, especialidades e WHERE c.usuario_id = u.id AND c.hospital_id = h.id AND c.especialidad_id = e.id AND c.usuario_id = $id";
        $result = $conn->query($sql);
        if($result->num_rows > 0){
            while($row = $result->fetch_assoc()){
                $response[] = $row;
            }
        }else{
            $response["success"] = false;
        }
    }
    
    $conn->close();
    
    echo json_encode($response, JSON_UNESCAPED_UNICODE);
?>