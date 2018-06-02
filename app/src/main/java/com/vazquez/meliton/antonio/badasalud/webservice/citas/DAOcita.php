<?php
//Representa  hospitales almacenados en la base de datos

require '../Database.php';

class DAOcita {

    function __construct() {
    }

    public static function getAll() {

        $consulta = "SELECT * FROM citas";
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
        $consulta = "SELECT id,titulo,fecha,hora,usuario_id,hospital_id,especialidad_id FROM citas WHERE id = ?";
        try {
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            $comando->execute(array($id));
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            return -1; //Clasificación del error para mostrarlo en json
        }
    }

    public static function update($id, $titulo, $fecha, $hora, $hospital_id, $especialidad_id ) {
        $consulta = "UPDATE hospital" .
            " SET titulo=?, fecha=?, hora=?, hospital_id=?, especialidad_id=? " .
            " WHERE id=?";
        $cmd = Database::getInstance()->getDb()->prepare($consulta);
        $cmd->execute(array($id, $titulo, $fecha, $hora, $hospital_id, $especialidad_id ));
        return $cmd;
    }

    public static function insert($titulo, $fecha, $hora, $usuario_id, $hospital_id, $especialidad_id  ) {
        $comando = "INSERT INTO citas (titulo, fecha, hora, usuario_id, hospital_id, especiliadad_id) VALUES(?,?,?,?,?,?)";

        $sentencia = Database::getInstance()->getDb()->prepare($comando);
        return $sentencia->execute(array($titulo, $fecha, $hora, $usuario_id, $hospital_id, $especialidad_id));
    }

    public static function delete($id) {
        $comando = "DELETE FROM citas WHERE id=?";
        $sentencia = Database::getInstance()->getDb()->prepare($comando);
        return $sentencia->execute(array($id));
    }

}  //Fin de la clase
?>