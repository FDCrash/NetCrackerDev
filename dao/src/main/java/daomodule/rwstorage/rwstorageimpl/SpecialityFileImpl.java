package daomodule.rwstorage.rwstorageimpl;

import daomodule.entities.SpecialityEntity;
import daomodule.rwstorage.RWStorage;
import org.json.simple.JSONArray;

import java.util.List;

public class SpecialityFileImpl implements RWStorage {
    private Object obj;
    private List<SpecialityEntity> specialities;
    private JSONArray jsonArray;

    @Override
    public void fillStorage() {

    }
}
