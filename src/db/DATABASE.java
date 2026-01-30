package db;

import java.sql.*;
import java.util.*;

abstract class serviceS{
    abstract void add();
    abstract void update();
    abstract void view();
    abstract void search();
    abstract void delete();
}
class ServiceSimpl extends serviceS{
    Scanner sc = new Scanner(System.in);

    void add(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/DATA","root","root");

            Statement s = c.createStatement();

            ResultSet rs = s.executeQuery("SELECT * FROM student LIMIT 1");
            ResultSetMetaData rsm = rs.getMetaData();
            int colc = rsm.getColumnCount();

            StringBuilder st = new StringBuilder("INSERT INTO student VALUES (");
            for(int i = 0; i < colc; i++){
                st.append("?,");
            }
            st.deleteCharAt(st.length() -1);
            st.append(")");

            PreparedStatement p = c.prepareStatement(st.toString());

            for(int i = 1; i <= colc; i++){
                System.out.print("Enter " + rsm.getColumnName(i) + ": ");
                p.setString(i,sc.nextLine());
            }

            p.executeUpdate();

            System.out.println("DATA INSERTED SUCCESSFULLY !");

        }catch (Exception e){
            System.out.println(e);
        }
    }
    void update(){
        int ch;
        System.out.println("-----------------------");
        System.out.println("|      1. RENAME       |");
        System.out.println("|      2. CHANGE ID    |");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/DATA","root","root");

            Statement s1 = c.createStatement();

            System.out.println("---------------------");
            System.out.print("| Enter Column Name : ");
            String col = sc.nextLine();
            System.out.println("---------------------");

            System.out.println("--------------");
            System.out.println("| 1. INT     |");
            System.out.println("| 2. VARCHAR |");
            System.out.println("--------------");

            System.out.println("--------------");
            System.out.print("| Enter Choice : ");ch = sc.nextInt(); sc.nextLine();
            System.out.println("--------------");
            String datatype = " ";

            if(ch == 1){
                datatype = "INT";
            } else if (ch == 2) {
                System.out.print("Enter Varchar Size : ");
                int s = sc.nextInt(); sc.nextLine();
                datatype = "VARCHAR (" + s + " )";
            } else {
                System.out.println("ERROR");
            }

            String sql = "ALTER TABLE student ADD COLUMN " + col + " " + datatype;

            s1.executeUpdate(sql);

            System.out.println("COLUMN UPDATED SUCCESSFULLY ");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    void view(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/DATA","root","root");

            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM student");
            ResultSetMetaData rsm = rs.getMetaData();
            int colc = rsm.getColumnCount();

            for(int i = 1; i <= colc; i++){
                System.out.printf("%-10s\t", rsm.getColumnName(i));
            }
            System.out.println();
            for (int i = 1; i <= colc; i++) {
                System.out.print("---------------");
            }
            System.out.println();
           while(rs.next()){
                for(int i = 1; i <= colc; i++) {
                    System.out.printf("%-10s\t", rs.getString(i));
                }
                System.out.println();
            }

        } catch (Exception e){
            System.out.println(e);
        }
    }
    void search(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/DATA","root","root");

            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("Select * from student");
            ResultSetMetaData rsm = rs.getMetaData();
            int colc = rsm.getColumnCount();

            PreparedStatement p = c.prepareStatement("select * from student where roll = ?");
            System.out.print("Enter Your Id Number : ");
            int id = sc.nextInt();
            p.setInt(1,id);
            rs = p.executeQuery();
            for(int i = 1; i <= colc; i++){
                System.out.print( rsm.getColumnName(i) + "\t");
            }
            System.out.println();
            while(rs.next()){
                for(int i = 1; i <= colc; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    void delete() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/DATA", "root", "root");

            PreparedStatement p =
                    c.prepareStatement("DELETE FROM student WHERE roll = ?");

            System.out.print("Enter Roll Number to delete: ");
            int roll = sc.nextInt();

            p.setInt(1, roll);

            int rows = p.executeUpdate();

            if (rows > 0) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("No record found with this roll number.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
abstract class Student2{
    abstract void view1();
}
class Studentimpl extends Student2 {
    Scanner sc = new Scanner(System.in);

    void view1(){
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/DATA","root","root");

           Statement s = c.createStatement();
           ResultSet rs = s.executeQuery("SELECT * FROM student");

           ResultSetMetaData rsm = rs.getMetaData();
           int colc = rsm.getColumnCount();

            for(int i = 1; i <= colc; i++){
                System.out.printf("%-10s\t", rsm.getColumnName(i));
            }
            System.out.println();
            for (int i = 1; i <= colc; i++) {
                System.out.print("---------------");
            }
            System.out.println();
            while(rs.next()){
                for(int i = 1; i <= colc; i++) {
                    System.out.printf("%-10s\t", rs.getString(i));
                }
                System.out.println();
            }

        } catch (Exception e){
            System.out.println(e);
        }
    }
}
public class DATABASE {
    public static void main() {
        Scanner sc = new Scanner(System.in);
        serviceS s = new ServiceSimpl();
        Student2 s1 = new Studentimpl();
        int ch, c, cho;

        System.out.println("||=======MENU=======||");
        System.out.println("||    1. TEACHER    ||");
        System.out.println("||    2. STUDENT    ||");
        System.out.println("||==================||");
        System.out.print(  "|| Enter choice :   ||"); c = sc.nextInt();
        System.out.println("||==================||");
        if(c == 1){
            do {
                System.out.println("======TEACHERS MENU=======");
                System.out.println("||       1. ADD         ||");
                System.out.println("||       2. UPDATE      ||");
                System.out.println("||       3. VIEW        ||");
                System.out.println("||       4. SEARCH      ||");
                System.out.println("==========================");

                System.out.println("=================== ");
                System.out.print("||Enter Your Choice : ||");ch = sc.nextInt();
                System.out.println("=================== ");

                switch (ch) {
                    case 1 -> s.add();
                    case 2 -> s.update();
                    case 3 -> s.view();
                    case 4 -> s.search();
                    case 5 -> s.delete();
                    case 6 -> {
                        System.out.println("EXITING...........");
                    }
                    default -> {
                        System.out.println("INVALID CHOICE !");
                    }
                }
            }while(ch != 6);
        } else if (c == 2) {
            do {
                System.out.println("||=====STUDENT=====||");
                System.out.println("||     1. VIEW     ||");
                System.out.println("||     2. EXIT     ||");
                System.out.println("||=================||");
                System.out.print("  || Enter Choice :  ||");cho = sc.nextInt();
                System.out.println("||=================||");

                switch (cho){
                    case 1 -> s1.view1();
                    case 2 -> {
                        System.out.println("Exiting..........");
                    }
                    default -> {
                        System.out.println("Invalid Choice !");
                    }
                }
            } while (cho != 2);
        }
    }
}