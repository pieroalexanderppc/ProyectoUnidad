package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Modelo.ClsModeloUsuario;
import Configdb.ClsConexiondb;
//PARA ENCRIPTAR
import Controlador.LoginServlet;

public class ClsModeloDaoUsuario {
    private final Connection conexion;
    LoginServlet loginServlet = new LoginServlet();
    public ClsModeloDaoUsuario() {
        // Obtener la conexión a la base de datos desde ClsConexiondb
        conexion = new ClsConexiondb().obtenerConexion();
    }

    // Método para agregar un nuevo usuario a la base de datos
    public boolean registrarUsuario(ClsModeloUsuario usuario) {
        System.out.println("Estamos en registrarUsuario modleoDAO");
        String hashedpassword= loginServlet.hashPassword(usuario.getPasswordHash());
        String sql = "INSERT INTO tbUsuarios (nombre, apellido, fechaNacimiento, genero, pais, ciudad, email, passwordHash, estado, fotoPerfil) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getFechaNacimiento());
            statement.setString(4, usuario.getGenero());
            statement.setString(5, usuario.getPais());
            statement.setString(6, usuario.getCiudad());
            statement.setString(7, usuario.getEmail());
            statement.setString(8, hashedpassword);
            statement.setInt(9, usuario.getEstado());
            statement.setString(10, usuario.getFotoPerfil());
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }


    // Método para obtener todos los usuarios desde la base de datos
    public List<ClsModeloUsuario> obtenerTodosUsuarios() {
        List<ClsModeloUsuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM tbUsuarios";
        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ClsModeloUsuario usuario = new ClsModeloUsuario();
                usuario.setIdUsuario(resultSet.getInt("idUsuario"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setApellido(resultSet.getString("apellido"));
                usuario.setFechaNacimiento(resultSet.getString("fechaNacimiento"));
                usuario.setGenero(resultSet.getString("genero"));
                usuario.setPais(resultSet.getString("pais"));
                usuario.setCiudad(resultSet.getString("ciudad"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setPasswordHash(resultSet.getString("passwordHash"));
                usuario.setFechaRegistro(resultSet.getTimestamp("fechaRegistro"));
                usuario.setFechaActualizacion(resultSet.getTimestamp("fechaActualizacion"));
                usuario.setEstado(resultSet.getInt("estado"));
                usuario.setFotoPerfil(resultSet.getString("fotoPerfil"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
        }
        return usuarios;
    }

    // Método para actualizar un usuario en la base de datos
    public boolean actualizarUsuario(ClsModeloUsuario usuario) {
        String sql = "UPDATE tbUsuarios SET nombre = ?, apellido = ?, fechaNacimiento = ?, genero = ?, pais = ?, ciudad = ?, email = ?, passwordHash = ?, estado = ?, fotoPerfil = ? WHERE idUsuario = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getFechaNacimiento());
            statement.setString(4, usuario.getGenero());
            statement.setString(5, usuario.getPais());
            statement.setString(6, usuario.getCiudad());
            statement.setString(7, usuario.getEmail());
            statement.setString(8, usuario.getPasswordHash());
            statement.setInt(9, usuario.getEstado());
            statement.setString(10, usuario.getFotoPerfil());
            statement.setInt(11, usuario.getIdUsuario());
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un usuario de la base de datos
    public boolean eliminarUsuario(int idUsuario) {
        String sql = "DELETE FROM tbUsuarios WHERE idUsuario = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idUsuario);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }
    // Método para desactivar un usuario por su ID
    public boolean desactivarUsuario(int idUsuario) {
        String sql = "UPDATE tbUsuarios SET estado = 0 WHERE idUsuario = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idUsuario);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    // Método para activar un usuario por su ID
    public boolean activarUsuario(int idUsuario) {
        String sql = "UPDATE tbUsuarios SET estado = 1 WHERE idUsuario = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idUsuario);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }


    // Método para obtener un usuario por su ID desde la base de datos
    public ClsModeloUsuario obtenerUsuarioPorId(int idUsuario) {
        String sql = "SELECT * FROM tbUsuarios WHERE idUsuario = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ClsModeloUsuario usuario = new ClsModeloUsuario();
                    usuario.setIdUsuario(resultSet.getInt("idUsuario"));
                    usuario.setNombre(resultSet.getString("nombre"));
                    usuario.setApellido(resultSet.getString("apellido"));
                    usuario.setFechaNacimiento(resultSet.getString("fechaNacimiento"));
                    usuario.setGenero(resultSet.getString("genero"));
                    usuario.setPais(resultSet.getString("pais"));
                    usuario.setCiudad(resultSet.getString("ciudad"));
                    usuario.setEmail(resultSet.getString("email"));
                    usuario.setPasswordHash(resultSet.getString("passwordHash"));
                    usuario.setFechaRegistro(resultSet.getTimestamp("fechaRegistro"));
                    usuario.setFechaActualizacion(resultSet.getTimestamp("fechaActualizacion"));
                    usuario.setEstado(resultSet.getInt("estado"));
                    usuario.setFotoPerfil(resultSet.getString("fotoPerfil"));
                    return usuario;
                }
            }
        } catch (SQLException e) {
        }
        return null; // Si no se encontró ningún usuario con el ID proporcionado
    }
    
            // Método para realizar la autenticación de usuarios por correo electrónico y contraseña
    public ClsModeloUsuario autenticarUsuario(String email, String password, String captchaText) {
         System.out.println("elpassword:"+password);
        String hashedpassword= loginServlet.hashPassword(password);
         System.out.println("elpasswordhash:"+hashedpassword);
        String sql = "SELECT * FROM tbUsuarios WHERE email = ? AND passwordHash = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, hashedpassword);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ClsModeloUsuario usuario = new ClsModeloUsuario();
                    usuario.setIdUsuario(resultSet.getInt("idUsuario"));
                    usuario.setNombre(resultSet.getString("nombre"));
                    usuario.setApellido(resultSet.getString("apellido"));
                    usuario.setFechaNacimiento(resultSet.getString("fechaNacimiento"));
                    usuario.setGenero(resultSet.getString("genero"));
                    usuario.setPais(resultSet.getString("pais"));
                    usuario.setCiudad(resultSet.getString("ciudad"));
                    usuario.setEmail(resultSet.getString("email"));
                    usuario.setPasswordHash(resultSet.getString("passwordHash"));
                    usuario.setFechaRegistro(resultSet.getTimestamp("fechaRegistro"));
                    usuario.setFechaActualizacion(resultSet.getTimestamp("fechaActualizacion"));
                    usuario.setEstado(resultSet.getInt("estado"));
                    usuario.setFotoPerfil(resultSet.getString("fotoPerfil"));
                    if (!captchaText.equals("1")){
                        return usuario;
                    }else{
                        return null;
                    }
                    
                }
            }
        } catch (SQLException e) {
        }
        return null; // Si no se encontró ningún usuario con las credenciales proporcionadas
    }
            public boolean actualizarUsuarioPerfil(ClsModeloUsuario usuario) {
        String sql = "UPDATE tbUsuarios SET nombre = ?, apellido = ?, genero = ? WHERE idUsuario = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getGenero());
            statement.setInt(4, usuario.getIdUsuario());
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
     

}
