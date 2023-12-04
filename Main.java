import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (BufferedOutputStream bos = new BufferedOutputStream((n)->System.out.println("Writing"))){
            User user = new User("oldsport", "Jay Gatsby", "user123", new int[]{9,4,1970});
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
