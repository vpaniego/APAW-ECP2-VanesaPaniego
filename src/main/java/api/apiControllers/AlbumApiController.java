package api.apiControllers;

import api.businessControllers.AlbumBusinessController;
import api.dtos.AlbumDto;
import api.dtos.AlbumQueryDto;
import api.exceptions.ArgumentNotValidException;

import java.util.List;

public class AlbumApiController extends ApiControllerValidateSupport{

    public static final String ALBUMES = "/albumes";

    public static final String ID_ID = "/{id}";

    public static final String GENERO = "/genero";

    public static final String SEARCH = "/search";

    private AlbumBusinessController albumBusinessController = new AlbumBusinessController();

    public String create(AlbumDto albumDto) {
        this.validate(albumDto);
        return this.albumBusinessController.create(albumDto);
    }

    public void updateGenero(String albumId, String genero) {
        this.validateNotNull(genero, "Genero");
        this.albumBusinessController.updateGenero(albumId, genero);
    }

    public List<AlbumQueryDto> find(String query) {
        this.validateNotNull(query, "query param q");
        this.validateNotEquals("numPistas", query.split(":>=")[0].toString(), "query param q is incorrect, missing 'numPistas:>='");
        return this.albumBusinessController.findByNumPistasGreaterThanEqual(Double.valueOf(query.split(":>=")[1]));
    }

    public void validate(Object objectDto) {
        AlbumDto albumDto = (AlbumDto) objectDto;
        this.validateNotNull(albumDto, "albumDto");
        this.validateNotNull(albumDto.getNombre(), "AlbumDto Nombre");
        this.validateNotNull(albumDto.getArtista(), "AlbumDto Artista");
        this.validateNotNull(albumDto.getFechaEdicion(), "AlbumDto Fecha Edicion");
        this.validateNotNull(albumDto.getNumPistas(), "AlbumDto Numero Pistas");
        this.validateNotNull(albumDto.getGenero(), "AlbumDto Genero");
        this.validateNotNull(albumDto.getSelloId(), "AlbumDto Sello Id");
    }
}
