<?php
//Representa  hospitales almacenados en la base de datos

require '../Database.php';

class DAOespecialidad {

    function __construct() {
    }

    public static function getAll() {

        $consulta = "SELECT * FROM especialidades";
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
        $consulta = "SELECT id,especialidad FROM especialidades WHERE id = ?";
        try {
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            $comando->execute(array($id));
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            return -1; //Clasificación del error para mostrarlo en json
        }
    }

    public static function update($id, $especialidad ) {
        $consulta = "UPDATE especialidades" .
            " SET especialidad=?" .
            " WHERE id=?";
        $cmd = Database::getInstance()->getDb()->prepare($consulta);
        $cmd->execute(array($id, $especialidad));
        return $cmd;
    }

    public static function insert($especialidad ) {
        $comando = "INSERT INTO especialidades (especialidad) VALUES(?)";

        $sentencia = Database::getInstance()->getDb()->prepare($comando);
        return $sentencia->execute(array($especialidad));
    }

    public static function delete($id) {
        $comando = "DELETE FROM especialidades WHERE id=?";
        $sentencia = Database::getInstance()->getDb()->prepare($comando);
        return $sentencia->execute(array($id));
    }

}  //Fin de la clase
?>