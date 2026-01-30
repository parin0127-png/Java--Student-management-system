package db;
import javax.crypto.spec.PSource;
import java.sql.*;
import java.util.*;
class student{
    private String name;
    private int rollno;

    student(String n ,int r){
        this.name = n;
        this.rollno = r;
    }
    String getName(){
        return name;
    }
    int getRollno(){
        return  rollno;
    }
}
abstract class serviceStd{
    abstract void add1();
    abstract void view();
   abstract void search();
}
class serviceStdimpl extends serviceStd{
    Scanner sc = new Scanner(System.in);
    void add1() {
        try {
            System.out.print("Enter Number of Students You Want to ADD : ");
            int n = sc.nextInt();sc.nextLine();
            for (int i = 1; i <= n; i++) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/stud", "root", "root");
                PreparedStatement p = c.prepareStatement("insert into stude values (?,?)");
                System.out.print("Enter Name : ");
                String na = sc.nextLine();
                p.setString(1, na);
                System.out.print("Enter Roll Number : ");
                int r = sc.nextInt();sc.nextLine();
                p.setInt(2, r);
                int in = p.executeUpdate();

                if (in > 0) {
                    System.out.println("s");
                } else {
                    System.out.println("f");
                    c.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    void view() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/stud", "root", "root");

            PreparedStatement p = c.prepareStatement("SELECT * from stude");
            ResultSet rs = p.executeQuery();

            if(rs.next()){
                System.out.println(" SUCCESSFUL :  " + "\n" + rs.getString(1) + "\t" + rs.getInt(2));
            } else {
                System.out.println("F");
                c.close();
            }
            while(rs.next()){
                System.out.println(rs.getString(1) + "\t" + rs.getInt(2));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    void search(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/stud", "root", "root");

            PreparedStatement p = c.prepareStatement("SELECT * from stude where roll = ?");
            System.out.print("Enter Your Roll Number To find your Details : ");
            int r = sc.nextInt();
            p.setInt(1,r);
            ResultSet rs = p.executeQuery();

            if(rs.next()){
                System.out.println("Student Found : " + rs.getString(1) + "\t" + rs.getInt(2));
            } else {
                System.out.println("Student not Found !");
                c.close();
            }
            while(rs.next()){
                System.out.println(rs.getString(1) + "\t" + rs.getInt(2));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
abstract class teacherservice{
    abstract void Add();
    abstract void Update();
    abstract void delete();
    abstract void search();
    abstract void view();
}
class teacherimpl extends teacherservice{
    Scanner sc = new Scanner(System.in);
    int ch;
    void Add() {
        try {
            System.out.println("To Whom do You want to add ! ");
            System.out.println("1. Teacher ");
            System.out.println("2. Student ");
            System.out.print("Enter Your Choice : ");
            ch = sc.nextInt();sc.nextLine();
            if(ch == 1) {
                System.out.print("Enter Number of Teachers You Want to ADD : ");
                int n = sc.nextInt();sc.nextLine();
                sc.nextLine();
                for (int i = 1; i <= n; i++) {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/teach", "root", "root");
                    PreparedStatement p = c.prepareStatement("insert into teacher values (?,?,?)");
                    System.out.print("Enter Name  : ");
                    String na = sc.nextLine();
                    p.setString(1, na);
                    sc.nextLine();
                    System.out.print("Enter ID Number : ");
                    int r = sc.nextInt();
                    sc.nextLine();
                    p.setInt(2, r);
                    System.out.print("Enter Email ID : ");
                    String emid = sc.nextLine();
                    p.setString(3, emid);
                    int in = p.executeUpdate();

                    if (in > 0) {
                        System.out.println("s");
                    } else {
                        System.out.println("f");
                        c.close();
                    }
                }
            } else if (ch == 2) {
                System.out.print("Enter Number of Students You Want to ADD : ");
                int n = sc.nextInt();sc.nextLine();
                sc.nextLine();
                for (int i = 1; i <= n; i++) {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/stud", "root", "root");
                    PreparedStatement p = c.prepareStatement("insert into stude values (?,?)");
                    System.out.print("Enter Name : ");
                    String na = sc.nextLine();
                    p.setString(1, na);
                    sc.nextLine();
                    System.out.print("Enter Roll Number : ");
                    int r = sc.nextInt(); sc.nextLine();
                    p.setInt(2, r);
                    int in = p.executeUpdate();

                    if (in > 0) {
                        System.out.println("s");
                    } else {
                        System.out.println("f");
                        c.close();
                    }
                }
            } else {
                System.out.println("Invalid Choice !");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    void Update(){
        int cho, ch ,co;
        try{
            System.out.println("To whom do You want to Update : ");
            System.out.println("| 1. Teachers |");
            System.out.println("| 2. Students |");
            System.out.print("Enter Your choice : ");
            cho = sc.nextInt();sc.nextLine();

            if(cho == 1){
                System.out.println("| 1. Rename         |");
                System.out.println("| 2. Update Email   |");

                System.out.print("Enter Your Choice : ");
                ch = sc.nextInt();sc.nextLine();

                 if (ch == 1) {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/teach", "root", "root");

                    PreparedStatement p = c.prepareStatement("update teacher set name = ? where id = ?");
                    System.out.print("Enter New Name : ");
                    String s1 = sc.nextLine();
                    p.setString(1,s1);
                    System.out.print("Enter ID : ");
                    int s2 = sc.nextInt();sc.nextLine();
                    p.setInt(2,s2);
                    int i = p.executeUpdate();

                    if(i > 0){
                        System.out.println("Successful !");
                    } else {
                        System.out.println("Failed !");
                        c.close();
                    }
                } else if (ch == 2) {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/teach", "root", "root");

                    PreparedStatement p = c.prepareStatement("update teacher set Email = ? where id = ?");
                    System.out.print("Enter New Name : ");
                    String e1 = sc.nextLine();
                    p.setString(1,e1);
                    System.out.print("Enter ID : ");
                    int e2 = sc.nextInt();sc.nextLine();
                    p.setInt(2,e2);
                    int i = p.executeUpdate();

                    if(i > 0){
                        System.out.println("Successful : ");
                    } else {
                        System.out.println("Failed !");
                        c.close();
                    }

                }else{
                    System.out.println("Invalid Choice !");
                }
            } else if (cho == 2) {
                System.out.println("| 1. Rename  |");

                System.out.print("Enter Your Choice : ");
                ch = sc.nextInt();sc.nextLine();

                 if (ch == 1) {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/stud", "root", "root");

                    PreparedStatement p = c.prepareStatement("update stude set name = ? where roll = ?");
                    System.out.print("Enter New Name : ");
                    String s1 = sc.nextLine();
                    p.setString(1,s1);
                    System.out.print("Enter ID : ");
                    int s2 = sc.nextInt();sc.nextLine();
                    p.setInt(2,s2);
                    int i = p.executeUpdate();

                    if(i > 0){
                        System.out.println("Successful !");
                    } else {
                        System.out.println("Failed !");
                        c.close();
                    }

                }else{
                    System.out.println("Invalid Choice !");
                }
            }else {
                System.out.println("Invalid Choice !");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    void view(){
        int ch1;
        try{
            System.out.println("| 1. Teacher |");
            System.out.println("| 2. Student |");

            System.out.println("Enter Your Choice : ");
            ch1 = sc.nextInt();sc.nextLine();

            if(ch1 == 1){
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/teach", "root", "root");

                    PreparedStatement p = c.prepareStatement("SELECT * from teacher");
                    ResultSet rs = p.executeQuery();

                    if(rs.next()){
                        System.out.println("Found : " + "\n" + rs.getString(1) + "\t" + rs.getInt(2) + "\t" + rs.getString(3));
                    } else {
                        System.out.println("F");
                        c.close();
                    }
                    while(rs.next()){
                        System.out.println("Found : " + rs.getString(1) + "\t" + rs.getInt(2) + "\t" + rs.getString(3));
                    }
                }catch (Exception e){
                    System.out.println(e);
                }
            } else if (ch1 == 2){
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/stud", "root", "root");

                    PreparedStatement p = c.prepareStatement("SELECT * from stude");
                    ResultSet rs = p.executeQuery();

                    if(rs.next()){
                        System.out.println(" SUCCESSFUL :  " + "\n" + rs.getString(1) + "\t" + rs.getInt(2));
                    } else {
                        System.out.println("F");
                        c.close();
                    }
                    while(rs.next()){
                        System.out.println(rs.getString(1) + "\t" + rs.getInt(2));
                    }
                }catch (Exception e){
                    System.out.println(e);
                }
            } else {
                System.out.println("Invalid Choice !");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    void search(){
        int ch2;
        try{
            System.out.println("| 1. Teacher |");
            System.out.println("| 2. Student |");

            System.out.print("Enter Your Choice : ");
            ch2 = sc.nextInt();sc.nextLine();
            if(ch2 == 1){
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/teach", "root", "root");

                    PreparedStatement p = c.prepareStatement("SELECT * from teacher where id = ?");
                    System.out.print("Enter ID To find Details : ");
                    int r = sc.nextInt();
                    p.setInt(1,r);
                    ResultSet rs = p.executeQuery();

                    if(rs.next()){
                        System.out.println("Found : " + rs.getString(1) + "\t" + rs.getInt(2) + "\t" + rs.getString(3) );
                    } else {
                        System.out.println(" Not Found !");
                        c.close();
                    }
                    while(rs.next()){
                        System.out.println(rs.getString(1) + "\t" + rs.getInt(2) + "\t" + rs.getString(3) );
                    }
                }catch (Exception e){
                    System.out.println(e);
                }
            } else if (ch2 == 2) {
                try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/stud", "root", "root");

                PreparedStatement p = c.prepareStatement("SELECT * from stude where roll = ?");
                System.out.print("Enter Your Roll Number To find your Details : ");
                int r = sc.nextInt();
                p.setInt(1,r);
                ResultSet rs = p.executeQuery();

                if(rs.next()){
                    System.out.println("Student Found : " + rs.getString(1) + "\t" + rs.getInt(2));
                } else {
                    System.out.println("Student not Found !");
                    c.close();
                }
                while(rs.next()){
                    System.out.println(rs.getString(1) + "\t" + rs.getInt(2));
                }
            }catch (Exception e){
                System.out.println(e);
            }
            } else {
                System.out.println("Invalid choice !");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    void delete(){
        System.out.println("THE DATA IS GOING TO DELETE FROM STUDENTS DB !");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/stud", "root", "root");
            PreparedStatement p = c.prepareStatement("delete from stude where roll = ?");
            System.out.print("Enter Roll Number To Delete Student Data : ");
            int s = sc.nextInt();sc.nextLine();
            p.setInt(1,s);
            int i = p.executeUpdate();

            if(i > 0){
                System.out.println("SuccessFully Deleted Student Data ");
            } else {
                System.out.println("Failed To Deleted Student Data ");
                c.close();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
public class fate {
    static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        serviceStd s = new serviceStdimpl();
        teacherservice t = new teacherimpl();
        int cho, ch, c1;
        System.out.println("1. Teacher ");
        System.out.println("2. Student ");
        System.out.print("Enter Your Identity : ");
        cho = sc.nextInt();
        sc.nextLine();

        if (cho == 1)
        {
          Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/teach", "root", "root");
            PreparedStatement p = c.prepareStatement("select * from teacher where email = ?");
            System.out.print("Enter Your Email : ");
            String emid = sc.nextLine();
            p.setString(1,emid);

            ResultSet rs = p.executeQuery();
            if(rs.next())
            {
            do {
                System.out.println("|=====Teachers Menu=====|");
                System.out.println("|     1. ADD            |");
                System.out.println("|     2. Update         | ");
                System.out.println("|     3. View           |");
                System.out.println("|     4. Search         |");
                System.out.println("|     5. Delete         |");
                System.out.println("|     6. Exit           |");
                System.out.println("|=======================|");

                System.out.print("Enter Choice : ");
                c1 = sc.nextInt(); sc.nextLine();

                switch (c1) {
                    case 1 -> t.Add();
                    case 2 -> t.Update();
                    case 3 -> t.view();
                    case 4 -> t.search();
                    case 5 -> t.delete();
                    case 6 -> {
                        System.out.println("Exiting......");
                    }
                    default -> {
                        System.out.println("Invalid choice ");
                    }
                }
            } while (c1 != 6);
        } else {
                System.out.println("f");
            }
        }else if (cho == 2){
                do {
                    System.out.println("|======Student Management=====|");
                    System.out.println("|          1. Add             |");
                    System.out.println("|          2. View            |");
                    System.out.println("|          3. Search          |");
                    System.out.println("|          4. Exit            |");
                    System.out.println("|=============================|");
                    System.out.print("Enter your Choice : ");
                    ch = sc.nextInt();

                    switch (ch) {
                        case 1 -> s.add1();
                        case 2 -> s.view();
                        case 3 -> s.search();
                        case 4 -> {
                            System.out.println("Exiting........");
                        }
                        default -> {
                            System.out.println("Invalid choice !");
                        }
                    }
                }while (ch != 4) ;
            }else{
                System.out.println("Suck my Ballz !");
            }
    }
}
