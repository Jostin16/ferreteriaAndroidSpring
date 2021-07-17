package idat.com.appferreteria.model;

public class Comentarios{

    private int id;
    private String descripcion;
    private String nombre_usuario;
    private String fecha;
    private String urlFoto;

    public Comentarios(String descripcion, String nombre_usuario, String fecha, String urlFoto) {
        this.descripcion = descripcion;
        this.nombre_usuario = nombre_usuario;
        this.fecha = fecha;
        this.urlFoto = urlFoto;
    }

    public int getId() {
        /*String[] arrayUrl = urlFoto.split("/");
        id = Integer.parseInt(arrayUrl[arrayUrl.length -1]);*/
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

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}
