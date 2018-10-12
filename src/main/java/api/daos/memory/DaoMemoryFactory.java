package api.daos.memory;

import api.daos.DaoFactory;
import api.daos.SelloDao;

public class DaoMemoryFactory extends DaoFactory {

    private SelloDao selloDao;

    @Override
    public SelloDao getSelloDao() {
        if (selloDao == null) {
            selloDao = new SelloDaoMemory();
        }
        return selloDao;
    }

}
