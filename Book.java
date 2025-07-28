import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String topic;
    private String writer;
    private String publisher;
    private String year;
    private String ISBN;

   public Book(String name , String topic, String writer , String publisher , String year , String ISBN){
          this.name = name;
          this.topic = topic;
          this.writer = writer;
          this.publisher = publisher;
          this.year = year;
          this.ISBN = ISBN;
   }
  //for isbn : 978-country code(3 digits)-publisher code(3 digits)-book code(3 digits)-1



   //getter methods
   public String getBookInfo(){
       return "name: "+this.name+" | topic: "+this.topic+" | writer: "+this.writer+" | publisher: "+this.publisher+" | year: "+this.year+" | ISBN: "+this.ISBN;

   }

    public String getName(){
       return this.name;
    }

    public String getTopic(){
       return this.topic;
    }

    public String getwriter(){
       return this.writer;
    }

    public String getPublisher(){
        return this.publisher;
    }

    public String getYear(){
        return this.year;
    }

    public String getISBN(){
        return this.ISBN;
    }

    //setter methods

    public void set_newName(String newName){
       this.name = newName;
    }

    public void set_newTopic(String newTopic){
        this.topic = newTopic;
    }

    public void set_newWriter(String newWriter){
        this.writer = newWriter;
    }

    public void set_newPub(String newPub){
       this.publisher = newPub;
    }

    public void set_newYear(String newYear){
       this.year = newYear;
    }

    public void set_newISBN(String newISBN){
       this.ISBN = newISBN;
    }



}


