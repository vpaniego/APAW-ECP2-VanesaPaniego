package api.businessControllers;

import api.daos.DaoFactory;
import api.dtos.AlbumDto;
import api.dtos.AlbumQueryDto;
import api.entities.Album;
import api.entities.Genero;
import api.entities.Sello;
import api.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class AlbumBusinessController {

    public String create(AlbumDto albumDto) {
        Sello sello = DaoFactory.getFactory().getSelloDao().read(albumDto.getSelloId()).orElseThrow(() -> new NotFoundException("Sello id " + albumDto.getSelloId()));
        Album album = new Album(albumDto.getNombre(), albumDto.getArtista(), albumDto.getFechaEdicion(), albumDto.getNumPistas(), albumDto.getGenero(), sello);
        DaoFactory.getFactory().getAlbumDao().save(album);
        return album.getId();
    }

    public void updateGenero(String albumId, String genero) {
        Album album = DaoFactory.getFactory().getAlbumDao().read(albumId).orElseThrow(() -> new NotFoundException("Album id " + albumId));
        Genero generoToUpadte = Genero.valueOf(genero);
        album.setGenero(generoToUpadte);
        DaoFactory.getFactory().getAlbumDao().save(album);
    }

    public List<AlbumQueryDto> findByNumPistasGreaterThanEqual(Double value) {
        return DaoFactory.getFactory().getAlbumDao().findAll().stream()
                .filter(album -> album.getNumPistas() >= value)
                .map(AlbumQueryDto::new)
                .collect(Collectors.toList());
    }
}
