package api.apiControllers;

import api.businessControllers.SelloBusinessController;
import api.dtos.SelloDto;
import api.exceptions.ArgumentNotValidException;

public class SelloApiController {

    public static final String SELLOS = "/sellos";

    private SelloBusinessController selloBusinessController = new SelloBusinessController();

    public String create(SelloDto selloDto) {
        this.validate(selloDto, "selloDto");
        this.validate(selloDto.getNombre(), "SelloDto Nombre");
        this.validate(selloDto.getSede(), "SelloDto Sede");
        return this.selloBusinessController.create(selloDto);
    }

    public void validate(Object property, String message){
        if(property == null){
            throw new ArgumentNotValidException(message + " is NULL");
        }

    }

}