package api.dtos;

import api.entities.Album;

public class AlbumQueryDto {

    private String id;

    private String nombre;

    private String artista;

    private Integer numPistas;

    public AlbumQueryDto(Album album) {
        this.id = album.getId();
        this.nombre = album.getNombre();
        this.artista = album.getArtista();
        this.numPistas = album.getNumPistas();
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

    public Integer getNumPistas() {
        return numPistas;
    }

    public void setNumPistas(Integer numPistas) {
        this.numPistas = numPistas;
    }

    @Override
    public String toString() {
        return "AlbumQueryDto{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", artista='" + artista + '\'' +
                ", numPistas=" + numPistas +
                '}';
    }
}
