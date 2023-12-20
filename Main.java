import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        User u1 = new User("hPotter", "Harry Potter", "Harry's_account1");
        User u2 = new User("oldsport", "Jay Gatsby", "user123");
        try (FileOutputStream fos = new FileOutputStream(new File("DB/UserFile.txt"));
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(u1);
            oos.writeObject(u2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("DB/UserFile.txt"))) {
            User u3 = (User) ois.readObject();
            System.out.println(u3);
        } catch (Exception e) {
            System.out.println("Checkpoint ----------------------------> " + e.getMessage());
        }
        new GUI();
    }
}
