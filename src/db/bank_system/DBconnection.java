package db.bank_system;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
    private static final String URL = "jdbc:mysql://localhost:3306/bank";
    private static final String User = "root";
    private static final String Pass = "root";

        public static Connection getConnection() throws Exception{
            return DriverManager.getConnection(URL,User,Pass);
        }
}
