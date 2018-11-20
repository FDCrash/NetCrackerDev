package com.netcracker.denisik.rwstorage.rwstorageimpl;

import com.netcracker.denisik.entities.AdminEntity;

import com.netcracker.denisik.rwstorage.RWStorage;

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
