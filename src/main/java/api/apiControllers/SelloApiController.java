package api.apiControllers;

import api.businessControllers.SelloBusinessController;
import api.dtos.SelloDto;
import api.dtos.SelloIdNombreDto;
import api.exceptions.ArgumentNotValidException;

import java.util.List;

public class SelloApiController {

    public static final String SELLOS = "/sellos";

    public static final String ID_ID = "/{id}";

    private SelloBusinessController selloBusinessController = new SelloBusinessController();

    public String create(SelloDto selloDto) {
        this.validate(selloDto, "selloDto");
        this.validate(selloDto.getNombre(), "SelloDto Nombre");
        this.validate(selloDto.getSede(), "SelloDto Sede");
        return this.selloBusinessController.create(selloDto);
    }

    public void update(String id, SelloDto selloDto) {
        this.validate(selloDto, "selloDto");
        this.validate(selloDto.getSede(), "SelloDto Sede");
        this.selloBusinessController.updateSello(id, selloDto);
    }

    public void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is NULL");
        }
    }

    public List<SelloIdNombreDto> readAll() {
        return selloBusinessController.readAll();
    }

    public void delete(String id) {
        this.selloBusinessController.delete(id);
    }
}
