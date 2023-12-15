import java.io.*;
import java.util.ArrayList;

public class MovieDatabase {
    private ArrayList<Movie> movies;

    public MovieDatabase() {
        movies = new ArrayList<>();
    }

    public static void addMovie(Movie movie) {
        try (FileOutputStream fos = new FileOutputStream(new File("DB/Movie.txt"));
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(movie);
        } catch (Exception e) {
            e.printStackTrace();
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
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("DB/Movie.txt"))) {
            Movie m;

            while ((m = (Movie) ois.readObject()) != null) {
                if (m.getTitle().equals(title)) {
                    return m;
                }
            }
        } catch (Exception e) {
            System.out.println("Movie doesn't exist");
        }

        return new Movie();

    }

    public void removeMovie(Movie movie) {
        if (movies.contains(movie)) {
            movies.remove(movie);
            System.out.println(movie.getTitle());
        } else {
            System.out.println("Movie doesn't exist.");
        }
    }

    public Movie getMovieTitle(String title) {
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                return movie;
            }
        }
        return null;
    }

    public void AllMovies() {
        System.out.println("List of Movies in the Database:");
        for (Movie movie : movies) {
            System.out.println(movie);
            System.out.println("----------------------");
        }
    }

}
