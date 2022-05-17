package kiwi.header;

import kiwi.dto.Bookmark;
import kiwi.dto.Movie;
import kiwi.dto.User;
import kiwi.header.Define.EVENT_TYPE;
import kiwi.mgr.EventMgr;

// Event method or extension method
public class Func {
	void enterUser(User user, Bookmark bookmark) {
		CustomEvent newEvent = new CustomEvent();
		newEvent.eEvent = EVENT_TYPE.ENTER_USER;
		newEvent.lParam = user;
		newEvent.wParam = bookmark;
		
		EventMgr.getInstance().execute(newEvent);
	}
	
	void loadMovie(Movie movie) {
		CustomEvent newEvent = new CustomEvent();
		newEvent.eEvent = EVENT_TYPE.LOAD_MOVIE;
		newEvent.lParam = movie;

		EventMgr.getInstance().execute(newEvent);
	}
	
	void addBookmark(User user, Movie movie) {
		CustomEvent newEvent = new CustomEvent();
		newEvent.eEvent = EVENT_TYPE.ADD_BOOKMARK;
		newEvent.lParam = user;
		newEvent.wParam = movie;

		EventMgr.getInstance().execute(newEvent);
	}
	
	void exitUser(User user) {
		CustomEvent newEvent = new CustomEvent();
		newEvent.eEvent = EVENT_TYPE.EXIT;
		newEvent.lParam = user;
		
		EventMgr.getInstance().execute(newEvent);
	}
}