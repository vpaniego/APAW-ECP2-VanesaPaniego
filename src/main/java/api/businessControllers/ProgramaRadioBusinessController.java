package api.businessControllers;

import api.daos.DaoFactory;
import api.dtos.ProgramaRadioDto;
import api.entities.Album;
import api.entities.ProgramaRadio;
import api.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ProgramaRadioBusinessController {

    public String create(ProgramaRadioDto programaRadioDto) {
        Album album = DaoFactory.getFactory().getAlbumDao().read(programaRadioDto.getAlbumId()).orElseThrow(() -> new NotFoundException("Album id " + programaRadioDto.getAlbumId()));
        ProgramaRadio programaRadio = new ProgramaRadio(programaRadioDto.getNocturno(), programaRadioDto.getNombre(), programaRadioDto.getDiaEmision());
        programaRadio.addAlbum(album);
        DaoFactory.getFactory().getProgramaRadioDao().save(programaRadio);
        return programaRadio.getId();
    }

    public void addAlbumes(String programaRadioId, List<String> albumesId) {
        List<Album> albumes = new ArrayList<Album>();
        ProgramaRadio programaRadio = DaoFactory.getFactory().getProgramaRadioDao().read(programaRadioId)
                .orElseThrow(() -> new NotFoundException("ProgramaRadio (" + programaRadioId + ")"));

        for (String albumId : albumesId) {
            Album album = DaoFactory.getFactory().getAlbumDao().read(albumId)
                    .orElseThrow(() -> new NotFoundException("Album (" + albumId + ")"));
            programaRadio.addAlbum(album);
        }

        DaoFactory.getFactory().getProgramaRadioDao().save(programaRadio);
    }
}
