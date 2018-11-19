package dao.rwstorage.rwstorageimpl;

import dao.entities.AdminEntity;

import dao.rwstorage.RWStorage;

import org.json.simple.JSONArray;

import java.util.List;

public class AdminFileImpl implements RWStorage {
    private Object obj;
    private List<AdminEntity> admins;
    private JSONArray jsonArray;

    @Override
    public void fillStorage() {

    }
}
