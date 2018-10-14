package api.apiControllers;

import api.businessControllers.AlbumBusinessController;
import api.dtos.AlbumDto;
import api.dtos.AlbumIdNombreArtistaNumPistaDto;
import api.exceptions.ArgumentNotValidException;

import java.util.List;

public class AlbumApiController {

    public static final String ALBUMES = "/albumes";

    public static final String ID_ID = "/{id}";

    public static final String GENERO = "/genero";

    public static final String SEARCH = "/search";

    private AlbumBusinessController albumBusinessController = new AlbumBusinessController();

    public String create(AlbumDto albumDto) {
        this.validate(albumDto, "albumDto");
        this.validate(albumDto.getNombre(), "AlbumDto Nombre");
        this.validate(albumDto.getArtista(), "AlbumDto Artista");
        this.validate(albumDto.getFechaEdicion(), "AlbumDto Fecha Edicion");
        this.validate(albumDto.getNumPistas(), "AlbumDto Numero Pistas");
        this.validate(albumDto.getGenero(), "AlbumDto Genero");
        this.validate(albumDto.getSelloId(), "AlbumDto Sello Id");
        return this.albumBusinessController.create(albumDto);
    }

    public void updateGenero(String albumId, String genero) {
        this.validate(genero, "Genero");
        this.albumBusinessController.updateGenero(albumId, genero);
    }

    public List<AlbumIdNombreArtistaNumPistaDto> find(String query) {
        this.validate(query, "query param q");
        if (!"numPistas".equals(query.split(":>=")[0])) {
            throw new ArgumentNotValidException("query param q is incorrect, missing 'numPistas:>='");
        }
        return this.albumBusinessController.findByNumPistasGreaterThanEqual(Double.valueOf(query.split(":>=")[1]));
    }

    public void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is NULL");
        }
    }
}
