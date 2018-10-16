package api.apiControllers;

import api.businessControllers.SelloBusinessController;
import api.dtos.SelloDto;
import api.dtos.SelloIdNombreDto;
import api.exceptions.ArgumentNotValidException;

import java.util.List;

public class SelloApiController extends ApiControllerValidateSupport{

    public static final String SELLOS = "/sellos";

    public static final String ID_ID = "/{id}";

    private SelloBusinessController selloBusinessController = new SelloBusinessController();

    public String create(SelloDto selloDto) {
        this.validate(selloDto);
        return this.selloBusinessController.create(selloDto);
    }

    public void update(String id, SelloDto selloDto) {
        this.validate(selloDto);
        this.selloBusinessController.updateSello(id, selloDto);
    }

    public void validate(Object objectDto) {
        SelloDto selloDto = (SelloDto) objectDto;
        this.validateNotNull(selloDto, "selloDto");
        this.validateNotNull(selloDto.getNombre(), "SelloDto Nombre");
        this.validateNotNull(selloDto.getSede(), "SelloDto Sede");
    }

    public List<SelloIdNombreDto> readAll() {
        return selloBusinessController.readAll();
    }

    public void delete(String id) {
        this.selloBusinessController.delete(id);
    }
}
