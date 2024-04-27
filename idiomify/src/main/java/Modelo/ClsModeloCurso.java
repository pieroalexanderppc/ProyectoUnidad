package Modelo;

public class ClsModeloCurso {
    private String idCurso;
    private int FKidIdioma;
    private String urlBanner;
    private String nombre;
    private String descripcion;

    public ClsModeloCurso() {
        // Constructor por defecto
    }

    public ClsModeloCurso(String idCurso, int FKidIdioma, String urlBanner, String nombre, String descripcion) {
        this.idCurso = idCurso;
        this.FKidIdioma = FKidIdioma;
        this.urlBanner = urlBanner;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public int getFKidIdioma() {
        return FKidIdioma;
    }

    public void setFKidIdioma(int FKidIdioma) {
        this.FKidIdioma = FKidIdioma;
    }

    public String getUrlBanner() {
        return urlBanner;
    }

    public void setUrlBanner(String urlBanner) {
        this.urlBanner = urlBanner;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
