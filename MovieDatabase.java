import java.io.*;
import java.util.ArrayList;

public class MovieDatabase {
    private ArrayList<Movie> movies;

    public MovieDatabase() {
        movies = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        if (!movies.contains(movie)) {
            movies.add(movie);
            System.out.println("Movie added to the database: " + movie.getTitle());
            // You can add file writing logic here to store the updated movie list in a file
        } else {
            System.out.println("Movie already exists in the database.");
        }
    }

    public void removeMovie(Movie movie) {
        if (movies.contains(movie)) {
            movies.remove(movie);
            System.out.println("Movie removed from the database: " + movie.getTitle());
            // Here aswell
        } else {
            System.out.println("Movie does not exist in the database.");
        }
    }

    public Movie getMovieByTitle(String title) {
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                return movie;
            }
        }
        return null; 
    }

    public void displayAllMovies() {
        System.out.println("List of Movies in the Database:");
        for (Movie movie : movies) {
            System.out.println(movie);
            System.out.println("----------------------");
        }
    }
}
