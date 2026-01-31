package db.bank_system;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
public class DeleteUser {
    Scanner sc = new Scanner(System.in);
    public void Delete(String currentUser){
        try (Connection c = DBconnection.getConnection()){
            System.out.print("Enter User Name to Delete : ");
            String name = sc.nextLine();

            if(name.equals(currentUser)){
                System.out.println("Warning : Do you want to Close your account. Type 'Confirm' to proceed : ");
                String confirm = sc.nextLine();
                if(!confirm.equalsIgnoreCase("Confirm")){
                    System.out.println("Operation Aborted !");
                    return;
                }
            }
            String sql = "DELETE FROM user WHERE user_name = ?";
            try(PreparedStatement p = c.prepareStatement(sql)){
                p.setString(1,name);
                int i = p.executeUpdate();

                if (i > 0){
                    System.out.println("ACCOUNT DELETE SUCCESSFULLY !");
                } else {
                    System.out.println("User Not Found !");
                }
            } catch (Exception e1){
                e1.getStackTrace();
            }
        }catch (Exception e){
            e.getStackTrace();
        }
    }
}
