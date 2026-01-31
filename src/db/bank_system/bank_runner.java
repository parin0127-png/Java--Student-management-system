package db.bank_system;
import java.util.*;
class Start{
    Scanner sc = new Scanner(System.in);
    void start(){
        Registration r = new Registration();
        login l = new login();
        int choice;
        do {
            System.out.println("||======= WELCOME TO THE BANK =======||");
            System.out.println("||      1. Register (New User)       ||");
            System.out.println("||      2. Login (Existing User)     ||");
            System.out.println("||      3. Exit                      ||");
            System.out.println("||===================================||");
            System.out.print("||============ Choice ===============||");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> r.user_resgistration();
                case 2 -> l.loginpage();
                case 3 -> {
                    System.out.println("Exiting.........");
                }
                default -> {
                    System.out.println("Error Invalid Choice !");
                }
            }
        }while (choice != 3);
    }
}
public class bank_runner {
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Start s = new Start();
        s.start();
    }
}
