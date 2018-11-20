package com.netcracker.denisik.rwstorage.rwstorageimpl;

import com.netcracker.denisik.entities.SpecialityEntity;
import com.netcracker.denisik.rwstorage.RWStorage;
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
