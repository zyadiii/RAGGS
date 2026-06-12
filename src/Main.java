import java.util.Scanner;
import backend.db.DBInitializer;

public class Main {

    public static void main(String[] args) {

        DBInitializer.initialize();

        Scanner sc = new Scanner(System.in);

        System.out.println("===== STUDENT RECORD SYSTEM =====");

        // Login will go here

        boolean running = true;

        while (running) {

            System.out.println("\n1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Generate Report");
            System.out.println("7. Exit");

            System.out.print("Choice: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    System.out.println("Add Student");
                    break;

                case 2:
                    System.out.println("View Students");
                    break;

                case 3:
                    System.out.println("Search Student");
                    break;

                case 4:
                    System.out.println("Update Student");
                    break;

                case 5:
                    System.out.println("Delete Student");
                    break;

                case 6:
                    System.out.println("Generate Report");
                    break;

                case 7:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}