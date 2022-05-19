package kiwi.mgr;

import javax.swing.JComponent;
import javax.swing.JPanel;

import kiwi.gui.BookmarkScreen;
import kiwi.gui.ErrorScreen;
import kiwi.gui.HomeScreen;
import kiwi.gui.LoginScreen;
import kiwi.gui.MainFrame;
import kiwi.gui.SignUpScreen;
import kiwi.header.Define.SCREEN_TYPE;

public class ScreenMgr {
	private static ScreenMgr instance = new ScreenMgr();
	private JPanel pCurScreen;

	public static ScreenMgr getInstance() {
		return instance;
	}
	
	ScreenMgr() {
		pCurScreen = new LoginScreen();
	}
	
	public void changeCurrentScreen(SCREEN_TYPE _eType, boolean hasSidebar, JComponent comp) {
		switch (_eType) {
		case HOME -> pCurScreen = new HomeScreen();
		case SIGN_IN -> pCurScreen = new LoginScreen();
		case SIGN_UP -> pCurScreen = new SignUpScreen();
		case BOOKMARK -> pCurScreen = new BookmarkScreen();
		case ERROR -> pCurScreen = new ErrorScreen();
		default -> pCurScreen = new ErrorScreen();
		}
		
		MainFrame rootFrame = (MainFrame)comp.getTopLevelAncestor();
		rootFrame.revalidateScreen(hasSidebar);
	}
	
	public JPanel getCurrentScreen() {
		return pCurScreen;
	}
}