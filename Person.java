import java.io.Serializable;

public  abstract class Person implements Serializable {
    String password;
    String nationalCode;
    String name;



    
    abstract void signIn(String password);
    abstract String getNationalCode();
    abstract String getAllInformation();

}
