package idat.com.appferreteria.model;

public class Promocion{

    private int id=5;
    private String nombre_producto;
    private Double precio_normal;
    private Double precio_oferta;
    private String url;

    public Promocion(String nombre_producto, Double precio_normal, Double precio_oferta, String url) {
        this.nombre_producto = nombre_producto;
        this.precio_normal = precio_normal;
        this.precio_oferta = precio_oferta;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public Double getPrecio_normal() {
        return precio_normal;
    }

    public void setPrecio_normal(Double precio_normal) {
        this.precio_normal = precio_normal;
    }

    public Double getPrecio_oferta() {
        return precio_oferta;
    }

    public void setPrecio_oferta(Double precio_oferta) {
        this.precio_oferta = precio_oferta;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
