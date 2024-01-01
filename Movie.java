import java.io.Serializable;

class Movie implements Serializable, Comparable<Movie> {
	private String title;
	private String director;
	private int releasedYear;
	private int runningTime;
	private static int countMovies = 0;

	public Movie(String title, String director, int releasedYear, int runningTime) {
		this.title = title;
		this.director = director;
		this.releasedYear = releasedYear;
		this.runningTime = runningTime;
		countMovies++;
	}

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
