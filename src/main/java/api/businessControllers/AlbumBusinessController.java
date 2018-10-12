package api.businessControllers;

import api.daos.DaoFactory;
import api.dtos.AlbumDto;
import api.entities.Album;
import api.entities.Sello;
import api.exceptions.NotFoundException;

public class AlbumBusinessController {

    public String create(AlbumDto albumDto) {
        Sello sello = DaoFactory.getFactory().getSelloDao().read(albumDto.getSelloId()).orElseThrow(() -> new NotFoundException("Sello id " + albumDto.getSelloId()));
        Album album = new Album(albumDto.getNombre(), albumDto.getArtista(), albumDto.getFechaEdicion(), albumDto.getNumPistas(), albumDto.getGenero(), sello);
        DaoFactory.getFactory().getAlbumDao().save(album);
        return album.getId();
    }
}
