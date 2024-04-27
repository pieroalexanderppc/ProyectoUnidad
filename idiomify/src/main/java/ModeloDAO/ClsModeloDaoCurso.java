package ModeloDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Modelo.ClsModeloCurso;
import Configdb.ClsConexiondb;

public class ClsModeloDaoCurso {
    private final Connection conexion;
    
    public ClsModeloDaoCurso() {
        // Obtener la conexión a la base de datos desde ClsConexiondb
        conexion = new ClsConexiondb().obtenerConexion();
    }

    // Método para agregar un nuevo curso a la base de datos
    public boolean agregarCurso(ClsModeloCurso curso) {
        String sql = "INSERT INTO tbCursos (idCurso, FKidIdioma, urlBanner, nombre, descripcion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, curso.getIdCurso());
            statement.setInt(2, curso.getFKidIdioma());
            statement.setString(3, curso.getUrlBanner());
            statement.setString(4, curso.getNombre());
            statement.setString(5, curso.getDescripcion());
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    // Método para actualizar un curso en la base de datos
    public boolean actualizarCurso(ClsModeloCurso curso) {
        String sql = "UPDATE tbCursos SET FKidIdioma = ?, urlBanner = ?, nombre = ?, descripcion = ? WHERE idCurso = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, curso.getFKidIdioma());
            statement.setString(2, curso.getUrlBanner());
            statement.setString(3, curso.getNombre());
            statement.setString(4, curso.getDescripcion());
            statement.setString(5, curso.getIdCurso());
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    // Método para eliminar un curso de la base de datos
    public boolean eliminarCurso(String idCurso) {
        String sql = "DELETE FROM tbCursos WHERE idCurso = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, idCurso);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }
    
    // Método para buscar un curso por su idCurso
    public ClsModeloCurso buscarPorIdCurso(String idCurso) {
        ClsModeloCurso curso = null;
        String sql = "SELECT * FROM tbCursos WHERE idCurso = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, idCurso);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    curso = new ClsModeloCurso(
                        resultSet.getString("idCurso"),
                        resultSet.getInt("FKidIdioma"),
                        resultSet.getString("urlBanner"),
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion")
                    );
                }
            }
        } catch (SQLException e) {

        }
        return curso;
    }

    // Método para listar cursos por idIdioma
    public List<ClsModeloCurso> listarCursosPorIdIdioma(int idIdioma) {
        List<ClsModeloCurso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM tbCursos WHERE FKidIdioma = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idIdioma);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ClsModeloCurso curso = new ClsModeloCurso(
                        resultSet.getString("idCurso"),
                        resultSet.getInt("FKidIdioma"),
                        resultSet.getString("urlBanner"),
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion")
                    );
                    cursos.add(curso);
                }
            }
        } catch (SQLException e) {
        }
        return cursos;
    }
}
