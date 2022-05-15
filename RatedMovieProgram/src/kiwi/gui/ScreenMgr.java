package kiwi.gui;

import javax.swing.JPanel;

enum Screen_Type {
	HOME,
	SIGN_IN,
	SGIN_UP,
	END
}

public class ScreenMgr {
	private static ScreenMgr instance = new ScreenMgr();
	private JPanel pHomeScreen;
	private JPanel pSignInScreen;
	private JPanel pSignUpScreen;
	private JPanel pCurScreen;

	ScreenMgr() {
		pHomeScreen = new HomeScreen();
		pSignInScreen = new SignInScreen();
		pSignUpScreen = new SignUpScreen();
		pCurScreen = pSignInScreen;
	}
	
	public static ScreenMgr getInstance() {
		return instance;
	}
	
	public void changeCurrentScreen(Screen_Type _eType) {
		switch (_eType) {
		case SGIN_UP -> pCurScreen = pSignUpScreen;
		case SIGN_IN -> pCurScreen = pSignInScreen;
		default -> pCurScreen = pHomeScreen;
		}
	}
	
	public JPanel getCurrentScreen() {
		return pCurScreen;
	}
}