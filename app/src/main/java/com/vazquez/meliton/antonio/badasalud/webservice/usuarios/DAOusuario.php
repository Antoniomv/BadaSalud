<?php
//Representa  hospitales almacenados en la base de datos

require '../Database.php';

class DAOusuario {

    function __construct() {
    }

    public static function getAll() {

        $consulta = "SELECT * FROM usuarios";
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
        $consulta = "SELECT id,nombre,apellidos,telefono,email,password FROM usuarios WHERE id = ?";
        try {
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            $comando->execute(array($id));
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            return -1; //Clasificación del error para mostrarlo en json
        }
    }

    public static function getByLogin($email,$password ) {
        $consulta = "SELECT id,nombre,apellidos,telefono,email,password FROM usuarios WHERE email = ? AND password= ?";
        try {
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            $comando->execute(array($email, $password));
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            return -1; //Clasificación del error para mostrarlo en json
        }
    }

    public static function update($id, $nombre, $apellidos, $telefono, $email, $password ) {
        $consulta = "UPDATE hospital SET nombre=?, apellidos=?, telefono=?, email=?, password=? WHERE id=?";
        $cmd = Database::getInstance()->getDb()->prepare($consulta);
        $cmd->execute(array($id, $nombre, $apellidos, $telefono, $email, $password));
        return $cmd;
    }

    public static function insert($nombre, $apellidos, $telefono, $email, $password ) {
        $comando = "INSERT INTO usuarios (nombre,apellidos,telefono,email,password) VALUES(?,?,?,?,?)";

        $sentencia = Database::getInstance()->getDb()->prepare($comando);
        return $sentencia->execute(array($nombre, $apellidos, $telefono, $email, $password));
    }

    public static function delete($id) {
        $comando = "DELETE FROM usuarios WHERE id=?";
        $sentencia = Database::getInstance()->getDb()->prepare($comando);
        return $sentencia->execute(array($id));
    }

}  //Fin de la clase
?>