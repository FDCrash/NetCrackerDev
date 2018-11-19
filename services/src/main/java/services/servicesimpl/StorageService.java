package services.servicesimpl;

import dao.rwstorage.rwstorageimpl.FullFillImpl;

public class StorageService {
    public void fillStorage(){
        new FullFillImpl().fillStorage();
    }
    public void fillFile(){
        new FullFillImpl().writeFile();
    }
}
