/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Configdb.ClsConexiondb;
import Modelo.ClsModeloConsultaProgreso;
import Modelo.ClsModeloProgreso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ClsModeloDaoProgreso {
       private final Connection conexion;
        public ClsModeloDaoProgreso() {
        // Obtener la conexión a la base de datos desde ClsConexiondb
        conexion = new ClsConexiondb().obtenerConexion();
    }
        
//        CREATE TABLE tbProgreso (
//    idProgreso INT AUTO_INCREMENT PRIMARY KEY,
//    FKidUsuario INT NOT NULL,
//    FKidIdioma INT NOT NULL,
//    FKidCurso VARCHAR(6) NOT NULL,
//    FKidLeccion VARCHAR(9) NOT NULL,
//    fechaProgreso TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//    puntuacion INT DEFAULT 0,
//    FOREIGN KEY (FKidUsuario) REFERENCES tbUsuarios(idUsuario),
//    FOREIGN KEY (FKidIdioma) REFERENCES tbIdiomas(idIdioma),
//    FOREIGN KEY (FKidCurso) REFERENCES tbCursos(idCurso),
//    FOREIGN KEY (FKidLeccion) REFERENCES tbLecciones(idLeccion)
//);
            // Método para agregar una nueva lección a la base de datos
        public boolean agregarProgreso(ClsModeloProgreso progreso) {
            String sql = "INSERT INTO tbProgreso (FKidUsuario, FKidIdioma, FKidCurso, FKidLeccion, puntuacion) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                statement.setInt(1, progreso.getIdFKidUsuario());
                statement.setInt(2, progreso.getIdFKidIdioma());
                statement.setString(3, progreso.getFKidCurso());
                statement.setString(4, progreso.getFKidLeccion());
                statement.setInt(5, progreso.getPuntuacion());

                int filasAfectadas = statement.executeUpdate();
                return filasAfectadas > 0;
            } catch (SQLException e) {
                e.printStackTrace(); // Esto es útil para ver detalles del error en la consola
                return false;
            }
        }
public boolean existeProgresoPorIdLeccion(String idLeccion, int idUsuario) {
    String sql = "SELECT COUNT(*) AS total FROM tbProgreso WHERE FKidLeccion = ? AND FKidUsuario = ?";
    try (PreparedStatement statement = conexion.prepareStatement(sql)) {
        statement.setString(1, idLeccion);
        statement.setInt(2, idUsuario);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                int total = resultSet.getInt("total");
                return total > 0; // Devuelve true si hay al menos un registro con ese idLeccion para el usuario especificado
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Esto es útil para ver detalles del error en la consola
    }
    return false; // Si hay un error o no se encuentra ningún registro, devuelve false
}


public boolean actualizarProgreso(String idLeccion, int nuevaPuntuacion) {
    String sql = "UPDATE tbProgreso SET fechaProgreso = CURRENT_TIMESTAMP, puntuacion = ? WHERE FKidLeccion = ?";
    try (PreparedStatement statement = conexion.prepareStatement(sql)) {
        statement.setInt(1, nuevaPuntuacion);
        statement.setString(2, idLeccion);

        int filasAfectadas = statement.executeUpdate();
        return filasAfectadas > 0; // Retorna true si al menos una fila ha sido actualizada
    } catch (SQLException e) {
        e.printStackTrace(); // Muestra detalles del error en la consola en caso de una excepción SQL
        return false;
    }
}


  public List<ClsModeloConsultaProgreso> obtenerDetallesProgresoPorUsuario(int FKidUsuario) {
        List<ClsModeloConsultaProgreso> detallesProgresoList = new ArrayList<>();
        String sql = "SELECT p.*, l.titulo AS tituloLeccion, l.urlBanner AS urlBannerLeccion, c.nombre AS nombreCurso, " +
                     "i.nombre AS nombreIdioma " +
                     "FROM tbProgreso p " +
                     "INNER JOIN tbLecciones l ON p.FKidLeccion = l.idLeccion " +
                     "INNER JOIN tbCursos c ON p.FKidCurso = c.idCurso " +
                     "INNER JOIN tbIdiomas i ON p.FKidIdioma = i.idIdioma " +
                     "WHERE p.FKidUsuario = ?";

        try (Connection conexion = new ClsConexiondb().obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, FKidUsuario);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String tituloLeccion = resultSet.getString("tituloLeccion");
                String urlBannerLeccion = resultSet.getString("urlBannerLeccion");
                String nombreCurso = resultSet.getString("nombreCurso");
                String nombreIdioma = resultSet.getString("nombreIdioma");
                int puntuacion = resultSet.getInt("puntuacion");
                Date fechaProgreso = resultSet.getTimestamp("fechaProgreso");

                ClsModeloConsultaProgreso progreso = new ClsModeloConsultaProgreso(
                    tituloLeccion, urlBannerLeccion, nombreCurso, nombreIdioma, puntuacion, fechaProgreso
                );
                detallesProgresoList.add(progreso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones, log, etc.
        }

        return detallesProgresoList;
    }
    
}
