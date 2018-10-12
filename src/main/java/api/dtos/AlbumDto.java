package api.dtos;

import api.entities.Genero;

import java.time.LocalDateTime;

public class AlbumDto {

    private String nombre;
    private String artista;
    private LocalDateTime fechaEdicion;
    private Integer numPistas;
    private Genero genero;
    private String selloId;

    public AlbumDto(String nombre, String artista, LocalDateTime fechaEdicion, Integer numPistas, Genero genero, String selloId){
        this.nombre = nombre;
        this.artista = artista;
        this.fechaEdicion = fechaEdicion;
        this.numPistas = numPistas;
        this.genero = genero;
        this.selloId = selloId;
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

    public String getSelloId() {
        return selloId;
    }

    public void setSelloId(String selloId) {
        this.selloId = selloId;
    }


    @Override
    public String toString() {
        return "AlbumDto{" +
                "nombre='" + nombre + '\'' +
                ", artista='" + artista + '\'' +
                ", fechaEdicion=" + fechaEdicion +
                ", numPistas=" + numPistas +
                ", genero=" + genero +
                ", selloId='" + selloId + '\'' +
                '}';
    }
}
