package api.businessControllers;

import api.daos.DaoFactory;
import api.dtos.SelloDto;
import api.dtos.SelloIdNombreDto;
import api.entities.Sello;
import api.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

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

    public List<SelloIdNombreDto> readAll() {
        List<Sello> sellos = DaoFactory.getFactory().getSelloDao().findAll();
        List<SelloIdNombreDto> sellosIdNombreDtos = new ArrayList<>();
        for (Sello sello : sellos) {
            sellosIdNombreDtos.add(new SelloIdNombreDto(sello));
        }
        return sellosIdNombreDtos;
    }
}
