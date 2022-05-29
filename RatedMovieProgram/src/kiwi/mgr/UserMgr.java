package kiwi.mgr;

import java.util.Vector;

import kiwi.dao.UserDAO;
import kiwi.dto.Movie;
import kiwi.dto.User;
import kiwi.header.Define.USER_TYPE;

public class UserMgr {
	private static UserMgr instance = new UserMgr();
	private User curUser;
	private Vector<Movie> vecBookmark;
	private USER_TYPE uType;
	
	public static UserMgr getInstance() {
		return instance;
	}
	
	// enter :: 로그인 시점
	public void enter(User user, Vector<Movie> vecBookmark, USER_TYPE uType) {
		this.curUser = user;
		this.vecBookmark = vecBookmark;
		this.uType = uType;
		
		System.out.println("Entry : " + user.getNickname() + " " + uType.toString());
	}
	
	public void setUserType(USER_TYPE uType) {
		this.uType = uType;
	}
	
	// exit :: 로그아웃 시점
	public void exit() {
		curUser = null;
		if (!vecBookmark.equals(null)) {
			vecBookmark.clear();
		}
	}
	
	public void reloadBookmark() {
		UserDAO uDAO = new UserDAO();
		vecBookmark = uDAO.findBookmarkByUserId(curUser.getId());
	}
	
	public USER_TYPE getUserType() {
		return uType;
	}
	
	public User getCurUser() {
		return curUser;
	}
	
	public Vector<Movie> getBookmark() {
		return vecBookmark;
	}
	
	public int getBookmarkCount() {
		return vecBookmark.size();
	}
}