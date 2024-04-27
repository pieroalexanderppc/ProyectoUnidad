package Configdb;
import java.sql.*;

public class ClsConexiondb {
    Connection conexiondb = null;  // Declaración de una variable de tipo "Connection" llamada "conexiondb" inicializada como nula.

    // Constructor de la clase "ClsConexiondb" que se encarga de establecer una conexión a la base de datos.
    public ClsConexiondb() {
        try {
            // Cargar el controlador (driver) de MySQL para Java.
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establecer una conexión a la base de datos "nombre_de_la_base_de_datos" en el servidor MySQL local en el puerto 3306,
            // utilizando el usuario "tu_usuario" y la contraseña "tu_contraseña".
            conexiondb = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbidiomify", "root", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Registra el error en la consola.
        } catch (SQLException e) {
            e.printStackTrace(); // Registra el error en la consola.
        }
    }

    // Método que proporciona la conexión a la base de datos.
    public Connection obtenerConexion() {
        return this.conexiondb;
    }

    // Método que se encarga de cerrar la conexión a la base de datos.
    public void cerrarConexion() {
        try {
            if (conexiondb != null) {
                // Cerrar la conexión a la base de datos si no es nula.
                conexiondb.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Registra el error en la consola.
        }
    }
}
