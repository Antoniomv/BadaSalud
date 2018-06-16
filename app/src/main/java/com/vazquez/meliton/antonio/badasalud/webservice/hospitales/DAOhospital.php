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

    

}  //Fin de la clase
?>