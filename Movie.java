class Movie{
	private String title;
	private String director;
	private int releasedYear;
	private int runningTime;

	public Movie(String title, String director, int releasedYear, int runningTime){
		this.title = title;
		this.director = director;
		this.releasedYear = releasedYear;
		this.runningTime = runningTime;
	}
	public Movie(){
		title = "Inglourious Basterds";
		director = "Quentin Tarantino";
		releasedYear = 2009;
		runningTime = 153;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}

	public int getReleasedYear() {
		return releasedYear;
	}
	public void setReleasedYear(int releasedYear) {
		this.releasedYear = releasedYear;
	}
	
	public int getRunningTime() {
		return runningTime;
	}
	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}
}
