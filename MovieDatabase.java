import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class MovieDatabase {
    private ArrayList<Movie> movies;

    public MovieDatabase() {
        movies = new ArrayList<>();
    }

    public static void addMovie(Movie movie) {
        boolean check=true;
        ArrayList<Movie> al = allMovies();

        Iterator<Movie> iter = al.iterator();
        for(Movie x: iter){
            if(x.getTitle().equals(movie.getTitle())){
                check=false;
            }
        }

        if(check){
            try (FileOutputStream fos = new FileOutputStream(new File("DB/Movie.txt"));
                    ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(movie);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void remove(String movie) {
        // Open the file, then we read it.
        ArrayList<Movie> al = new ArrayList<>();
        try (FileInputStream fos = new FileInputStream(new File("DB/Movie.txt"));
                ObjectInputStream ois = new ObjectInputStream(fos)) {
            Movie movie4;
            while ((movie4 = (Movie) ois.readObject()) != null) {
                al.add(movie4);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (ObjectOutputStream fos = new ObjectOutputStream(new FileOutputStream("DB/Movie.txt"))) {
            for (int i = 0; i < al.size(); i++) {
                Movie movie5 = al.get(i);
                if (movie5.getTitle().equals(movie)) {
                    continue;
                } else {
                    fos.writeObject(movie5);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Movie retrieveMovie(String title) {
        ArrayList<Movie> al = allMovies();
        Iterator<Movie> iter = al.iterator();
        for(Movie x: iter){
            if(x.getTitle().equals(title)){
                return x;
            }
        }
        return new Movie();
    }

    public static ArrayList<Movie> allMovies() {
        ArrayList<Movie> al = new ArrayList<>();
        try(FileInputStream fis = new FileInputStream("DB/Movie.txt");
            ObjectInputStream ois = new ObjectInputStream(fis)){
            Movie temp;
            while((temp = (Movie)ois.readObject()) != null){
                al.add(temp);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return al;
    }

}
