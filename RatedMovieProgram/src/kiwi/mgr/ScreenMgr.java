package kiwi.mgr;

import javax.swing.JComponent;
import javax.swing.JPanel;

import kiwi.gui.AdminScreen;
import kiwi.gui.BookmarkScreen;
import kiwi.gui.ErrorScreen;
import kiwi.gui.ForgotPasswordScreen;
import kiwi.gui.HomeScreen;
import kiwi.gui.LoginScreen;
import kiwi.gui.MainFrame;
import kiwi.gui.MovieScreen;
import kiwi.gui.ReviewScreen;
import kiwi.gui.PopularMoviesScreen;
import kiwi.gui.SideBar;
import kiwi.gui.SignUpScreen;
import kiwi.gui.UserScreen;
import kiwi.header.Define.SCREEN_TYPE;

public class ScreenMgr {
	private static ScreenMgr instance = new ScreenMgr();
	private JPanel pCurScreen;
	private JPanel pSideBar;

	public static ScreenMgr getInstance() {
		return instance;
	}
	
	ScreenMgr() {
		pSideBar = new SideBar();
		//pCurScreen = new LoginScreen();
		pCurScreen = new ReviewScreen();
	}
	
	public void changeCurScreenWithBar(SCREEN_TYPE eType, JComponent comp) {
		switch (eType) {
		case HOME -> pCurScreen = new HomeScreen();
		case LOG_IN -> pCurScreen = new LoginScreen();
		case SIGN_UP -> pCurScreen = new SignUpScreen();
		case ADMIN -> pCurScreen = new AdminScreen();
		case USER -> pCurScreen = new UserScreen();
		case MOVIE -> pCurScreen = new MovieScreen();
		case FORGOT_PASSWORD -> pCurScreen = new ForgotPasswordScreen();
		case BOOKMARK -> pCurScreen = new BookmarkScreen();
		case REVIEW -> pCurScreen = new ReviewScreen();
		case POP_MOVIES -> pCurScreen = new PopularMoviesScreen();
		case ERROR -> pCurScreen = new ErrorScreen();
		default -> pCurScreen = new ErrorScreen();
		}
		
		MainFrame rootFrame = (MainFrame)comp.getTopLevelAncestor();
		rootFrame.revalidateScreenWithSideBar();
	}
	
	public void changeCurScreen(SCREEN_TYPE eType, JComponent comp) {
		switch (eType) {
		case HOME -> pCurScreen = new HomeScreen();
		case LOG_IN -> pCurScreen = new LoginScreen();
		case SIGN_UP -> pCurScreen = new SignUpScreen();
		case ADMIN -> pCurScreen = new AdminScreen();
		case USER -> pCurScreen = new UserScreen();
		case MOVIE -> pCurScreen = new MovieScreen();
		case FORGOT_PASSWORD -> pCurScreen = new ForgotPasswordScreen();
		case BOOKMARK -> pCurScreen = new BookmarkScreen();
		case REVIEW -> pCurScreen = new ReviewScreen();
		case POP_MOVIES -> pCurScreen = new PopularMoviesScreen();
		case ERROR -> pCurScreen = new ErrorScreen();
		default -> pCurScreen = new ErrorScreen();
		}
		
		MainFrame rootFrame = (MainFrame)comp.getTopLevelAncestor();
		rootFrame.revalidateScreen();
	}
	
	public JPanel getCurScreen() {
		return pCurScreen;
	}
	
	public JPanel getSideBar() {
		return pSideBar;
	}
}