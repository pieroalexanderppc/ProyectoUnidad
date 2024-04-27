package Modelo;

public class ClsModeloPregunta {
    private int idPregunta;
    private String FKidLeccion;
    private String enunciado;
    private String actividad;
    private String respuesta;
    private String urlIlustracion;
    private String urlAudio;

    // Constructor
    public ClsModeloPregunta() {
        // Constructor por defecto
    }

    public ClsModeloPregunta(int idPregunta, String FKidLeccion, String enunciado, String actividad, String respuesta, String urlIlustracion, String urlAudio) {
        this.idPregunta = idPregunta;
        this.FKidLeccion = FKidLeccion;
        this.enunciado = enunciado;
        this.actividad = actividad;
        this.respuesta = respuesta;
        this.urlIlustracion = urlIlustracion;
        this.urlAudio = urlAudio;
    }
    

    // Getters y Setters
    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getFKidLeccion() {
        return FKidLeccion;
    }

    public void setFKidLeccion(String FKidLeccion) {
        this.FKidLeccion = FKidLeccion;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getUrlIlustracion() {
        return urlIlustracion;
    }

    public void setUrlIlustracion(String urlIlustracion) {
        this.urlIlustracion = urlIlustracion;
    }

    public String getUrlAudio() {
        return urlAudio;
    }

    public void setUrlAudio(String urlAudio) {
        this.urlAudio = urlAudio;
    }
}
