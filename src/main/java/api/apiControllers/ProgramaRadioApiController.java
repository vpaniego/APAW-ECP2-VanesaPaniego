package api.apiControllers;

import api.businessControllers.ProgramaRadioBusinessController;
import api.dtos.ProgramaRadioDto;

import java.util.List;

public class ProgramaRadioApiController extends ApiControllerValidateSupport {

    public static final String PROGRAMAS_RADIO = "/programas-radio";

    public static final String ID_ID = "/{id}";

    public static final String ALBUMES = "/albumes";

    private ProgramaRadioBusinessController programaRadioBusinessController = new ProgramaRadioBusinessController();

    public String create(ProgramaRadioDto programaRadioDto) {
        this.validate(programaRadioDto);
        return this.programaRadioBusinessController.create(programaRadioDto);
    }

    public void addAlbumes(String programaRadioId, List<String> albumesId) {
        this.validateNotNull(albumesId, "albumes");
        this.programaRadioBusinessController.addAlbumes(programaRadioId, albumesId);
    }

    public void validate(Object objectDto) {
        ProgramaRadioDto programaRadioDto = (ProgramaRadioDto) objectDto;
        this.validateNotNull(programaRadioDto, "programaRadioDto");
        this.validateNotNull(programaRadioDto.getNombre(), "ProgramaRadioDto Nombre");
        this.validateNotNull(programaRadioDto.getNocturno(), "ProgramaRadioDto Nocturno");
        this.validateNotNull(programaRadioDto.getDiaEmision(), "ProgramaRadioDto DiaEmision");
        this.validateNotNull(programaRadioDto.getAlbumId(), "ProgramaRadioDto AlbumId");
    }
}
