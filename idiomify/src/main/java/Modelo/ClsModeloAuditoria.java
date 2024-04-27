/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Timestamp;

/**
 *
 * @author usuario
 */
public class ClsModeloAuditoria {
    private int idRegistro;
    private int FKidAdmin;
    private String accion;
    private Timestamp fecha;

    public ClsModeloAuditoria() {
    }

    public ClsModeloAuditoria(int idRegistro, int FKidAdmin, String accion, Timestamp fecha) {
        this.idRegistro = idRegistro;
        this.FKidAdmin = FKidAdmin;
        this.accion = accion;
        this.fecha = fecha;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getFKidAdmin() {
        return FKidAdmin;
    }

    public void setFKidAdmin(int FKidAdmin) {
        this.FKidAdmin = FKidAdmin;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
    
}
