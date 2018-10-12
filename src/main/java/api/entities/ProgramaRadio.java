package api.entities;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class ProgramaRadio {

    private String id;
    private boolean nocturno;
    private String nombre;
    private DayOfWeek diaEmision;
    private List<Album> albumes;

    public ProgramaRadio(boolean nocturno, String nombre, DayOfWeek diaEmision) {
        this.nocturno = nocturno;
        this.nombre = nombre;
        this.diaEmision = diaEmision;
        this.albumes = new ArrayList<Album>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isNocturno() {
        return nocturno;
    }

    public void setNocturno(boolean nocturno) {
        this.nocturno = nocturno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DayOfWeek getDiaEmision() {
        return diaEmision;
    }

    public void setDiaEmision(DayOfWeek diaEmision) {
        this.diaEmision = diaEmision;
    }

    public List<Album> getAlbumes() {
        return albumes;
    }

    public void setAlbumes(List<Album> albumes) {
        this.albumes = albumes;
    }

    public void addAlbum(Album album) {
        assert album != null;
        assert albumes != null;
        this.albumes.add(album);
    }

    public void removeAlbum(Album album) {
        assert album != null;
        assert albumes != null;
        this.albumes.remove(album);
    }

    @Override
    public String toString() {
        return "ProgramaRadio{" +
                "id='" + id + '\'' +
                ", nocturno=" + nocturno +
                ", nombre='" + nombre + '\'' +
                ", diaEmision=" + diaEmision +
                '}';
    }
}
