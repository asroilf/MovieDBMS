import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;


public class MovieDatabase {
    // ... other methods ...

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

    public static void addMovie(Movie movie) {

        try (FileWriter fw = new FileWriter("DB/Movie.csv", true)) {
            String temp = String.format("%s, %s, %d, %d\n", movie.getTitle(), movie.getDirector(), movie.getReleasedYear(), movie.getRunningTime());
            fw.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } 

    }
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return al;
    }

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
