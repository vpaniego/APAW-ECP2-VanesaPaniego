package api.businessControllers;

import api.daos.DaoFactory;
import api.dtos.SelloDto;
import api.entities.Sello;
import api.exceptions.NotFoundException;

public class SelloBusinessController {

    public String create(SelloDto selloDto) {
        Sello sello = new Sello(selloDto.getNombre(), selloDto.getSede());
        DaoFactory.getFactory().getSelloDao().save(sello);
        return sello.getId();
    }

    public void updateSello(String id, SelloDto selloDto) {
        Sello sello = DaoFactory.getFactory().getSelloDao().read(id).orElseThrow(() -> new NotFoundException("Sello id " + id));
        sello.setSede(selloDto.getSede());
        DaoFactory.getFactory().getSelloDao().save(sello);
    }
}
