/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Configdb.ClsConexiondb;
import Modelo.ClsModeloAuditoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class ClsModeloDaoAuditoria {
    ClsConexiondb conexionDB = new ClsConexiondb();
    Connection conexion = conexionDB.obtenerConexion();
    public boolean agregarAuditoria(ClsModeloAuditoria auditoria) {
        String sql = "INSERT INTO tbAuditoria (FKidAdmin, accion) VALUES (?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, auditoria.getFKidAdmin());
            statement.setString(2, auditoria.getAccion());
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public List<ClsModeloAuditoria> obtenerAuditoria() {
        List<ClsModeloAuditoria> listaAuditoria = new ArrayList<>();

        String sql = "SELECT * FROM tbAuditoria";

        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                ClsModeloAuditoria auditoria = new ClsModeloAuditoria();
                auditoria.setIdRegistro(rs.getInt("idRegistro"));
                auditoria.setFKidAdmin(rs.getInt("FKidAdmin"));
                auditoria.setAccion(rs.getString("accion"));
                auditoria.setFecha(rs.getTimestamp("fecha"));

                listaAuditoria.add(auditoria);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaAuditoria;
    }
}