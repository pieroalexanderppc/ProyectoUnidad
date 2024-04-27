package ModeloDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Modelo.ClsModeloIdioma;
import Configdb.ClsConexiondb;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ClsModeloDaoIdioma {
    private final Connection conexion;
    
    public ClsModeloDaoIdioma() {
        // Obtener la conexión a la base de datos desde ClsConexiondb
        conexion = new ClsConexiondb().obtenerConexion();
    }

    // Método para agregar un nuevo idioma a la base de datos
    public boolean agregarIdioma(ClsModeloIdioma idioma) {
        String sql = "INSERT INTO tbIdiomas (nombre, descripcion, urlBanner) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, idioma.getNombre());
            statement.setString(2, idioma.getDescripcion());
            statement.setString(3, idioma.getUrlBanner());
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    // Método para obtener todos los idiomas desde la base de datos
    public List<ClsModeloIdioma> obtenerTodosIdiomas() {
        List<ClsModeloIdioma> idiomas = new ArrayList<>();
        String sql = "SELECT * FROM tbIdiomas";
        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ClsModeloIdioma idioma = new ClsModeloIdioma(
                    resultSet.getInt("idIdioma"),
                    resultSet.getString("nombre"),
                    resultSet.getString("descripcion"),
                    resultSet.getString("urlBanner")
                );
                idiomas.add(idioma);
            }
        } catch (SQLException e) {
        }
        return idiomas;
    }

    // Método para actualizar un idioma en la base de datos
    public boolean actualizarIdioma(ClsModeloIdioma idioma) {
        String sql = "UPDATE tbIdiomas SET nombre = ?, descripcion = ?, urlBanner = ? WHERE idIdioma = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, idioma.getNombre());
            statement.setString(2, idioma.getDescripcion());
            statement.setString(3, idioma.getUrlBanner());
            statement.setInt(4, idioma.getIdIdioma());
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
          
            return false;
        }
    }

    // Método para eliminar un idioma de la base de datos
    public boolean eliminarIdioma(int idIdioma) {
        String sql = "DELETE FROM tbIdiomas WHERE idIdioma = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idIdioma);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }
        // Método para obtener un idioma por su ID desde la base de datos
    public ClsModeloIdioma obtenerIdiomaPorId(int idIdioma) {
        String sql = "SELECT * FROM tbIdiomas WHERE idIdioma = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idIdioma);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new ClsModeloIdioma(
                        resultSet.getInt("idIdioma"),
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion"),
                        resultSet.getString("urlBanner")
                    );
                }
            }
        } catch (SQLException e) {
        }
        return null; // Si no se encontró ningún idioma con el ID proporcionado
    }
    
    // Método para agregar idiomas desde un archivo CSV
    public boolean agregarIdiomasDesdeCSV(InputStream inputStream) throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(inputStream)))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // Los elementos de nextLine representan una línea en el archivo CSV
                // Puedes acceder a cada elemento por su índice (por ejemplo, nextLine[0], nextLine[1], etc.)

                // Crea un objeto ClsModeloIdioma y asigna los valores desde el archivo CSV
                ClsModeloIdioma nuevoIdioma = new ClsModeloIdioma();
                nuevoIdioma.setNombre(nextLine[0]); // Ajusta los índices según la estructura de tu archivo CSV
                nuevoIdioma.setDescripcion(nextLine[1]);
                nuevoIdioma.setUrlBanner(nextLine[2]);

                // Agrega el nuevo idioma a la base de datos
                if (!agregarIdioma(nuevoIdioma)) {
                    // Manejar el caso en que no se pudo agregar el idioma
                    return false;
                }
            }
            return true;
        }
        // Manejar las excepciones, por ejemplo, registrándolas o lanzándolas nuevamente
            }


}
