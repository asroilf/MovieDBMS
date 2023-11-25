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
}
