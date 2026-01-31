package db.bank_system;
import java.sql.*;
import java.util.*;

public class Transaction {
    private String username;
    private double amount;

     Transaction(String username, double amount) {
        this.username = username;
        this.amount = amount;
    }
    public void AccountTransaction(){
         Scanner sc = new Scanner(System.in);
         int cho;
         SearchUser su = new SearchUser();
         DeleteUser du = new DeleteUser();
        try (Connection c = DBconnection.getConnection()){
            if(amount == 0){
                PreparedStatement p = c.prepareStatement("UPDATE user SET balance = balance + ? WHERE user_name = ?");
                System.out.print("Enter Your Name   : ");
                String n = sc.nextLine();
                p.setString(2,n);
                System.out.print("Enter Your Amount : ");
                double amt = sc.nextDouble(); sc.nextLine();
                p.setDouble(1,amt);

                p.executeUpdate();
            } else {
                do {
                    System.out.println("||===============MENU================||");
                    System.out.println("||           1. Deposit              ||");
                    System.out.println("||           2. Withdraw             ||");
                    System.out.println("||           3. Transfer             ||");
                    System.out.println("||           4. View Mini-Statement  ||");
                    System.out.println("||           5. Search User          ||");
                    System.out.println("||           6. Delete Account       ||");
                    System.out.println("||           7. Exit                 ||");
                    System.out.println("||===================================||");

                    System.out.print("Enter Your Choice : ");
                    cho = sc.nextInt();
                    sc.nextLine();

                    if(cho == 1){
                        Statement s = c.createStatement();
                        ResultSet rs = s.executeQuery("SELECT * FROM user");
                       if(rs.next()) {
                           PreparedStatement p = c.prepareStatement("UPDATE user SET balance = balance + ? WHERE user_name = ?");
                           p.setString(2, this.username);
                           System.out.print("Enter Your Amount : ");
                           double amt = sc.nextDouble();sc.nextLine();
                           p.setDouble(1, amt);
                           int i = p.executeUpdate();

                           if(i > 0){
                               System.out.println("Successfully Deposited");
                           } else {
                             /*  if (amt <= 0) {
                                   System.out.println("You Can't Enter The Amount in Negative ");
                               }*/
                               System.out.println("Unsuccessful");
                           }
                       }
                    } else if (cho == 2) {
                            System.out.print("Enter Amount to withdraw : ");
                            double amt = sc.nextDouble();sc.nextLine();

                            if(amt > this.amount) {
                                System.out.println("Not Enough Balance ");
                            } else if (amt <= 0){
                                System.out.println("Error : Invalid amount ");
                            } else {
                                PreparedStatement p = c.prepareStatement("UPDATE user SET balance = balance - ? WHERE user_name = ?");
                                p.setDouble(1,amt);
                                p.setString(2,this.username);
                                int i = p.executeUpdate();
                                    if(i > 0){
                                        System.out.println("Withdrawn Successful");
                                    } else {
                                        System.out.println("Unsuccessful");
                                    }
                            }
                    } else if (cho == 3) {
                        try {
                            c.setAutoCommit(false);

                            // SENDER POV :
                            PreparedStatement sender = c.prepareStatement("UPDATE user SET balance = balance - ? WHERE user_name = ? AND balance >= ?");
                            System.out.print("Enter Amount to Send : ");
                            double amt = sc.nextDouble();
                            sc.nextLine();
                            sender.setDouble(1, amt);
                            sender.setString(2, this.username);
                            sender.setDouble(3, amt);
                            int i = sender.executeUpdate();

                            if (i == 0) {
                                System.out.println("Insufficient Amount or Invalid Account");
                            }

                            // Receiver POV :
                            PreparedStatement receiver = c.prepareStatement("UPDATE user SET balance = balance + ? WHERE user_name = ?");
                            receiver.setDouble(1, amt);
                            System.out.print("Enter Name of Receiver : ");
                            String rec_name = sc.nextLine();
                            receiver.setString(2, rec_name);
                            if(rec_name.equals(this.username)){
                                System.out.println("Sorry You Can't Send Money to Yourself !");
                                c.rollback();
                                continue;
                            }
                            int j = receiver.executeUpdate();

                            if(j == 0){
                                System.out.println("Receiver name not found ");
                                c.rollback();
                                continue;
                            }
                            // LOG POV :
                            PreparedStatement log = c.prepareStatement("INSERT INTO transactions(sender_Name,receiver_Name,amount) VALUES(?,?,?)");
                            log.setString(1, this.username);
                            log.setString(2, rec_name);
                            log.setDouble(3, amt);
                            log.executeUpdate();

                            c.commit();
                            System.out.println("Transfer Successful");
                        } catch (Exception e) {
                            try {
                                if (c != null) c.rollback();
                                System.out.println("Transfer Failed Money has been rolled back" ); e.printStackTrace();
                            } catch (SQLException e1){
                                System.out.println(e1);
                            }
                        }
                    } else if (cho == 4) {
                        System.out.println("\n||=========== MINI STATEMENT ==========||");
                        System.out.printf("%-10s | %-15s | %-10s | %-20s\n", "Type", "Opponent", "Amount", "Date");
                        System.out.println("------------------------------------------------------------");

                        String q = "SELECT * FROM transactions WHERE sender_Name = ? OR receiver_name = ? ORDER BY timestamp DESC LIMIT 5";
                        try(PreparedStatement p = c.prepareStatement(q)){
                            p.setString(1,this.username);
                            p.setString(2,this.username);

                            ResultSet rs = p.executeQuery();

                            while (rs.next()){
                                String sName = rs.getString("sender_name");
                                String rName = rs.getString("receiver_name");
                                double amount = rs.getDouble("amount");
                                Timestamp tp = rs.getTimestamp("timestamp");

                                if(sName.equals(this.username)){
                                    System.out.printf("%-10s | To: %-11s | -%-9.2f | %-20s\n", "DEBIT", rName, amount, tp);
                                } else {
                                    System.out.printf("%-10s | From: %-9s | +%-9.2f | %-20s\n", "CREDIT", sName, amount, tp);
                                }
                            }
                        }
                    } else if(cho == 5){
                      su.Search();
                    } else if (cho == 6) {
                        du.Delete(this.username);
                    } else if(cho == 7){
                        break;
                    } else {
                        System.out.println("Error : Invalid Choice !");
                    }
                } while(cho != 5);
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

}
