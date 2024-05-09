package Configdb;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class ClsConexiondb {
    Connection conexiondb = null;  // Declaración de una variable de tipo "Connection" llamada "conexiondb" inicializada como nula.

   
    private String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al obtener instancia de SHA-256", e);
        }
    }

    public ClsConexiondb() {
        try {
            String passwordEnv = System.getenv("DB_PASSWORD"); // Obtener la contraseña desde una variable de entorno
            if (passwordEnv == null) {
                throw new IllegalArgumentException("La variable de entorno DB_PASSWORD no está definida");
            }
            String encryptedPassword = encryptPassword(passwordEnv);
            conexiondb = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbidiomify", "tu_usuario", encryptedPassword);
        } catch (SQLException e) {
            e.printStackTrace();
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
