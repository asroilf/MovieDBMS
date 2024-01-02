import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MovieAPIExample {
    public static void main(String[] args) {
        try {
            String apiKey = "813778b0e7fa3c41a5fa952010669470";
            String apiUrl = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + apiKey + "&language=en-US&page=1";
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            // JSONObject jsonResponse = new JSONObject(response.toString());
            // JSONArray resultsArray = jsonResponse.getJSONArray("results");

            // for (int i = 0; i < resultsArray.length(); i++) {
            //     JSONObject movie = resultsArray.getJSONObject(i);

            //     String title = movie.getString("title");
            //     String releaseDate = movie.getString("release_date");
                
                
            //     String director = "";

            //     System.out.println("Title: " + title);
            //     System.out.println("Director: " + director);
            //     System.out.println("Release Year: " + getReleaseYear(releaseDate));
            //     System.out.println("-----------");
            // }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getReleaseYear(String releaseDate) {
        
        if (releaseDate.length() >= 4) {
            return releaseDate.substring(0, 4);
        } else {
            return "N/A";
        }
    }
}