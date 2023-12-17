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

public class Main {
    public static void main(String[] args) {
        User u1 = new User("hPotter", "Harry Potter", "Harry's_account1", new int[] { 9, 9, 1999 });
        User u2 = new User("oldsport", "Jay Gatsby", "user123", new int[] { 9, 4, 1970 });
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

        try {
            System.out.println(User.login("hPotter", "Harry's_account1"));

        } catch (Exception e) {
            System.out.println("User not found");
        }

        try {
            User.login("oldsport", "user12");
        } catch (Exception e) {
            System.out.println("User not found!");
        }
        Movie object = new Movie("Attack on Titan", "SomeRanomdude", 2016, 1);
        Movie object2 = new Movie("Star Wars", "SomeRanomdude", 2015, 1);
        Movie object3 = new Movie("Godfather", "SomeRanomdude", 1990, 1);
        Movie object4 = new Movie("Attack on Titan", "SomeRanomdude", 2016, 1);
        // MovieDatabase.addMovie(object);
        // MovieDatabase.addMovie(object2);
        // MovieDatabase.addMovie(object3);
        // MovieDatabase.addMovie(object4);
        // MovieDatabase.addMovie(object2);
        ArrayList<Movie> m = MovieDatabase.allMovies();
        Iterator<Movie> iterator = m.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().getTitle());
        }

    }
}
