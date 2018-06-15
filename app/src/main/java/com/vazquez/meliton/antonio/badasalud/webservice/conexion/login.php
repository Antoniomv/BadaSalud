<?php
    require 'conexion.php';
    
    if(isset($_POST["email"]) && isset($_POST["password"])){
        
        $email = $_POST["email"];
        $password = md5($_POST["password"]);
        
        $sql = "SELECT id FROM usuarios WHERE email = '$email' AND password = '$password'";
        $result = $conn->query($sql);
        
        if($result->num_rows > 0){
            $response["success"] = TRUE;
            while($row = $result->fetch_assoc()) $response["data"] = $row;
        }else{
            $response["success"] = FALSE;
        }
        
    }
    
    $conn->close();
    
    echo json_encode($response, JSON_UNESCAPED_UNICODE);
?>