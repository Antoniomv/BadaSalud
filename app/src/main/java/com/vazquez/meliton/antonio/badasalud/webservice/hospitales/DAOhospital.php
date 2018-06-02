<?php
//Representa  hospitales almacenados en la base de datos

require '../Database.php';

class DAOhospital {

    function __construct() {
    }

    public static function getAll() {

        $consulta = "SELECT * FROM hospitales";
        try {
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            $comando->execute();
            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            echo "Error";
            return false;
        }
    }

    public static function getById($id) {
        $consulta = "SELECT id,nombre,direccion,telefono FROM hospitales WHERE id = ?";
        try {
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            $comando->execute(array($id));
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            return -1; //Clasificación del error para mostrarlo en json
        }
    }

    public static function update($id, $nombre, $direccion, $telefono ) {
        $consulta = "UPDATE hospital" .
            " SET nombre=?, direccion=?, telefono=? " .
            " WHERE id=?";
        $cmd = Database::getInstance()->getDb()->prepare($consulta);
        $cmd->execute(array($id, $nombre, $direccion, $telefono));
        return $cmd;
    }

    public static function insert($nombre, $direccion, $telefono ) {
        $comando = "INSERT INTO hospitales (nombre,direccion,telefono) VALUES(?,?,?)";

        $sentencia = Database::getInstance()->getDb()->prepare($comando);
        return $sentencia->execute(array($nombre, $direccion, $telefono));
    }

    public static function delete($id) {
        $comando = "DELETE FROM hospitales WHERE id=?";
        $sentencia = Database::getInstance()->getDb()->prepare($comando);
        return $sentencia->execute(array($id));
    }

}  //Fin de la clase
?>