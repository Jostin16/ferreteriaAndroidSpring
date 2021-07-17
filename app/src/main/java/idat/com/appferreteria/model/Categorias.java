package idat.com.appferreteria.model;

public class Categorias {

    private int id;
    private String descripcion;
    private String url;

    public Categorias(int id, String descripcion, String url) {
        this.id = id;
        this.descripcion = descripcion;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
