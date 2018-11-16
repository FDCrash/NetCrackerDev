import controllers.Menu;
import daomodule.rwstorage.rwstorageimpl.AdminFileImpl;
import daomodule.rwstorage.rwstorageimpl.StudentFileImpl;
import services.servicesimpl.StorageService;

public class Main {
    public static void main(String[] args) {
        new StorageService().fillStorage();
        Menu.getInstance().startMenu();
        new StudentFileImpl().writeFile();
    }
}
