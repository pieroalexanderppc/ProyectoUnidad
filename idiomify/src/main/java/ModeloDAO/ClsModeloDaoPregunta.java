package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Modelo.ClsModeloPregunta;
import Configdb.ClsConexiondb;

public class ClsModeloDaoPregunta {
    private final Connection conexion;
    
    public ClsModeloDaoPregunta() {
        // Obtener la conexión a la base de datos desde ClsConexiondb
        conexion = new ClsConexiondb().obtenerConexion();
    }

    // Método para agregar una nueva pregunta a la base de datos
    public boolean agregarPregunta(ClsModeloPregunta pregunta) {
        String sql = "INSERT INTO tbPreguntas (idPregunta, FKidLeccion, enunciado, actividad, respuesta, urlIlustracion, urlAudio) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, pregunta.getIdPregunta());
            statement.setString(2, pregunta.getFKidLeccion());
            statement.setString(3, pregunta.getEnunciado());
            statement.setString(4, pregunta.getActividad());
            statement.setString(5, pregunta.getRespuesta());
            statement.setString(6, pregunta.getUrlIlustracion());
            statement.setString(7, pregunta.getUrlAudio());
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public ClsModeloPregunta buscarPorIdPregunta(int idPregunta) {
        ClsModeloPregunta pregunta = null;
        String sql = "SELECT * FROM tbPreguntas WHERE idPregunta = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idPregunta);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    pregunta = new ClsModeloPregunta();
                    pregunta.setIdPregunta(resultSet.getInt("idPregunta"));
                    pregunta.setFKidLeccion(resultSet.getString("FKidLeccion"));
                    pregunta.setEnunciado(resultSet.getString("enunciado"));
                    pregunta.setActividad(resultSet.getString("actividad"));
                    pregunta.setRespuesta(resultSet.getString("respuesta"));
                    pregunta.setUrlIlustracion(resultSet.getString("urlIlustracion"));
                    pregunta.setUrlAudio(resultSet.getString("urlAudio"));
                }
            }
        } catch (SQLException e) {
        }
        return pregunta;
    }

    public List<ClsModeloPregunta> listarPreguntasPorLeccion(String idLeccion) {
        List<ClsModeloPregunta> preguntas = new ArrayList<>();
        String sql = "SELECT * FROM tbPreguntas WHERE FKidLeccion = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, idLeccion);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ClsModeloPregunta pregunta = new ClsModeloPregunta();
                    pregunta.setIdPregunta(resultSet.getInt("idPregunta"));
                    pregunta.setFKidLeccion(resultSet.getString("FKidLeccion"));
                    pregunta.setEnunciado(resultSet.getString("enunciado"));
                    pregunta.setActividad(resultSet.getString("actividad"));
                    pregunta.setRespuesta(resultSet.getString("respuesta"));
                    pregunta.setUrlIlustracion(resultSet.getString("urlIlustracion"));
                    pregunta.setUrlAudio(resultSet.getString("urlAudio"));
                    preguntas.add(pregunta);
                }
            }
        } catch (SQLException e) {
        }
        return preguntas;
    }

    public boolean actualizarPregunta(ClsModeloPregunta pregunta) {
        String sql = "UPDATE tbPreguntas SET FKidLeccion = ?, enunciado = ?, actividad = ?, respuesta = ?, urlIlustracion = ?, urlAudio = ? WHERE idPregunta = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, pregunta.getFKidLeccion());
            statement.setString(2, pregunta.getEnunciado());
            statement.setString(3, pregunta.getActividad());
            statement.setString(4, pregunta.getRespuesta());
            statement.setString(5, pregunta.getUrlIlustracion());
            statement.setString(6, pregunta.getUrlAudio());
            statement.setInt(7, pregunta.getIdPregunta());
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean eliminarPregunta(int idPregunta) {
        String sql = "DELETE FROM tbPreguntas WHERE idPregunta = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idPregunta);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}
