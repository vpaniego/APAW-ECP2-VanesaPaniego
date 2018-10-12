package api.dtos;

import api.entities.Sello;

public class SelloIdNombreDto {

    private String id;

    private String nombre;

    public SelloIdNombreDto(Sello sello) {
        this.id = sello.getId();
        this.nombre = sello.getNombre();
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

    @Override
    public String toString() {
        return "SelloIdNombreDto{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
