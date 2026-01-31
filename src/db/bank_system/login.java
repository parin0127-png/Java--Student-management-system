package db.bank_system;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
public class login {
    public void loginpage(){
        Scanner sc = new Scanner(System.in);

        try (Connection c = DBconnection.getConnection()){
            System.out.print("Enter Your Username : ");
            String name = sc.nextLine();

            System.out.print("Enter Your PassWord : ");
            String pass = sc.nextLine();


            PreparedStatement p = c.prepareStatement("SELECT * FROM user WHERE user_name = ?");
            p.setString(1,name);
            ResultSet rs = p.executeQuery();
            if(rs.next()) {
                String hashpass = rs.getString("password");
                String loginpass = SecurityUtils.hashpassword(pass);

                if(loginpass.equals(hashpass)){
                    System.out.println("login Successful");
                    String namefromDb = rs.getString("user_name");
                    double balDb = rs.getDouble("balance");
                    Transaction tr = new Transaction(namefromDb,balDb);
                    tr.AccountTransaction();
                } else {
                    System.out.println("ERROR ");
                }
            } else {
                System.out.println("Failed to Login please try again later !");
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
