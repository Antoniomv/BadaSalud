<?php
// Fuente http://www.hermosaprogramacion.com/2015/05/crear-un-webservice-para-android-con-mysql-php-y-json/
// Clase que envuelve una instancia de la clase PDO para el manejo de la base de datos

require_once 'config.php';  //Donde se definen los datos conexion

class Database {
    private static $db = null;
    private static $pdo;

    final public function __construct() {
        try {
            self::getDb(); // Crear nueva conexión PDO
        } catch (PDOException $e) {
            // Manejo de excepciones
        }
    }

    public static function getInstance() {  // Devuelve en la única instancia de la clase
        if (self::$db === null) {
            self::$db = new self();
        }
        return self::$db;
    }

    public function getdb()  { //Crea la conexión
        if (self::$pdo == null) {
           $dns = 'mysql:host=' . HOSTNAME .';dbname=' . DATABASE;
           $user = USERNAME;
           $pass = PASSWORD;
           self::$pdo = new PDO($dns,$user,$pass,array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8"));
           self::$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION); // Habilitar excepciones


        }
        else { }

        return self::$pdo;
    }

    final protected function __clone() { //Evita la clonación del objeto
    }

    function _destructor() {
        self::$pdo = null;
    }
}

?>