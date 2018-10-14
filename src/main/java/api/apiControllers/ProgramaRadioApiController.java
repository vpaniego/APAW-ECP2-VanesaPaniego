package api.apiControllers;

import api.businessControllers.ProgramaRadioBusinessController;
import api.dtos.ProgramaRadioDto;
import api.exceptions.ArgumentNotValidException;

public class ProgramaRadioApiController {

    public static final String PROGRAMAS_RADIO = "/programas-radio";

    private ProgramaRadioBusinessController programaRadioBusinessController = new ProgramaRadioBusinessController();

    public String create(ProgramaRadioDto programaRadioDto) {
        this.validate(programaRadioDto, "programaRadioDto");
        this.validate(programaRadioDto.getNombre(), "ProgramaRadioDto Nombre");
        this.validate(programaRadioDto.getNocturno(), "ProgramaRadioDto Nocturno");
        this.validate(programaRadioDto.getDiaEmision(), "ProgramaRadioDto DiaEmision");
        this.validate(programaRadioDto.getAlbumId(), "ProgramaRadioDto AlbumId");
        return this.programaRadioBusinessController.create(programaRadioDto);
    }

    public void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is NULL");
        }
    }
}
