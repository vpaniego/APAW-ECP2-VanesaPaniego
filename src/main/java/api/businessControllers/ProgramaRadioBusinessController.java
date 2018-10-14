package api.businessControllers;

import api.daos.DaoFactory;
import api.dtos.ProgramaRadioDto;
import api.entities.Album;
import api.entities.ProgramaRadio;
import api.exceptions.NotFoundException;

public class ProgramaRadioBusinessController {

    public String create(ProgramaRadioDto programaRadioDto) {
        Album album = DaoFactory.getFactory().getAlbumDao().read(programaRadioDto.getAlbumId()).orElseThrow(() -> new NotFoundException("Album id " + programaRadioDto.getAlbumId()));
        ProgramaRadio programaRadio = new ProgramaRadio(programaRadioDto.getNocturno(), programaRadioDto.getNombre(), programaRadioDto.getDiaEmision());
        programaRadio.addAlbum(album);
        DaoFactory.getFactory().getProgramaRadioDao().save(programaRadio);
        return programaRadio.getId();
    }
}
