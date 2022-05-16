package kiwi.gui;

import javax.swing.JPanel;

enum Screen_Type {
	HOME,
	SIGN_IN,
	SIGN_UP,
	ERROR,
	END
}

public class ScreenMgr {
	private static ScreenMgr instance = new ScreenMgr();
	private JPanel pCurScreen;

	ScreenMgr() {
		pCurScreen = new SignInScreen();
	}
	
	public static ScreenMgr getInstance() {
		return instance;
	}
	
	public void changeCurrentScreen(Screen_Type _eType) {
		switch (_eType) {
		case HOME -> pCurScreen = new HomeScreen();
		case SIGN_IN -> pCurScreen = new SignInScreen();
		case SIGN_UP -> pCurScreen = new SignUpScreen();
		case ERROR -> pCurScreen = new ErrorScreen();
		default -> pCurScreen = new ErrorScreen();
		}
	}
	
	public JPanel getCurrentScreen() {
		return pCurScreen;
	}
}