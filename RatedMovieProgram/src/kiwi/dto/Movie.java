package kiwi.dto;

import java.awt.image.BufferedImage;
import java.sql.Date;

public class Movie implements java.io.Serializable {
	private static final long serialVersionUID = 7494491231032079451L;
	
	private int id;
	private String title;
	private String director;
	private String actors;
	private String genre;
	private int freshRate;
	private Date releaseDate;
	private int runningTime;
	private int ageLimit;
	private String summary;
	private BufferedImage poster;
	
	public Movie(int id, String title, String director, String actors, String genre, int freshRate, Date releaseDate,
			int runningTime, int ageLimit, String summary, BufferedImage poster) {
		this.id = id;
		this.title = title;
		this.director = director;
		this.actors = actors;
		this.genre = genre;
		this.freshRate = freshRate;
		this.releaseDate = releaseDate;
		this.runningTime = runningTime;
		this.ageLimit = ageLimit;
		this.summary = summary;
		this.poster = poster;
	}
	public BufferedImage getPoster() {
		return poster;
	}
	public void setPoster(BufferedImage poster) {
		this.poster = poster;
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
	public int getRate() {
		return freshRate;
	}
	public void setRate(int freshRate) {
		this.freshRate = freshRate;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
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
