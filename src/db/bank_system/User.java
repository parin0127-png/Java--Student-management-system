package db.bank_system;

public class User {
    private String User_name;
    private int UserId;
    private double balance;
    private int password;

        User(String User_name, int UserId, double balance, int password){
            this.User_name = User_name;
            this.UserId = UserId;
            this.balance = balance;
            this.password = password;
        }

        String getUser_name(){return User_name;}
        int getUserId(){return UserId;}
        double getBalance(){return balance;}
        int getPassword(){return password;}
}