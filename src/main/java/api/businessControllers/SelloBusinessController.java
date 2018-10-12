package api.businessControllers;

import api.daos.DaoFactory;
import api.dtos.SelloDto;
import api.entities.Sello;

public class SelloBusinessController {

    public String create(SelloDto selloDto) {
        Sello sello = new Sello(selloDto.getNombre(), selloDto.getSede());
        DaoFactory.getFactory().getSelloDao().save(sello);
        return sello.getId();
    }
}
