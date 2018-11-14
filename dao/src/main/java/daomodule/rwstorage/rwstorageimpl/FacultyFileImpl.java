package daomodule.rwstorage.rwstorageimpl;

import daomodule.entities.FacultyEntity;
import daomodule.rwstorage.RWStorage;
import org.json.simple.JSONArray;

import java.util.List;

public class FacultyFileImpl implements RWStorage {
    private Object obj;
    private List<FacultyEntity> faculties;
    private JSONArray jsonArray;

    @Override
    public void fillStorage() {
    }
}
