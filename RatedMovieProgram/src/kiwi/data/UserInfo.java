package kiwi.data;

import java.util.ArrayList;

import kiwi.dto.Bookmark;
import kiwi.dto.Movie;
import kiwi.dto.User;

// 로그인, 로그아웃시 갱신되는 정보들을 모아놓음.
public class UserInfo {
	private User user;
	private ArrayList<Movie> listOfBookmark;
	
	public UserInfo(User user, Bookmark bookmark) {
		this.user = user;
		// bookmark ->
	}
}