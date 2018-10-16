package api.dtos;

import api.entities.Genero;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ProgramaRadioDto {

    private String nombre;
    private Boolean nocturno;
    private DayOfWeek diaEmision;
    private String albumId;

    public ProgramaRadioDto(String nombre, Boolean nocturno, DayOfWeek diaEmision, String albumId) {
        this.nombre = nombre;
        this.nocturno = nocturno;
        this.diaEmision = diaEmision;
        this.albumId = albumId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getNocturno() {
        return nocturno;
    }

    public void setNocturno(Boolean nocturno) {
        this.nocturno = nocturno;
    }

    public DayOfWeek getDiaEmision() {
        return diaEmision;
    }

    public void setDiaEmision(DayOfWeek diaEmision) {
        this.diaEmision = diaEmision;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    @Override
    public String toString() {
        return "ProgramaRadioDto{" +
                "nombre='" + nombre + '\'' +
                ", nocturno=" + nocturno +
                ", diaEmision=" + diaEmision +
                ", albumId='" + albumId + '\'' +
                '}';
    }
}
