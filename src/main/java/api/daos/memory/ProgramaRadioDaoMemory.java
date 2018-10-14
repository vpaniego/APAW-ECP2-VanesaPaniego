package api.daos.memory;

import api.daos.ProgramaRadioDao;
import api.entities.ProgramaRadio;

import java.util.HashMap;

public class ProgramaRadioDaoMemory extends GenericDaoMemory<ProgramaRadio> implements ProgramaRadioDao {

    public ProgramaRadioDaoMemory() {
        super(new HashMap<>());
    }

    @Override
    public String getId(ProgramaRadio programaRadio) {
        return programaRadio.getId();
    }

    @Override
    public void setId(ProgramaRadio programaRadio, String id) {
        programaRadio.setId(id);
    }
}
