package api.daos.memory;

import api.daos.SelloDao;
import api.entities.Sello;

import java.util.HashMap;
import java.util.Map;

public class SelloDaoMemory extends GenericDaoMemory<Sello> implements SelloDao {

    public SelloDaoMemory() {
        super(new HashMap<>());
    }

    @Override
    public String getId(Sello sello) {
        return sello.getId();
    }

    @Override
    public void setId(Sello sello, String id) {
        sello.setId(id);
    }
}
