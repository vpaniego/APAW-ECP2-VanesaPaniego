package api.daos.memory;

import api.daos.AlbumDao;
import api.entities.Album;

import java.util.HashMap;
import java.util.Map;

public class AlbumDaoMemory extends GenericDaoMemory<Album> implements AlbumDao {

    AlbumDaoMemory() {
        super(new HashMap<>());
    }

    @Override
    public String getId(Album album) {
        return album.getId();
    }

    @Override
    public void setId(Album album, String id) {
        album.setId(id);

    }
}
