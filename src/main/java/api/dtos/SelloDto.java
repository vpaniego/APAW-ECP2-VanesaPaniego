package api.dtos;

public class SelloDto {

    private String id;

    private String nombre;

    private String sede;

    public SelloDto(String nombre, String sede) {
        this.nombre = nombre;
        this.sede = sede;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SelloDto{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", sede='" + sede + '\'' +
                '}';
    }
}
