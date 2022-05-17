package kiwi.mgr;

import kiwi.data.UserInfo;
import kiwi.dto.Bookmark;
import kiwi.dto.User;

public class UserMgr {
	private static UserMgr instance = new UserMgr();
	private UserInfo currentUser;
	
	public UserMgr getInstance() {
		return instance;
	}
	
	// enter :: 로그인 시점
	public void enter(User user, Bookmark bookmark) {
		currentUser = new UserInfo(user, bookmark);
	}
	
	// addBookmark :: 북마크에 추가한 시점
	public void addBookmark() {
		
	}
	
	// deleteBookmark :: 북마크에서 지운 시점
	public void deleteBookmark() {
		
	}
	
	// exit :: 로그아웃 시점
	public void exit() {
		
	}

}
