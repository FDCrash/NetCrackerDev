package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.rwstorage.rwstorageimpl.FullFillImpl;

public class StorageService {
    public void fillStorage(){
        new FullFillImpl().fillStorage();
    }

    public void fillFile(){
        new FullFillImpl().writeFile();
    }
}
