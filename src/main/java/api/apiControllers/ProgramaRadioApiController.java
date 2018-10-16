package api.apiControllers;

import api.businessControllers.ProgramaRadioBusinessController;
import api.dtos.ProgramaRadioDto;
import api.exceptions.ArgumentNotValidException;

public class ProgramaRadioApiController extends ApiControllerValidateSupport{

    public static final String PROGRAMAS_RADIO = "/programas-radio";

    private ProgramaRadioBusinessController programaRadioBusinessController = new ProgramaRadioBusinessController();

    public String create(ProgramaRadioDto programaRadioDto) {
        this.validate(programaRadioDto);
        return this.programaRadioBusinessController.create(programaRadioDto);
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
