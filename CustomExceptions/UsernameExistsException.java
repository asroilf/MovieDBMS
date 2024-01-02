package CustomExceptions;

public class UsernameExistsException extends Exception {
    public UsernameExistsException(){
        super("Such username already exists!");
    }
}
