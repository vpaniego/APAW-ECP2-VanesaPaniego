package api.entities;

import java.time.LocalDateTime;

public class Album {

    private String id;
    private String nombre;
    private String artista;
    private LocalDateTime fechaEdicion;
    private Integer numPistas;
    private Genero genero;
    private Sello sello;

    public Album(String nombre, String artista, LocalDateTime fechaEdicion, Integer numPistas, Genero genero, Sello sello) {
        this.nombre = nombre;
        this.artista = artista;
        this.fechaEdicion = fechaEdicion;
        this.numPistas = numPistas;
        this.genero = genero;
        this.sello = sello;
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

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public LocalDateTime getFechaEdicion() {
        return fechaEdicion;
    }

    public void setFechaEdicion(LocalDateTime fechaEdicion) {
        this.fechaEdicion = fechaEdicion;
    }

    public Integer getNumPistas() {
        return numPistas;
    }

    public void setNumPistas(Integer numPistas) {
        this.numPistas = numPistas;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Sello getSello() {
        return sello;
    }

    public void setSello(Sello sello) {
        this.sello = sello;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaEdicion=" + fechaEdicion +
                ", numPistas=" + numPistas +
                ", genero=" + genero +
                '}';
    }
}
