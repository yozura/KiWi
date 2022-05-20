package kiwi.mgr;

import java.util.Vector;

import kiwi.dto.Movie;
import kiwi.dto.User;

public class UserMgr {
	private static UserMgr instance = new UserMgr();
	private User currentUser;
	private Vector<Movie> vecBookmark;
	
	public static UserMgr getInstance() {
		return instance;
	}
	
	// enter :: 로그인 시점
	public void enter(User user, Vector<Movie> vecBookmark) {
		this.currentUser = user;
		this.vecBookmark = vecBookmark;
	}
	
	// addBookmark :: 북마크에 추가한 시점
	public void addBookmark(Movie movie) {
		vecBookmark.add(movie);
	}
	
	// deleteBookmark :: 북마크에서 지운 시점
	public void deleteBookmark() {
		// 
	}
	
	// exit :: 로그아웃 시점
	public void exit() {
		currentUser = null;
		vecBookmark.clear();
	}
}