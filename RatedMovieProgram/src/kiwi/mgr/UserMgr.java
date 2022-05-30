package kiwi.mgr;

import java.util.HashMap;
import java.util.Vector;

import kiwi.dao.UserDAO;
import kiwi.dto.Movie;
import kiwi.dto.User;
import kiwi.header.Define.USER_TYPE;

public class UserMgr {
	private static UserMgr instance = new UserMgr();
	private User curUser;
	private HashMap<Integer, Movie> mapBookmark;
	private USER_TYPE uType;
	
	public static UserMgr getInstance() {
		return instance;
	}
	
	public void enter(User user, HashMap<Integer, Movie> mapBookmark, USER_TYPE uType) {
		this.curUser = user;
		this.mapBookmark = mapBookmark;
		this.uType = uType;
		
		System.out.println("Entry : " + user.getNickname() + ", " + uType.toString());
	}
	
	public void setUserType(USER_TYPE uType) {
		this.uType = uType;
	}
	
	public void exit() {
		curUser = null;
		
		if (mapBookmark != null) {
			mapBookmark.clear();
		}
	}
	
	public void reloadBookmark() {
		UserDAO uDAO = new UserDAO();
		mapBookmark = uDAO.findMapBookmarkByUserId(curUser.getId());
	}
	
	public boolean checkBookmarkByMovieId(int movieId) {
		if (mapBookmark.containsKey(movieId)) {
			return true;
		}
		return false;
	}
	
	public USER_TYPE getUserType() {
		return uType;
	}
	
	public User getCurUser() {
		return curUser;
	}
	
	public HashMap<Integer, Movie> getMapBookmark() {
		return mapBookmark;
	}
}