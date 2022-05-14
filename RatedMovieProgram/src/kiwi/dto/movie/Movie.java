package kiwi.dto.movie;

public class Movie {
	/*id
int AI PK
title
varchar(100)
director
varchar(100)
actors
varchar(100)
genre
varchar(100)
rate
double
releaseDate
datetime
runningTime
int
ageLimit
int
summary
varchar(300)
*/
	private int id;
	private String title;
	private String director;
	private String actors;
	private String genre;
	private double rate;
	private String releaseDate;
	private int runningTime;
	private int ageLimit;
	private String summary;
	
	public Movie(String title, String director, String actors, String genre, double rate, String releaseDate,
			int runningTime, int ageLimit, String summary) {
		this.title = title;
		this.director = director;
		this.actors = actors;
		this.genre = genre;
		this.rate = rate;
		this.releaseDate = releaseDate;
		this.runningTime = runningTime;
		this.ageLimit = ageLimit;
		this.summary = summary;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public int getRunningTime() {
		return runningTime;
	}
	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}
	public int getAgeLimit() {
		return ageLimit;
	}
	public void setAgeLimit(int ageLimit) {
		this.ageLimit = ageLimit;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
}
