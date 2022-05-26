package kiwi.dto;

import java.sql.Date;

public class Review implements java.io.Serializable {
	private static final long serialVersionUID = 8314195945889500717L;
	
	private int id;
	private String userId;
	private int movieId;
	private String content;
	private int freshRate;
	private Date reviewDate;
	
	public Review(int id, String userId, int movieId, String content, int freshRate, Date reviewDate) {
		this.id = id;
		this.userId = userId;
		this.movieId = movieId;
		this.content = content;
		this.freshRate = freshRate;
		this.reviewDate = reviewDate;
	}

	public int getFreshRate() {
		return freshRate;
	}

	public void setFreshRate(int freshRate) {
		this.freshRate = freshRate;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
}