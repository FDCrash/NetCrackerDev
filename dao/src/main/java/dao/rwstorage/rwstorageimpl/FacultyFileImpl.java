package dao.rwstorage.rwstorageimpl;

import dao.entities.FacultyEntity;
import dao.rwstorage.RWStorage;
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
