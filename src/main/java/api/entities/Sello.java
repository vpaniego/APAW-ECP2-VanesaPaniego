package api.entities;

public class Sello {

    private String id;
    private String nombre;
    private String sede;

    public Sello(String id, String nombre, String sede) {
        this.id = id;
        this.nombre = nombre;
        this.sede = sede;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    @Override
    public String toString() {
        return "Sello{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", sede='" + sede + '\'' +
                '}';
    }
}
