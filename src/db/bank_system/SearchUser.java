package db.bank_system;
import db.Exam.DBcon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
public class SearchUser {
    public void Search() {
        try {
            Connection c = DBconnection.getConnection();
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter User Name to User Account : ");
            String name = sc.nextLine();

            String search = "SELECT user_name, balance FROM user WHERE user_name = ?";
            PreparedStatement p = c.prepareStatement(search);
            p.setString(1,name);
                try(ResultSet rs = p.executeQuery()){
                    if(rs.next()){
                        System.out.println("|+-+-+-+-+-+ACCOUNT FOUND+-+-+-+-+-+|");
                        System.out.print("| User_Name" + "\t" + "\t \t Balance " + "\t" + "\t|");
                        System.out.printf("\n|+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+|");
                        System.out.printf("\n" + "| %-15s \t %-15s| \t",rs.getString("user_name"), rs.getString("balance"));
                        System.out.printf("\n|+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+|\n");
                    } else {
                        System.out.println("user not found");
                    }
                } catch (Exception e1){
                    e1.getStackTrace();
                }

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
