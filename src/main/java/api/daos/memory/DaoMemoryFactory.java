package api.daos.memory;

import api.daos.AlbumDao;
import api.daos.DaoFactory;
import api.daos.SelloDao;

public class DaoMemoryFactory extends DaoFactory {

    private SelloDao selloDao;

    private AlbumDao albumDao;

    @Override
    public SelloDao getSelloDao() {
        if (selloDao == null) {
            selloDao = new SelloDaoMemory();
        }
        return selloDao;
    }

    @Override
    public AlbumDao getAlbumDao() {
        if(albumDao == null) {
            albumDao = new AlbumDaoMemory();
        }
        return albumDao;
    }

}
