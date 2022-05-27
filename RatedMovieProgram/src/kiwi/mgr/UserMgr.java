package kiwi.mgr;

import java.util.Vector;

import kiwi.dto.Movie;
import kiwi.dto.User;
import kiwi.header.Define.USER_TYPE;

public class UserMgr {
	private static UserMgr instance = new UserMgr();
	private User currentUser;
	private Vector<Movie> vecBookmark;
	private USER_TYPE uType;
	
	public static UserMgr getInstance() {
		return instance;
	}
	
	public UserMgr() {
		this.uType = USER_TYPE.ADMIN;
	}
	
	// enter :: 로그인 시점
	public void enter(User user, Vector<Movie> vecBookmark, USER_TYPE uType) {
		this.currentUser = user;
		this.vecBookmark = vecBookmark;
		this.uType = uType;
		
		System.out.println("Entry : " + user.getNickname() + " " + uType.toString());
	}
	
	// addBookmark :: 북마크에 추가한 시점
	public void addBookmark(Movie movie) {
		vecBookmark.add(movie);
	}
	
	// deleteBookmark :: 북마크에서 지운 시점
	public void deleteBookmark() {
		
	}
	
	// exit :: 로그아웃 시점
	public void exit() {
		currentUser = null;
		vecBookmark.clear();
	}
	
	public User getCurUser() {
		return currentUser;
	}
}