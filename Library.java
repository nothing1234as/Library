
import java.io.*;
import java.util.*;

//!!!!!!!!!!!!!!!!!!!!!!!make sure to add catch errors to file handling!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class Library implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String MEMBERS_FILE = "members.ser";
    private static final String LIBRARIANS_FILE = "librarians.ser";

    private static ArrayList<Member> members = new ArrayList<>();
    private static ArrayList<Librarian> librarians = new ArrayList<>();
    private static ArrayList <String> borrowed_booksM = new ArrayList<>();
    transient Scanner scanner = new Scanner(System.in);

   
    public void signUp(int op){
        
        
        //member sign up
        if(op == 1){
            
            //***notice: every time we want to save a new object first we should storage old ones****
            loadMembers();
            System.out.print("Enter your name(member): ");
            String name = scanner.nextLine();

            System.out.print("Enter your nationalCode(member): ");
            String nationalCode = scanner.nextLine();

            System.out.print("Enter your password(member): ");
            String paasword = scanner.nextLine();
            
            
            Member newMember = new Member(name, nationalCode, paasword);
            members.add(newMember);
            saveMembers(); //save to members file
             

            System.out.println("new member added successfully!\n");
        }//end of member sign up

        //librarian sign up 
        else if(op == 2){
            
            loadLibrarians();
            System.out.print("Enter your name(librarian): ");
            String name = scanner.nextLine();

            System.out.print("Enter your nationalCode(librarian): ");
            String nationalCode = scanner.nextLine();

            System.out.print("Enter your password(librarian): ");
            String paasword = scanner.nextLine();
            
            Librarian newLibrarian = new Librarian(name, nationalCode, paasword);
            librarians.add(newLibrarian);
            saveLibrarians(); //save to librarians file
             

            System.out.println("new librarian added successfully!\n");
        }//end of librarian sign up

    }
    
    public void signIn(int op){
            System.out.print("Enter your national code for sign in: ");
            String nationalCode = scanner.nextLine();

            System.out.print("Enter your password for sign in: ");
            String password = scanner.nextLine();

           //member sign in
            if(op == 1){
                loadMembers();
                for(Member member : members){
                    if(nationalCode.equals(member.getNationalCode())){
                        System.out.println("member was found!\n");
                        member.signIn(password);
                        return;
                    }
                }
                System.out.println("member with this national code was not found\n");
                
            }

            else if(op == 2){
                loadLibrarians();
                for(Librarian librarian : librarians){
                    if(nationalCode.equals(librarian.getNationalCode())){
                        System.out.println("librarian was found!\n");
                        librarian.signIn(password);
                        return;
                    }
                }
                System.out.println("librarian with this national code was not found\n ");
            }
    
    }

    public static void search_member(){
        loadMembers();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter member national code: ");
        System.out.print(">>>> ");
        String nationalCode = scanner.nextLine();

        for(Member member: members){
            if(member.getNationalCode().equals(nationalCode)){
                System.out.println(member.getAllInformation()+"\n");
                return;

            }
        }

    }

    public static void all_members(){
        System.out.println("All members: \n");
        loadMembers();

        for(Member member: members){
            System.out.println(member.getAllInformation()+"\n");
        }
    }

    public static void member_change_password(String nationalCode , String newPassword){
       loadMembers();

       for(Member member : members){
                if(member.getNationalCode().equals(nationalCode)){
                   member.setNewPassword(newPassword);
                   return;
                }

       }

        saveMembers();

    }

    public static void remove_member(String name){
        loadMembers();
        ArrayList <Member> temp = new ArrayList<>();

        for(Member member: members){
            if(!member.getName().equals(name)){
                temp.add(member);
            }
        }

        members = new ArrayList<>(temp);
        System.out.println("member: "+name+" deleted.");

        saveMembers();
    }

    public static void borrowing(String memberNationalCode , String bookName){
        loadMembers();
        loadBorrow();
        for(Member member: members){
            if(member.getNationalCode().equals(memberNationalCode)){
                borrowed_booksM.add(bookName+"|"+memberNationalCode);

            }
        }
        saveBorrow();

    }

    public static void returning(String memberNationalCode , String bookReturn){
        loadBorrow();

        for(String book : borrowed_booksM){
            String[] parts = book.split("\\|");

            if(parts[0].equals(bookReturn)){
                if(parts[1].equals(memberNationalCode)){
                    borrowed_booksM.remove(book);
                    System.out.println(bookReturn + " return finished");
                }
            }
        }

        saveBorrow();

    }
    //this is for show all books member borrowed
    public static void MemberBook(String nationalCode){

        loadBorrow();
        for(String book : borrowed_booksM){
            String[] parts = book.split("\\|");
            if(parts[1].equals(nationalCode)){
                BookHandling.showBook(parts[0]);

            }
        }
    }
    
   //File handling members
    private static void saveMembers(){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("members.ser"))){
            out.writeObject(members);

            System.out.println("members were saved successfully");
        }
        catch(IOException e){
            System.err.println("Error saving members: " + e.getMessage());
            
        }
    }

    private static void loadMembers(){

        File file = new File(MEMBERS_FILE);
        if (!file.exists()) {
             members = new ArrayList<>();
            return;
         }
        
        
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("members.ser"))){
            members = (ArrayList<Member>) in.readObject();
        }
        catch(IOException | ClassNotFoundException e){
            System.err.println("Error loading members: " + e.getMessage());
            members = new ArrayList<>();

        }
    }



    //File handling librarians
    
    private void saveLibrarians(){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("librarians.ser"))){
            out.writeObject(librarians);
        }
        catch(IOException e){
            System.err.println("Error saving librarians: " + e.getMessage());
        }
    }

    private void loadLibrarians(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("librarians.ser"))){
            librarians = (ArrayList<Librarian>) in.readObject();
        }
        catch(IOException | ClassNotFoundException e){
            
            librarians = new ArrayList<>();
        }
    }

   // borrow book file handling

    private static void loadBorrow(){
        File file = new File("memberBorrow.ser");
        if(!file.exists()){
            borrowed_booksM = new ArrayList<>();
            return;
        }

        try(ObjectInputStream in =new ObjectInputStream(new FileInputStream("memberBorrow.ser"))){
            borrowed_booksM = (ArrayList <String>) in.readObject();

        }
        catch(IOException | ClassNotFoundException e){
            System.out.println("error: "+ e.getMessage());
        }
    }


    private static void saveBorrow() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("memberBorrow.ser"))) {
            out.writeObject(borrowed_booksM);
        } catch (IOException e) {
            System.err.println("Error saving borrow book: " + e.getMessage());
        }


    }
    
}
