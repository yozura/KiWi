package kiwi.header;

// 열거체 집합 or 전역 변수, 구조체 집합
public class Define {
	public enum USER_TYPE {
		ADMIN,
		NORMAL,
		
		END
	}
	
	public enum SCREEN_TYPE {
		HOME,
		LOG_IN,
		SIGN_UP,
		ADMIN,
		USER,
		MOVIE,
		POP_MOVIES,
		ERROR,
		
		END
	}
	
	public enum SFX_TYPE {
		LOG_IN,
		CLICK,
		END
	}
	
	public enum BGM_TYPE {
		
		END
	}
}