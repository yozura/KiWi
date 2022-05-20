package kiwi.dto;

public class Bookmark implements java.io.Serializable {
	private static final long serialVersionUID = -5308082460281231006L;
	
	private String userId;
	private int movieId;
	
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
}