package kiwi.mgr;

import java.util.HashMap;
import java.util.Vector;

import kiwi.dao.ReviewDAO;
import kiwi.dao.UserDAO;
import kiwi.dto.Movie;
import kiwi.dto.Review;
import kiwi.dto.User;
import kiwi.header.Define.USER_TYPE;

public class UserMgr {
	private static UserMgr instance = new UserMgr();
	private User curUser;
	private HashMap<Integer, Movie> mapBookmark;
	private HashMap<Integer, Review> mapReview;
	private USER_TYPE uType;
	
	public static UserMgr getInstance() {
		return instance;
	}
	
	public void enter(User user, HashMap<Integer, Movie> mapBookmark, HashMap<Integer, Review> mapReview, USER_TYPE uType) {
		this.curUser = user;
		this.mapBookmark = mapBookmark;
		this.mapReview = mapReview;
		this.uType = uType;
		
		System.out.println("Entry : " + user.getNickname() + ", 권한 : " + uType.toString());
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
	
	public void reloadUserInfo() {
		UserDAO uDAO = new UserDAO();
		uDAO.findUser(curUser.getId(), null);
	}
	
	public void reloadReview() {
		ReviewDAO rDAO = new ReviewDAO();
		mapReview = rDAO.selectMapReviewByUserId(curUser.getId());
	}
	
	public void reloadBookmark() {
		UserDAO uDAO = new UserDAO();
		mapBookmark = uDAO.selectMapBookmarkByUserId(curUser.getId());
	}
	
	public boolean checkBookmarkByMovieId(int movieId) {
		if (mapBookmark == null) {
			return false;
		}
		
		if (mapBookmark.containsKey(movieId)) {
			return true;
		}
		return false;
	}
	
	public boolean checkReviewByMovieId(int movieId) {
		if (mapReview == null) {
			return false;
		}
		
		for (Review review : mapReview.values()) {
			if (review.getMovieId() == movieId) {
				return true;
			}
		}
		
		return false;
	}
	
	public USER_TYPE getUserType() {
		return uType;
	}
	
	public User getCurUser() {
		return curUser;
	}
	
	public HashMap<Integer, Review> getMapReview() {
		return mapReview;
	}
	
	public HashMap<Integer, Movie> getMapBookmark() {
		return mapBookmark;
	}
}