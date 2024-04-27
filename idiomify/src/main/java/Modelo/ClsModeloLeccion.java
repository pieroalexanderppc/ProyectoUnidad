package Modelo;

public class ClsModeloLeccion {
    private String idLeccion;
    private String FKidCurso;
    private int FKidIdioma;
    private String titulo;
    private String urlBanner;

    public ClsModeloLeccion() {
        // Constructor por defecto
    }

    public ClsModeloLeccion(String idLeccion, String FKidCurso, int FKidIdioma, String titulo, String urlBanner) {
        this.idLeccion = idLeccion;
        this.FKidCurso = FKidCurso;
        this.FKidIdioma = FKidIdioma;
        this.titulo = titulo;
        this.urlBanner = urlBanner;
    }

    public String getIdLeccion() {
        return idLeccion;
    }

    public void setIdLeccion(String idLeccion) {
        this.idLeccion = idLeccion;
    }

    public String getFKidCurso() {
        return FKidCurso;
    }

    public void setFKidCurso(String FKidCurso) {
        this.FKidCurso = FKidCurso;
    }

    public int getFKidIdioma() {
        return FKidIdioma;
    }

    public void setFKidIdioma(int FKidIdioma) {
        this.FKidIdioma = FKidIdioma;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrlBanner() {
        return urlBanner;
    }

    public void setUrlBanner(String urlBanner) {
        this.urlBanner = urlBanner;
    }
}
