import backend.db.DBInitializer;

public class Main {
    public static void main(String[] args) {
        DBInitializer.initialize(); // Creates the tables if not exists

    }
}