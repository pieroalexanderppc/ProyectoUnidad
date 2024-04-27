package Modelo;

public class ClsModeloIdioma {
    private int idIdioma;
    private String nombre;
    private String descripcion;
    private String urlBanner;

    public ClsModeloIdioma() {
        // Constructor por defecto
    }

    public ClsModeloIdioma(int idIdioma, String nombre, String descripcion, String urlBanner) {
        this.idIdioma = idIdioma;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.urlBanner = urlBanner;
    }

    public int getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(int idIdioma) {
        this.idIdioma = idIdioma;
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

    public String getUrlBanner() {
        return urlBanner;
    }

    public void setUrlBanner(String urlBanner) {
        this.urlBanner = urlBanner;
    }
}
