import java.io.*;
import java.util.Scanner;


public class Librarian extends Person implements Serializable{
    private static final long serialVersionUID = 1L;
    //transient private Scanner scanner = new Scanner(System.in); //because we want to serialize we separate Scanner object with transient
    private String name;
    private String nationalCode;
    private String password;

    public Librarian(String name , String nationalCode, String password){
        this.name = name;
        this.nationalCode = nationalCode;
        this.password = password;
    }


    @Override
    void signIn(String password) {
        if(password.equals(this.password)){
            System.out.println("librarian sign in was successfully!\n");
            menu();
            return;
        }
        System.out.println("librarian password was not correct!");
    }

    //getter methods 
    @Override
    String getNationalCode(){
        return this.nationalCode;
    }
    @Override
    String getAllInformation(){
        return  "name: "+this.name+"| "+"nationalcode: "+this.nationalCode;
    }


    private void menu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome back librarian!\n");

        boolean run = true;
        while(run){
            System.out.println("1.search member\n2.all members\n3.remove member\n4.add book\n5.search book(by different parameters)\n6.all books\n7.edit book\n8.remove book\n9.Exit");
            System.out.print(">>>> ");
            String op = scanner.nextLine();

            switch(op){

                case "1":
                    Library.search_member();
                    break;
                case "2":
                    Library.all_members();
                    break;

                case "3":
                    System.out.print("Enter member name for delete: ");
                    String memberName = scanner.nextLine();

                    Library.remove_member(memberName);
                    break;

                case "4":
                    BookHandling.addBook();
                    break;

                case "5":
                    BookHandling.searchBook();
                    break;

                case "6":
                    BookHandling.allBooks();
                    break;

                case "7":
                    BookHandling.editBook();
                    break;

                case "8":
                    System.out.print("enter book name for delete: ");
                    String bookName = scanner.nextLine();

                    BookHandling.removeBook(bookName);
                    break;

                case "9":
                    run = false;
                    System.out.println("Exit from librarian menu.\n");
                    break;

                default:
                    System.out.println("Enter a valid input");

            }
        }
    }

}

