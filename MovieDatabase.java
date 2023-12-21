import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class MovieDatabase {
    private ArrayList<Movie> movies;

    public MovieDatabase() {
        movies = new ArrayList<>();
    }

    public static void addMovie(Movie movie) {
        boolean check = true;
        ArrayList<Movie> al = allMovies();

        Iterator<Movie> iter = al.iterator();

        while (iter.hasNext()) {
            Movie x = iter.next();
            if (x.getTitle().equals(movie.getTitle())) {
                check = false;
            }
        }
        if (check) {
            try (FileWriter fw = new FileWriter("DB/Movie.csv", true);
                    BufferedWriter bw = new BufferedWriter(fw)) {
                String temp = String.format("%s, %s, %d, %d\n", movie.getTitle(), movie.getDirector(),
                        movie.getReleasedYear(), movie.getRunningTime());
                bw.append(temp);
                // bw.newLine?
            } catch (Exception e) {

            }
        } else {
            System.out.println("Such movie already exist!");
        }
    }

    public static void remove(String movie) {
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
        // try(FileInputStream fis = new FileInputStream("DB/Movie.txt");
        // ObjectInputStream ois = new ObjectInputStream(fis)){
        // Movie temp;
        // try{
        // while((temp = (Movie)ois.readObject()) != null){
        // al.add(temp);
        // }
        // }catch(EOFException e){

        // }

        // }catch(Exception e){
        // e.printStackTrace();
        // }

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
}
