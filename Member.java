import java.io.*;
import java.util.*;

public class Member extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    //transient private Scanner scanner = new Scanner(System.in); //important: because we want to serialize we separate Scanner object with transient

    private String password;
    private String nationalCode;
    private String name;
    
    
    public Member(String name , String nationalCode , String password){
        this.name = name;
        this.nationalCode = nationalCode;
        this.password = password;
    }
  
    
    @Override
    void signIn(String password) {
        if(password.equals(this.password)){
            System.out.println("member sign in was successfully!\n");
            menu();
            return;
        }
        System.out.println("member password was not correct!\n");
    }



    //getter methods
    String getNationalCode(){
        return this.nationalCode;
    }

    String getName() { return this.name; }

    String getAllInformation(){
        return "name: "+this.name+"| "+"nationalcode: "+this.nationalCode;
    }


    // setter methods
    public void setNewPassword(String newPassword){
        this.password = newPassword;
    }

    //menu
    private void menu(){
            Scanner scanner = new Scanner(System.in);
            System.out.println("welcome to your account!");

            boolean run = true;
            while(run){
                    System.out.println("1.your information\n2.changing password\n3.book borrowing\n4.return book\n5.search book\n6.all books in borrowing\n7.Exit\n");
                    System.out.print(">>>> ");
                    String op = scanner.nextLine();
                  
                   
                   switch(op){

                           case "1":
                                System.out.println(getAllInformation());
                                break;

                           case "2":
                                System.out.println("Enter your new password(member): ");

                                String newPass = scanner.nextLine();
                                //for change the pass we should change it in library (member object is in library)
                                Library.member_change_password(this.nationalCode , newPass);

                                System.out.println("password has changed.\n");
                                break;

                           case "3":
                               System.out.println("enter name of book for borrowing: ");
                               String bookName = scanner.nextLine();

                               BookHandling.borrowBook(bookName , this.nationalCode);
                               break;

                           case "4":
                                System.out.println("wich book do you want to return? " );
                                String bookReturn = scanner.nextLine();

                                BookHandling.retunBook(bookReturn , this.nationalCode);
                               break;

                          case "5":
                              BookHandling.searchBook();
                              break;

                          case "6":
                              Library.MemberBook(this.nationalCode);
                              break;

                          case "7":
                             run = false;
                             System.out.println("Exiting from Member menu.\n");
                             break;

                       default:
                           System.out.println("Enter an invalid input");
                   }
            }
    }

}
