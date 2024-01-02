import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * The MovieDatabase class provides methods for managing movie data within the Local Movie Database.
 * It manages operations such as adding, retrieving, and removing movies, as well as user-specific movie lists.
 *
 * @author Farid, Melek, Asliddin
 * @version 4.0
 */
public class MovieDatabase {

    /**
     * A TreeSet that stores unique director names from the movies in the database.
     */

    static TreeSet<String> directors = new TreeSet<>(); 


    /**
     * Removes a movie from the database based on its title.
     *
     * @param movieTitle The title of the movie to be removed.
     */


    public static void removeMovie(String movieTitle) {
        ArrayList<Movie> al = allMovies();
        Iterator<Movie> iter = al.iterator();

        while (iter.hasNext()) {
            Movie movie = iter.next();
            if (movie.getTitle().equals(movieTitle)) {
                iter.remove();
                break; // Exit the loop after removing the movie
            }
        }

        try (FileWriter fw = new FileWriter("DB/Movie.csv");
                BufferedWriter bw = new BufferedWriter(fw)) {
            // Write the CSV header
            bw.write("Title, Director, Year, Runtime\n");

            // Write the remaining movies
            for (Movie movie : al) {
                String temp = String.format("%s, %s, %d, %d\n", movie.getTitle(), movie.getDirector(),
                        movie.getReleasedYear(), movie.getRunningTime());
                bw.write(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new movie to the database.
     *
     * @param movie The Movie object to be added to the database.
     */

    public static void addMovie(Movie movie) {

        try (FileWriter fw = new FileWriter("DB/Movie.csv", true)) {
            String temp = String.format("%s, %s, %d, %d\n", movie.getTitle(), movie.getDirector(), movie.getReleasedYear(), movie.getRunningTime());
            fw.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } 

    }
    
     /**
     * Retrieves a movie from the database based on its title.
     *
     * @param title The title of the movie to retrieve.
     * @return The Movie object with the specified title, or a default Movie object if not found.
     */

    public static Movie retrieveMovie(String title) {
        ArrayList<Movie> al = allMovies();
        Iterator<Movie> iter = al.iterator();
        while (iter.hasNext()) {
            Movie x = iter.next();
            if (x.getTitle().equals(title)) {
                return x;
            }
        }
        return new Movie();
    }

    /**
     * Retrieves all movies from the database and fills in an ArrayList.
     *
     * @return An ArrayList containing all Movie objects in the database.
     */

    public static ArrayList<Movie> allMovies() {
        ArrayList<Movie> al = new ArrayList<>();
        try (FileReader fr = new FileReader("DB/Movie.csv");
                BufferedReader bis = new BufferedReader(fr)) {
            String str;
            bis.readLine();
            while ((str = bis.readLine()) != null) {
                String[] strar = str.split(", ");
                Movie temp = new Movie(strar[0], strar[1], Integer.parseInt(strar[2]), Integer.parseInt(strar[3]));
                al.add(temp);
                directors.add(strar[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return al;
    }

     /**
     * Retrieves a user-specific movie list from their database file.
     *
     * @param user The User object associated with the movie list.
     * @return An ArrayList containing Movie objects in the user's movie list.
     */

    public static ArrayList<Movie> getUserDB(User user) {
        ArrayList<Movie> al = new ArrayList<>();
        
        try(FileReader fr = new FileReader("DB/UserDB/DB" + user.getUsername() + ".csv");
        BufferedReader bis = new BufferedReader(fr)) {
            String str;
            bis.readLine();
            while ((str = bis.readLine()) != null) {
                String[] strar = str.split(", ");
                Movie temp = new Movie(strar[0], strar[1], Integer.parseInt(strar[2]), Integer.parseInt(strar[3]));
                al.add(temp);
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return al;
    }
    
    /**
     * Removes a movie from a user's watchlist within their database file.
     *
     * @param movieTitle The title of the movie to remove from the user's watchlist.
     * @param user       The User object whose watchlist is being modified.
     */


    public static void removeFromWatchlist(String movieTitle, User user) {
        ArrayList<Movie> al = getUserDB(user);
        Iterator<Movie> iter = al.iterator();

        while (iter.hasNext()) {
            Movie movie = iter.next();
            if (movie.getTitle().equals(movieTitle)) {
                iter.remove();
                break;
            }
        }

        try (FileWriter fw = new FileWriter("DB/UserDB/DB" + user.getUsername() + ".csv");
                BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("Title, Director, Year, Runtime\n");

            for (Movie movie : al) {
                String temp = String.format("%s, %s, %d, %d\n", movie.getTitle(), movie.getDirector(),
                        movie.getReleasedYear(), movie.getRunningTime());
                bw.write(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
