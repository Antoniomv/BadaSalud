<?php
/**
 * Created by PhpStorm.
 * User: Antonio
 * Date: 17/02/2018
 * Time: 19:37
 */

require_once ('Database.php');
echo ("Probando conexión...". "<br>");
$db = new Database();
$connection = $db->getdb();
if ($db != null){
    echo("Base de datos Funcionando Correctamente". "<br>");
}
?>
