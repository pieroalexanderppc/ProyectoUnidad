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
public class ClsModeloHistorialUsuario {
    private int idHistorial;
    private int FKidUsuario;
    private String FKidiLeccion;
    private Timestamp fecha;

    public ClsModeloHistorialUsuario() {
    }

    public ClsModeloHistorialUsuario(int idHistorial, int FKidUsuario, String FKidiLeccion, Timestamp fecha) {
        this.idHistorial = idHistorial;
        this.FKidUsuario = FKidUsuario;
        this.FKidiLeccion = FKidiLeccion;
        this.fecha = fecha;
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public int getFKidUsuario() {
        return FKidUsuario;
    }

    public void setFKidUsuario(int FKidUsuario) {
        this.FKidUsuario = FKidUsuario;
    }

    public String getFKidiLeccion() {
        return FKidiLeccion;
    }

    public void setFKidiLeccion(String FKidiLeccion) {
        this.FKidiLeccion = FKidiLeccion;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
    
    
}
