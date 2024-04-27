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
public class ClsModeloConsultaProgreso {
    
        private String tituloLeccion;
    private String urlBannerLeccion;
    private String nombreCurso;
    private String nombreIdioma;
    private int puntuacion;
    private Date fechaProgreso;

    public ClsModeloConsultaProgreso() {
    }

    public ClsModeloConsultaProgreso(String tituloLeccion, String urlBannerLeccion, String nombreCurso, String nombreIdioma, int puntuacion, Date fechaProgreso) {
        this.tituloLeccion = tituloLeccion;
        this.urlBannerLeccion = urlBannerLeccion;
        this.nombreCurso = nombreCurso;
        this.nombreIdioma = nombreIdioma;
        this.puntuacion = puntuacion;
        this.fechaProgreso = fechaProgreso;
    }

    public String getTituloLeccion() {
        return tituloLeccion;
    }

    public void setTituloLeccion(String tituloLeccion) {
        this.tituloLeccion = tituloLeccion;
    }

    public String getUrlBannerLeccion() {
        return urlBannerLeccion;
    }

    public void setUrlBannerLeccion(String urlBannerLeccion) {
        this.urlBannerLeccion = urlBannerLeccion;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getNombreIdioma() {
        return nombreIdioma;
    }

    public void setNombreIdioma(String nombreIdioma) {
        this.nombreIdioma = nombreIdioma;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Date getFechaProgreso() {
        return fechaProgreso;
    }

    public void setFechaProgreso(Date fechaProgreso) {
        this.fechaProgreso = fechaProgreso;
    }
    
    
    
}
