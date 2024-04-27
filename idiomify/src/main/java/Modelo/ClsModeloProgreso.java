/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class ClsModeloProgreso {
    private int idProgreso;
    private int idFKidUsuario;
    private int idFKidIdioma;
    private String FKidCurso;
    private String FKidLeccion;
    private Date fechaProgreso;
    private int puntuacion;

    public ClsModeloProgreso() {
    }

    public ClsModeloProgreso(int idProgreso, int idFKidUsuario, int idFKidIdioma, String FKidCurso, String FKidLeccion, Date fechaProgreso, int puntuacion) {
        this.idProgreso = idProgreso;
        this.idFKidUsuario = idFKidUsuario;
        this.idFKidIdioma = idFKidIdioma;
        this.FKidCurso = FKidCurso;
        this.FKidLeccion = FKidLeccion;
        this.fechaProgreso = fechaProgreso;
        this.puntuacion = puntuacion;
    }

    public int getIdProgreso() {
        return idProgreso;
    }

    public void setIdProgreso(int idProgreso) {
        this.idProgreso = idProgreso;
    }

    public int getIdFKidUsuario() {
        return idFKidUsuario;
    }

    public void setIdFKidUsuario(int idFKidUsuario) {
        this.idFKidUsuario = idFKidUsuario;
    }

    public int getIdFKidIdioma() {
        return idFKidIdioma;
    }

    public void setIdFKidIdioma(int idFKidIdioma) {
        this.idFKidIdioma = idFKidIdioma;
    }

    public String getFKidCurso() {
        return FKidCurso;
    }

    public void setFKidCurso(String FKidCurso) {
        this.FKidCurso = FKidCurso;
    }

    public String getFKidLeccion() {
        return FKidLeccion;
    }

    public void setFKidLeccion(String FKidLeccion) {
        this.FKidLeccion = FKidLeccion;
    }

    public Date getFechaProgreso() {
        return fechaProgreso;
    }

    public void setFechaProgreso(Date fechaProgreso) {
        this.fechaProgreso = fechaProgreso;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    
    
}
