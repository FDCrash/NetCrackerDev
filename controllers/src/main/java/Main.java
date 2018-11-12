import controllers.Menu;
import services.servicesimpl.StorageService;

public class Main {
    public static void main(String[] args) {
        new StorageService().fillStorage();
        new Menu().startMenu();
    }
}
