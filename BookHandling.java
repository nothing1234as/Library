import java.io.*;
import java.util.*;

public class BookHandling {

    private static ArrayList<Book> books = new ArrayList<>();
    private static ArrayList<Book> borrowed_booksN = new ArrayList<>(); //this is for books in borrowing


    public static void addBook(){
        Scanner scanner = new Scanner(System.in);

        loadBooks();
        System.out.print("name: ");
        String name = scanner.nextLine();

        System.out.print("topic: ");
        String topic = scanner.nextLine();

        System.out.print("writer: ");
        String writer = scanner.nextLine();

        System.out.print("publisher: ");
        String publisher = scanner.nextLine();

        System.out.print("year: ");
        String year = scanner.nextLine();

        System.out.print("ISBN: ");
        String ISBN = scanner.nextLine();

        Book newBook = new Book(name , topic , writer , publisher , year , ISBN);
        books.add(newBook);
        saveBooks(); //save new book

    }

   public static void searchBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("search by:1.name 2.topic 3.writer 4.publisher 5.year 6.ISBN");
        String op = scanner.nextLine();

        loadBooks();

        switch(op){

            case "1":
                System.out.print("Enter book name: ");
                String name = scanner.nextLine();

                System.out.println("books with this name: ");
                for (Book book : books){
                    if(book.getName().equals(name)){
                        System.out.println(book.getBookInfo()+"\n");
                    }
                }
                break;

            case "2":
                System.out.println("Enter book topic: ");
                String topic = scanner.nextLine();

                System.out.println("books with this topic:");
                for (Book book : books){
                    if(book.getTopic().equals(topic)){
                        System.out.println(book.getBookInfo()+"\n");
                    }
                }
                break;

            case "3":
                System.out.print("Enter book writer: ");
                String writer = scanner.nextLine();

                System.out.println("books with this writer: ");
                for(Book book : books){
                    if(book.getwriter().equals(writer)){
                        System.out.println(book.getBookInfo()+"\n");
                    }
                }
                break;

            case "4":
                System.out.print("Enter book publisher: ");
                String publisher = scanner.nextLine();

                System.out.println("books with this publisher: ");
                for(Book book : books){
                    if(book.getPublisher().equals(publisher)){
                        System.out.println(book.getBookInfo()+"\n");
                    }
                }
                break;

            case "5":
                System.out.print("Enter book year: ");
                String year = scanner.nextLine();

                System.out.println("books with this year: ");
                for(Book book : books){
                    if(book.getYear().equals(year)){
                        System.out.println(book.getBookInfo()+"\n");
                    }
                }
                break;

            case "6":
                System.out.print("Enter book ISBN: ");
                String ISBN = scanner.nextLine();

                System.out.println("books with this ISBN: ");
                for(Book book : books){
                    if(book.getISBN().equals(ISBN)){
                        System.out.println(book.getBookInfo()+"\n");
                    }
                }
                break;

            default:
                System.out.println("Enter a valid parameter for search\n");
        }

   }

   public static void allBooks(){
        loadBooks();
        System.out.println("all books in library: ");
        for (Book book : books){
            System.out.println(book.getBookInfo());
        }

        loadBorrow();
       System.out.println("all books in borrowing: ");
        for(Book borrow : borrowed_booksN){
            System.out.println(borrow.getBookInfo());
        }
   }

   public static void editBook(){
        loadBooks();
        Scanner scanner = new Scanner(System.in);

        System.out.print("enter name of book for edit: ");
        String name = scanner.nextLine();

        for(Book book: books){
            if(book.getName().equals(name)){
                System.out.println(book.getBookInfo());
                System.out.println("which topic you want to edit: 1.name 2.topic 3.writer 4.publisher 5.year 6.ISBN");
                System.out.print(">>> ");
                String edit = scanner.nextLine();

                switch(edit){

                    case "1":
                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();
                        book.set_newName(newName);
                        break;

                    case "2":
                        System.out.print("enter new topic: ");
                        String newTopic = scanner.nextLine();
                        book.set_newTopic(newTopic);
                        break;

                    case "3":
                        System.out.print("enter new writer: ");
                        String newWriter = scanner.nextLine();
                        book.set_newWriter(newWriter);
                        break;

                    case "4":
                        System.out.print("enter new publisher: ");
                        String newPublisher = scanner.nextLine();
                        book.set_newPub(newPublisher);
                        break;

                    case "5":
                        System.out.print("enter new year: ");
                        String newYear = scanner.nextLine();
                        book.set_newYear(newYear);
                        break;

                    case "6":
                        System.out.print("enter new ISBN: ");
                        String newISBN = scanner.nextLine();
                        book.set_newISBN(newISBN);
                        break;

                    default:
                        System.out.println("enter a valid input for edit parameter");

                }
                System.out.println("Edit was successfull.new information: ");
                System.out.println(book.getBookInfo());
                saveBooks();
                return;
            }
        }
        System.out.println("book not found.\n");

   }

   public static void removeBook(String name){
        loadBooks();
        ArrayList<Book> temp = new ArrayList<>();
        for (Book book : books){
            if(!book.getName().equals(name)){
                temp.add(book);
            }
        }
        books = new ArrayList<>(temp);

       System.out.println(name+" deleted!");
        saveBooks();

   }

   public static void borrowBook(String Bookname , String memberNationalCode){
       loadBooks();
       loadBorrow();
       ArrayList <Book> mainTemp = new ArrayList<>();
      // ArrayList <Book> borrowTemp = new ArrayList<>();

       int flag = 0;


       for(Book book : books){

           if(!book.getName().equals(Bookname)){
               mainTemp.add(book);

           }
           else if(book.getName().equals(Bookname)){
               //borrowTemp.add(book);
               borrowed_booksN.add(book);
               Library.borrowing(memberNationalCode , Bookname);
               flag =1;

           }

       }


       if(flag == 1){

           books = new ArrayList<>(mainTemp);
         //  borrowed_booksN = new ArrayList<>(borrowTemp);

           saveBooks();
           saveBorrow();


       }
       else if(flag == 0){
           System.out.println("there is no book with this name");
       }



   }

   public static void retunBook(String bookReturn , String memberNationalCode){
        //return book to free books from borrowing books
       loadBooks();
       loadBorrow();

       ArrayList <Book> borrowTemp = new ArrayList<>();

       int flag = 0;
       for(Book book : borrowed_booksN){
           if(!book.getName().equals(bookReturn)){
               borrowTemp.add(book);

           }
           else if (book.getName().equals(bookReturn)) {
               books.add(book);
               Library.returning(memberNationalCode , bookReturn);
               flag = 1;
           }
       }

       if(flag == 1){
           borrowed_booksN = new ArrayList<>(borrowTemp);

           saveBooks();
           saveBorrow();
       }
       else if( flag == 0){
           System.out.println("there is no book in borrowing list with this name.");
       }


   }

   public static void showBook(String bookName){
        loadBorrow();
        for(Book book : borrowed_booksN){
            if(book.getName().equals(bookName)){
                System.out.println(book.getBookInfo()+"\n");
                return;
            }
        }
   }


    //book file handling
    private static void loadBooks() {

        //check before loadin'
        File file = new File("books.ser");
        if(!file.exists()){
            books = new ArrayList<>();
            return;
        }


        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("books.ser"))) {
            books = (ArrayList<Book>) in.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("error: "+ e.getMessage());
            //books = new ArrayList<>();
        }
    }



    private static void saveBooks(){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("books.ser"))){
            out.writeObject(books);
            System.out.println("books saved successfully.\n");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    // borrow book file handling

    private static void loadBorrow(){
        //check before loadin'
        File file = new File("nationalBorrow.ser");
        if(!file.exists()){
            borrowed_booksN = new ArrayList<>();
            return;
        }


        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("nationalBorrow.ser"))){
            borrowed_booksN = (ArrayList <Book>) in.readObject();
        }
        catch(IOException | ClassNotFoundException e){
            System.out.println("error: "+ e.getMessage());
        }
    }


    private static void saveBorrow(){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("nationalBorrow.ser"))){
            out.writeObject(borrowed_booksN);
            System.out.println("borrowed books saving was succesfull.\n");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }


}
