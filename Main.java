import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        User u1 = new User("hPotter", "Harry Potter", "Harry's_account1", new int[]{9,9,1999});
        User u2 = new User("oldsport", "Jay Gatsby", "user123", new int[]{9,4,1970});
        try (FileOutputStream fos = new FileOutputStream(new File("DB/UserFile.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(u1);
            oos.writeObject(u2);
        } catch (Exception e) {
            // TODO: handle exception
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("DB/UserFile.txt"))){
            User u3 = (User)ois.readObject();
            System.out.println(u3);
        }catch(Exception e){
            System.out.println("CHeckpoint ----------------------------> " + e.getMessage());
        }

        try {
            System.out.println(User.login("hPotter", "Harry's_account1"));
            
        } catch (Exception e) {
            System.out.println("User not found");
        }

        try{
            User.login("oldsport", "user12");
        }
        catch(ValidationException e){
            System.out.println("User not found!");
        }
    }
}
