package db.bank_system;
import java.sql.PreparedStatement;
import java.util.*;
import java.sql.Connection;
import java.security.*;
    public class Registration {
        public void user_resgistration() {
            Scanner sc = new Scanner(System.in);
            try (Connection c = DBconnection.getConnection()) {
                String sql = "INSERT INTO user(user_name, password) VALUES(?,?)";
                PreparedStatement p = c.prepareStatement(sql);
                System.out.print("Enter Your User Name : ");
                String n = sc.nextLine();
                p.setString(1, n);
                System.out.print("Enter Your Password : ");
                String rawPass = sc.nextLine();

                String passwo = SecurityUtils.hashpassword(rawPass);
                p.setString(2, passwo);
                p.executeUpdate();

                System.out.println("User Data Added to 'BANK' database ");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
