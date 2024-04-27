package ModeloDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Modelo.ClsModeloLeccion;
import Configdb.ClsConexiondb;

public class ClsModeloDaoLeccion {
    private final Connection conexion;
    
    public ClsModeloDaoLeccion() {
        // Obtener la conexión a la base de datos desde ClsConexiondb
        conexion = new ClsConexiondb().obtenerConexion();
    }

    // Método para agregar una nueva lección a la base de datos
    public boolean agregarLeccion(ClsModeloLeccion leccion) {
        String sql = "INSERT INTO tbLecciones (idLeccion, FKidCurso, FKidIdioma, titulo, urlBanner) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, leccion.getIdLeccion());
            statement.setString(2, leccion.getFKidCurso());
            statement.setInt(3, leccion.getFKidIdioma());
            statement.setString(4, leccion.getTitulo());
            statement.setString(5, leccion.getUrlBanner());
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }
    public ClsModeloLeccion buscarPorIdLeccion(String idLeccion) {
    ClsModeloLeccion leccion = null;
    String sql = "SELECT * FROM tbLecciones WHERE idLeccion = ?";
    try (PreparedStatement statement = conexion.prepareStatement(sql)) {
        statement.setString(1, idLeccion);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                leccion = new ClsModeloLeccion(
                    resultSet.getString("idLeccion"),
                    resultSet.getString("FKidCurso"),
                    resultSet.getInt("FKidIdioma"),
                    resultSet.getString("titulo"),
                    resultSet.getString("urlBanner")
                );
            }
        }
    } catch (SQLException e) {
    }
    return leccion;
}

public List<ClsModeloLeccion> listarLeccionesPorCurso(String idCurso) {
    List<ClsModeloLeccion> lecciones = new ArrayList<>();
    String sql = "SELECT * FROM tbLecciones WHERE FKidCurso = ?";
    try (PreparedStatement statement = conexion.prepareStatement(sql)) {
        statement.setString(1, idCurso);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ClsModeloLeccion leccion = new ClsModeloLeccion(
                    resultSet.getString("idLeccion"),
                    resultSet.getString("FKidCurso"),
                    resultSet.getInt("FKidIdioma"),
                    resultSet.getString("titulo"),
                    resultSet.getString("urlBanner")
                );
                lecciones.add(leccion);
            }
        }
    } catch (SQLException e) {
    }
    return lecciones;
}



    // Método para actualizar una lección en la base de datos
    public boolean actualizarLeccion(ClsModeloLeccion leccion) {
        String sql = "UPDATE tbLecciones SET FKidCurso = ?, FKidIdioma = ?, titulo = ?, urlBanner = ? WHERE idLeccion = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, leccion.getFKidCurso());
            statement.setInt(2, leccion.getFKidIdioma());
            statement.setString(3, leccion.getTitulo());
            statement.setString(4, leccion.getUrlBanner());
            statement.setString(5, leccion.getIdLeccion());
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    // Método para eliminar una lección de la base de datos
    public boolean eliminarLeccion(String idLeccion) {
        String sql = "DELETE FROM tbLecciones WHERE idLeccion = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, idLeccion);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}
