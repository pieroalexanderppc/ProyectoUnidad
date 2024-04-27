/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Configdb.ClsConexiondb;
import Modelo.ClsModeloHistorialUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class ClsModeloDaoHistorialUsuario {
    private final Connection conexion;
    
    public ClsModeloDaoHistorialUsuario() {
        // Obtener la conexión a la base de datos desde ClsConexiondb
        conexion = new ClsConexiondb().obtenerConexion();
    }
   public boolean agregarHistorial(ClsModeloHistorialUsuario historialUsuario) {
    System.out.println("Estamos en agregar Historial");
    System.out.println("FKUSUARIO=" + historialUsuario.getFKidUsuario());
    System.out.println("FKLECCION=" + historialUsuario.getFKidiLeccion());
    String sql = "INSERT INTO historialUsuario (FKidUsuario, FKidLeccion) VALUES (?, ?);";
    try (PreparedStatement statement = conexion.prepareStatement(sql)) {
        statement.setInt(1, historialUsuario.getFKidUsuario());
        statement.setString(2, historialUsuario.getFKidiLeccion());
        int filasAfectadas = statement.executeUpdate();
        return filasAfectadas > 0;
    } catch (SQLException e) {
        System.out.println("GHay una exception");
        e.printStackTrace();
        return false;
    }
}
   

public List<ClsModeloHistorialUsuario> listarHistorial(int FKidUsuario) {
    List<ClsModeloHistorialUsuario> historial = new ArrayList<>();
    
    String sql = "SELECT idHistorial, FKidLeccion, fecha FROM historialUsuario WHERE FKidUsuario = ?";
    System.out.println("El sql es: "+sql);
    
    try (PreparedStatement statement = conexion.prepareStatement(sql)) {
        statement.setInt(1, FKidUsuario);
        
        try (ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                ClsModeloHistorialUsuario historialUsuario = new ClsModeloHistorialUsuario();
                
                historialUsuario.setIdHistorial(result.getInt("idHistorial"));
                historialUsuario.setFKidiLeccion(result.getString("FKidLeccion"));
                historialUsuario.setFecha(result.getTimestamp("fecha"));
                historialUsuario.setFKidUsuario(FKidUsuario);
                
                historial.add(historialUsuario);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Manejo de errores de SQL
    }
    System.out.println("Historial"+historial);
    return historial;
}


}
