import java.io.Serializable;


/**
 * The Movie class represents a movie entity in the Local Movie Database.
 * It implements the Serializable interface to enable object serialization.
 * Movies have attributes such as title, director, release year, and running time.
 * The class also tracks the total count of movies created.
 *
 * @author Farid, Melek, Asliddin
 * @version 4.0
 */


class Movie implements Serializable, Comparable<Movie> {
	private String title;
	private String director;
	private int releasedYear;
	private int runningTime;
	private static int countMovies = 0;

	 /**
     * Constructs a new Movie object with the specified attributes.
     *
     * @param title       The title of the movie.
     * @param director    The director of the movie.
     * @param releasedYear The year the movie was released.
     * @param runningTime The duration of the movie in minutes.
     */

	public Movie(String title, String director, int releasedYear, int runningTime) {
		this.title = title;
		this.director = director;
		this.releasedYear = releasedYear;
		this.runningTime = runningTime;
		countMovies++;
	}

 	/**
     * Default constructor for a Movie object with preset values.
     * Used for testing purposes.
     */

	public Movie() {
		title = "Kill Bill: Vol II";
		director = "Quentin Tarantino";
		releasedYear = 2009;
		runningTime = 153;
		countMovies++;
	}

	public String getTitle() {
		return title;
	}

	public String getDirector() {
		return director;
	}

	public int getReleasedYear() {
		return releasedYear;
	}

	public int getRunningTime() {
		return runningTime;
	}

	public int getCountMovies() {
		return countMovies;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setReleasedYear(int releasedYear) {
		this.releasedYear = releasedYear;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	public static void setCountMovies(int countMovies) {
		Movie.countMovies = countMovies;
	}

	 /**
     * Compares two Movie objects based on their release years in descending order.
     *
     * @param o The Movie object to compare with.
     * @return A negative value if this movie was released later than the specified movie,
     *         a positive value if this movie was released earlier, or 0 if they were released in the same year.
     */

	@Override
	public String toString() {
		return this.getTitle() + " " + this.getDirector() + " " + this.releasedYear + " " + this.runningTime;
	}

	@Override
    public int compareTo(Movie o) {
        return o.releasedYear - this.getReleasedYear();
    }
}

// Blyoop
