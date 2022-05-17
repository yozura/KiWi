package kiwi.mgr;

import javax.swing.JPanel;

import kiwi.gui.BookmarkScreen;
import kiwi.gui.ErrorScreen;
import kiwi.gui.HomeScreen;
import kiwi.gui.SignInScreen;
import kiwi.gui.SignUpScreen;
import kiwi.header.Define.SCREEN_TYPE;

public class ScreenMgr {
	private static ScreenMgr instance = new ScreenMgr();
	private JPanel pCurScreen;

	public static ScreenMgr getInstance() {
		return instance;
	}
	
	ScreenMgr() {
		pCurScreen = new SignInScreen();
	}
	
	public void changeCurrentScreen(SCREEN_TYPE _eType) {
		switch (_eType) {
		case HOME -> pCurScreen = new HomeScreen();
		case SIGN_IN -> pCurScreen = new SignInScreen();
		case SIGN_UP -> pCurScreen = new SignUpScreen();
		case BOOKMARK -> pCurScreen = new BookmarkScreen();
		case ERROR -> pCurScreen = new ErrorScreen();
		default -> pCurScreen = new ErrorScreen();
		}
	}
	
	public JPanel getCurrentScreen() {
		return pCurScreen;
	}
}