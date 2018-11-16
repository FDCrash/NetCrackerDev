import controllers.Menu;
import services.servicesimpl.StorageService;

public class Main {
    public static void main(String[] args) {
        new StorageService().fillStorage();
        Menu.getInstance().startMenu();
        new StorageService().fillFile();
    }
}
