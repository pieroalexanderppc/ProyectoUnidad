package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Modelo.ClsModeloAdministrador;
import Configdb.ClsConexiondb;
import Controlador.LoginServlet;

public class ClsModeloDaoAdministrador {
    private final Connection conexion;
    LoginServlet loginServlet = new LoginServlet();
    
    public ClsModeloDaoAdministrador() {
        // Obtener la conexión a la base de datos desde ClsConexiondb
        conexion = new ClsConexiondb().obtenerConexion();
    }

    // Método para agregar un nuevo administrador a la base de datos
    public boolean registrarAdministrador(ClsModeloAdministrador administrador) {
         System.out.println("asswors normal:  "+administrador.getPasswordHash());
        String hashedpassword= loginServlet.hashPassword(administrador.getPasswordHash());
        System.out.println("password hash: "+hashedpassword);
  String sql = "INSERT INTO tbAdministradores (dni, nombre, apellido, fechaNacimiento, genero, pais, ciudad, email, telefono, passwordHash, rol, estado, fotoPerfil) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, administrador.getDni());
            statement.setString(2, administrador.getNombre());
            statement.setString(3, administrador.getApellido());
            statement.setString(4, administrador.getFechaNacimiento());
            statement.setString(5, administrador.getGenero());
            statement.setString(6, administrador.getPais());
            statement.setString(7, administrador.getCiudad());
            statement.setString(8, administrador.getEmail());
            statement.setInt(9, administrador.getTelefono());
            statement.setString(10, hashedpassword);
            statement.setString(11, administrador.getRol());
            statement.setInt(12, administrador.getEstado());
            statement.setString(13, administrador.getFotoPerfil());

            int filasAfectadas = statement.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores de SQL
            return false;
        }
}

    // Método para obtener todos los administradores desde la base de datos
    public List<ClsModeloAdministrador> obtenerTodosAdministradores() {
        List<ClsModeloAdministrador> administradores = new ArrayList<>();
        String sql = "SELECT * FROM tbAdministradores";
        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ClsModeloAdministrador administrador = new ClsModeloAdministrador();
                administrador.setDni(resultSet.getString("dni"));
                administrador.setNombre(resultSet.getString("nombre"));
                administrador.setApellido(resultSet.getString("apellido"));
                administrador.setFechaNacimiento(resultSet.getString("fechaNacimiento"));
                administrador.setGenero(resultSet.getString("genero"));
                administrador.setPais(resultSet.getString("pais"));
                administrador.setCiudad(resultSet.getString("ciudad"));
                administrador.setEmail(resultSet.getString("email"));
                administrador.setTelefono(resultSet.getInt("telefono"));
                administrador.setPasswordHash(resultSet.getString("passwordHash"));
                administrador.setRol(resultSet.getString("rol"));
                administrador.setFechaRegistro(resultSet.getTimestamp("fechaRegistro"));
                administrador.setFechaActualizacion(resultSet.getTimestamp("fechaActualizacion"));
                administrador.setEstado(resultSet.getInt("estado"));
                administrador.setFotoPerfil(resultSet.getString("fotoPerfil"));
                administradores.add(administrador);
            }
        } catch (SQLException e) {
        }
        return administradores;
    }

    // Método para actualizar un administrador en la base de datos
   public boolean actualizarAdministrador(ClsModeloAdministrador administrador) {
    String sql = "UPDATE tbAdministradores SET nombre = ?, apellido = ?, fechaNacimiento = ?, genero = ?, pais = ?, ciudad = ?, email = ?, telefono = ?, passwordHash = ?, rol = ?, estado = ?, fotoPerfil = ? WHERE dni = ?";
    
    try (PreparedStatement statement = conexion.prepareStatement(sql)) {
        statement.setString(1, administrador.getNombre());
        statement.setString(2, administrador.getApellido());
        statement.setString(3, administrador.getFechaNacimiento());
        statement.setString(4, administrador.getGenero());
        statement.setString(5, administrador.getPais());
        statement.setString(6, administrador.getCiudad());
        statement.setString(7, administrador.getEmail());
        statement.setInt(8, administrador.getTelefono());
        statement.setString(9, administrador.getPasswordHash());
        statement.setString(10, administrador.getRol());
        statement.setInt(11, administrador.getEstado());
        statement.setString(12, administrador.getFotoPerfil());
        statement.setString(13, administrador.getDni());

        int filasAfectadas = statement.executeUpdate();

        return filasAfectadas > 0;
    } catch (SQLException e) {
        e.printStackTrace(); // Manejo de errores de SQL
        return false;
    }
}

    // Método para desactivar un administrador por su DNI
    public boolean desactivarAdministrador(String dni) {
        String sql = "UPDATE tbAdministradores SET estado = 0 WHERE dni = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, dni);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    // Método para activar un administrador por su DNI
    public boolean activarAdministrador(String dni) {
        String sql = "UPDATE tbAdministradores SET estado = 1 WHERE dni = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, dni);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    // Método para obtener un administrador por su DNI desde la base de datos
    public ClsModeloAdministrador obtenerAdministradorPorDNI(String dni) {
        
        String sql = "SELECT * FROM tbAdministradores WHERE dni = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, dni);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ClsModeloAdministrador administrador = new ClsModeloAdministrador();
                    administrador.setDni(resultSet.getString("dni"));
                    administrador.setNombre(resultSet.getString("nombre"));
                    administrador.setApellido(resultSet.getString("apellido"));
                    administrador.setFechaNacimiento(resultSet.getString("fechaNacimiento"));
                    administrador.setGenero(resultSet.getString("genero"));
                    administrador.setPais(resultSet.getString("pais"));
                    administrador.setCiudad(resultSet.getString("ciudad"));
                    administrador.setEmail(resultSet.getString("email"));
                    administrador.setTelefono(resultSet.getInt("telefono"));
                    administrador.setPasswordHash(resultSet.getString("passwordHash"));
                    administrador.setRol(resultSet.getString("rol"));
                    administrador.setFechaRegistro(resultSet.getTimestamp("fechaRegistro"));
                    administrador.setFechaActualizacion(resultSet.getTimestamp("fechaActualizacion"));
                    administrador.setEstado(resultSet.getInt("estado"));
                    administrador.setFotoPerfil(resultSet.getString("fotoPerfil"));
                    return administrador;
                }
            }
        } catch (SQLException e) {
        }
        return null; // Si no se encontró ningún administrador con el DNI proporcionado
    }

      // Método para realizar la autenticación de administradores por correo electrónico y contraseña
    public ClsModeloAdministrador autenticarAdministrador(String email, String password) {
        String hashedpassword= loginServlet.hashPassword(password);
        String sql = "SELECT * FROM tbAdministradores WHERE email = ? AND passwordHash = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, hashedpassword);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ClsModeloAdministrador administrador = new ClsModeloAdministrador();
                    administrador.setDni(resultSet.getString("dni"));
                    administrador.setNombre(resultSet.getString("nombre"));
                    administrador.setApellido(resultSet.getString("apellido"));
                    administrador.setFechaNacimiento(resultSet.getString("fechaNacimiento"));
                    administrador.setGenero(resultSet.getString("genero"));
                    administrador.setPais(resultSet.getString("pais"));
                    administrador.setCiudad(resultSet.getString("ciudad"));
                    administrador.setEmail(resultSet.getString("email"));
                    administrador.setTelefono(resultSet.getInt("telefono"));
                    administrador.setPasswordHash(resultSet.getString("passwordHash"));
                    administrador.setRol(resultSet.getString("rol"));
                    administrador.setFechaRegistro(resultSet.getTimestamp("fechaRegistro"));
                    administrador.setFechaActualizacion(resultSet.getTimestamp("fechaActualizacion"));
                    administrador.setEstado(resultSet.getInt("estado"));
                    administrador.setFotoPerfil(resultSet.getString("fotoPerfil"));
                    int estado=resultSet.getInt("estado");
                    if (estado!=0){
                        return administrador;
                    } 
                }
            }
        } catch (SQLException e) {
            System.out.println("error"+e);
            // Manejar la excepción, por ejemplo, imprimir el error

        }
        return null; // Si no se encontró ningún administrador con las credenciales proporcionadas
    }


}
