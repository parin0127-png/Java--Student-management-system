import java.util.*;
class Stdmanage{
    private int id;
    private String name;
    private int age;

    Stdmanage(int x, String n, int a){
        this.id = x;
        this.name = n;
        this.age = a;
    }
    int getID(){
        return id;
    }
    String getName(){
        return name;
    }
    int getAge(){
        return age;
    }
}
abstract class Studentservice2{
    abstract void addStudent();
    abstract void viewStudent();
    abstract void searchStudent();
    abstract void deleteStudent();
}
class Studentserviceimpls extends Studentservice2{
    Scanner sc = new Scanner(System.in);
    ArrayList<Stdmanage> student = new ArrayList<>();
    LinkedList<String> activitylog  = new LinkedList<>();

    void addStudent(){
        System.out.print("Enter Name of Student : ");
        String name = sc.nextLine();

        System.out.print("Enter The ID : ");
        int id = sc.nextInt(); sc.nextLine();

        System.out.print("Enter The Age : ");
        int age = sc.nextInt();sc.nextLine();

        Stdmanage s = new Stdmanage(id, name, age);
        student.add(s);
    }
    void viewStudent(){
        if(student.isEmpty()){
            System.out.println("NO ACTIVITY YET !");
            return;
        }
        for(Stdmanage s : student){
            System.out.println("Name : " + s.getName() + " ID : " + s.getID() + " Age : " + s.getAge());
        }
    }
    void searchStudent(){
        System.out.println("Enter ID Number : ");
        int sid = sc.nextInt();

        boolean found = false;

        for(Stdmanage s : student){
            if(s.getID() == sid){
                System.out.println("ID : " + s.getID() + " Name : " + s.getName() + " Age : " + s.getAge());
                activitylog.add("Searched ID : " + sid);
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Student Not Found !");
        }
    }
    void deleteStudent(){
        System.out.println("Enter ID to Remove : ");
        int deleteId = sc.nextInt();sc.nextLine();

        Iterator<Stdmanage> it = student.iterator();
        boolean remove = false;

        while(it.hasNext()){
            Stdmanage s = it.next();

            if(s.getID() == deleteId){
                it.remove();
                activitylog.add("Deleted Student ID : " + deleteId);
                remove = true;
                System.out.println("Student Deleted SuccessFully ! ");
                break;
            }
        }
        if(!remove){
            System.out.println("Student Not Found ! ");
        }
    }
}
public class management {
    public static void main(String[] args) {
        Scanner s2 = new Scanner(System.in);
        Studentservice2 s1 = new Studentserviceimpls();
        do{
            System.out.println("1. Add Student ");
            System.out.println("2. View Student ");
            System.out.println("3. Search Student ");
            System.out.println("4. Remove Student ");
            System.out.println("5. Exit ");

            System.out.print("Enter Your Choice : ");
            int ch = s2.nextInt();
            if(ch == 1){
                s1.addStudent();
            }else if(ch == 2){
                s1.viewStudent();
            }else if(ch == 3){
                s1.searchStudent();
            }else if(ch == 4){
                s1.deleteStudent();
            }else if(ch == 5){
                break;
            }else{
                System.out.println("Error !");
            }
        }while(true);
    }
}