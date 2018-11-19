package com.netcracker.denisik.rwstorage.rwstorageimpl;

import com.netcracker.denisik.entities.FacultyEntity;
import com.netcracker.denisik.rwstorage.RWStorage;
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
