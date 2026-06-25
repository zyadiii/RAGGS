import backend.db.DBInitializer;
import frontend.windows.LoginWindow;

public class Main {

    public static void main(String[] args) {
        DBInitializer.initialize();

        new LoginWindow();
    }
}