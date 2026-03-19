import java.sql.*;
import java.util.Scanner;

public class StudentMngSystem{
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con= DriverManager.getConnection(DBConfig.url,DBConfig.user,DBConfig.pass);
            Scanner sc =new Scanner(System.in );
            while(true){
                System.out.println("Enter: \n1.Application form \n2.Student info retrieval \n3.Update details \n4.Delete Student Record\n5.Any other number for closure");
                int choice=sc.nextInt();
                sc.nextLine();
                switch (choice){
                    case 1: new ApplicationForm(con,sc);
                        break;
                    case 2: new DataRetreival(con,sc);
                        break;
                    case 3: new UpdateDetails(con,sc);
                    break;
                    case 4: new DeleteDetails(con,sc);
                    break;
                    default:con.close();
                    return;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class ApplicationForm {
    ApplicationForm(Connection con,Scanner sc) throws SQLException{
        String query="INSERT INTO student(name,email,phone,course,age) VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement=con.prepareStatement(query);
        System.out.println("Enter name:");
        String name=sc.nextLine();
        System.out.println("Enter Email:");
        String email=sc.nextLine();
        System.out.println("Enter Phone Number:");
        String phone=sc.nextLine();
        System.out.println("Enter Course:");
        String course=sc.nextLine();
        System.out.println("Enter Age:");
        int age=sc.nextInt();
        sc.nextLine();
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,email);
        preparedStatement.setString(3,phone);
        preparedStatement.setString(4,course);
        preparedStatement.setInt(5,age);
        int row=preparedStatement.executeUpdate();
        if(row>0){
            System.out.println("Student Added and ID alloted\n\n");
        }
    }
}
class DataRetreival{
    DataRetreival(Connection con,Scanner sc) throws SQLException {
        String query = "SELECT * FROM student WHERE id=?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        System.out.println("Enter Roll Number:");
        int roll=sc.nextInt();
        preparedStatement.setInt(1,roll);
        ResultSet resSet = preparedStatement.executeQuery();
        if(resSet.next()){
            int id= resSet.getInt(1);
           String name= resSet.getString(2);
            String email= resSet.getString(3);
            String ph= resSet.getString(4);
            String course= resSet.getString(5);
            int age= resSet.getInt(6);

            System.out.println("Student Info:");
            System.out.println("Roll Number:"+id+"\nName: "+name+"\nEmail: "+email+"\nPhone Number: "+ph+"\nCourse: "+
                    course+"\nAge: "+age+"\n\n");
        }else{
            System.out.println("ID not Found");
        }
    }
}

class UpdateDetails {

    UpdateDetails(Connection con, Scanner sc) throws SQLException {
        String query = null;
        System.out.println("Enter Choice to Update: \n1.Name \n2.Email \n3.Phone number \n4.Course \n5.Age\nAny other number for closure of updation");
        int option=sc.nextInt();
        System.out.println("Enter correct student ID: ");
        int ID=sc.nextInt();
        sc.nextLine();
        switch (option){
            case 1:query="UPDATE student SET name = ? WHERE id = ?";
                System.out.println("Enter updated name: ");
            break;
            case 2:query="UPDATE student SET email = ? WHERE id = ?";
                System.out.println("Enter updated email: ");
            break;
            case 3:query="UPDATE student SET phone = ? WHERE id = ?";
                System.out.println("Enter updated phone: ");
            break;
            case 4:query="UPDATE student SET course = ? WHERE id = ?";
                System.out.println("Enter updated course: ");
            break;
            case 5:query="UPDATE student SET age = ? WHERE id = ?";
                System.out.println("Enter updated age: ");
                PreparedStatement ps= con.prepareStatement(query);
                int updatedData=sc.nextInt();
                ps.setInt(1,updatedData);
                ps.setInt(2,ID);
                int row=ps.executeUpdate();
                if(row>0){
                    System.out.println("Details updated Successfully");
                }else{
                    System.out.println("Details not updated");
                }
                return;
            default:
                return;
        }
        PreparedStatement ps= con.prepareStatement(query);
        String updatedData=sc.nextLine();
        ps.setString(1,updatedData);
        ps.setInt(2,ID);
        int row=ps.executeUpdate();
        if(row>0){
            System.out.println("Details updated Successfully");
        }else{
            System.out.println("Details not updated");
        }
    }
}

class DeleteDetails{
    DeleteDetails(Connection con,Scanner sc)throws SQLException{
        while(true){
            System.out.println("Choose Option:\n(1)Enter 1 for ID to DELETE:\n(2)Enter 2 to back to Menu");
            int opt=sc.nextInt();
            if(opt==2){
                return;
            }
            System.out.println("Enter ID to be DELETED: ");
            int id=sc.nextInt();
            String sql="DELETE FROM student WHERE id = ?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            int resSet=ps.executeUpdate();
            if(resSet>0){
                System.out.println("ID Deleted Successfully");
            }else{
                System.out.println("Please Enter Valid ID\n\n");
            }
        }
    }
}
