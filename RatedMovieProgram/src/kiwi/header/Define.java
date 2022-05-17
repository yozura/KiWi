package kiwi.header;

// 열거체 집합 or 전역 변수 집합
public class Define {
	public enum SCREEN_TYPE {
		HOME,
		SIGN_IN,
		SIGN_UP,
		BOOKMARK,
		ERROR,
		
		END
	}
	
	public enum EVENT_TYPE {
		ENTER_USER,
		LOAD_MOVIE,
		ADD_BOOKMARK,
		DELETE_BOOKMARK,
		EXIT,
		
		END
	}
}
