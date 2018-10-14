package api.daos.memory;

import api.daos.AlbumDao;
import api.daos.DaoFactory;
import api.daos.ProgramaRadioDao;
import api.daos.SelloDao;

public class DaoMemoryFactory extends DaoFactory {

    private SelloDao selloDao;

    private AlbumDao albumDao;

    private ProgramaRadioDao programaRadioDao;

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

    @Override
    public ProgramaRadioDao getProgramaRadioDao() {
        if(programaRadioDao == null){
            programaRadioDao = new ProgramaRadioDaoMemory();
        }
        return programaRadioDao;
    }
}
