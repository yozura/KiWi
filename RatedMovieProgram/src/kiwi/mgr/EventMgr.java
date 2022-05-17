package kiwi.mgr;

import kiwi.dto.User;
import kiwi.header.CustomEvent;

public class EventMgr {
	private static EventMgr instance = new EventMgr();
	
	public static EventMgr getInstance() {
		return instance;
	}
	
	public void execute(CustomEvent event) {
		switch (event.eEvent) {
		case ENTER_USER:
		{
			// lParam : User
			User user = (User)event.lParam;
			
			
		}
			break;
		case LOAD_MOVIE:
			break;
		case EXIT:
			break;
		default:
			break;
		}
	}
}