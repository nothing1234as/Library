import java.util.*;

public class LibraryManage {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    boolean run = true;
    System.out.println("welcome!\n");
    
    while(run){
        System.out.println("1.Librarian Sign up\n2.Librarian Sign in\n3.member Sign up\n4.member Sign in\n5.Exit");
        System.out.print(">>>> ");

        String choice = scanner.nextLine();
        
        Library library = new Library();
        switch(choice){

            case "1":
               library.signUp(2);
               break;
            
            case "2":
                library.signIn(2);
                break;
            
            case "3":
                library.signUp(1);
                break;
            
            case "4":
                library.signIn(1);
                break;

            case "5":
                run = false;
                System.out.println("Exiting...");
                break;
            default:
                System.err.println("invalid input!\n");

        }
    } // end of loop
    scanner.close();
    }

    
}
