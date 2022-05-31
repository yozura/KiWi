package kiwi.mgr;

import javax.swing.JComponent;
import javax.swing.JPanel;

import kiwi.gui.AdminScreen;
import kiwi.gui.HomeScreen;
import kiwi.gui.LoginScreen;
import kiwi.gui.MainFrame;
import kiwi.gui.MovieListScreen;
import kiwi.gui.MovieScreen;
import kiwi.gui.SideBar;
import kiwi.gui.SignUpScreen;
import kiwi.gui.UserScreen;
import kiwi.header.Define.SCREEN_TYPE;

public class ScreenMgr {
	private static ScreenMgr instance = new ScreenMgr();
	private JPanel pCurScreen;
	private SCREEN_TYPE curScreenType;
	private JPanel pSideBar;

	public static ScreenMgr getInstance() {
		return instance;
	}
	
	ScreenMgr() {
		pSideBar = new SideBar();
		pCurScreen = new LoginScreen();
		curScreenType = SCREEN_TYPE.LOG_IN;
	}
	
	private JPanel getScreenByScreenType(SCREEN_TYPE eType) {
		return switch (eType) {
		case HOME -> new HomeScreen();
		case LOG_IN -> new LoginScreen();
		case SIGN_UP -> new SignUpScreen();
		case ADMIN -> new AdminScreen();
		case USER -> new UserScreen();
		case MOVIE -> new MovieScreen();
		case MOVIE_LIST -> new MovieListScreen();
		default -> new HomeScreen();
		};
	}
	
	public void redirectWithSideBar() {
		pCurScreen = getScreenByScreenType(curScreenType);
		
		MainFrame rootFrame = (MainFrame)pCurScreen.getTopLevelAncestor();
		rootFrame.revalidateScreenWithSideBar();
	}
	
	public void redirectWithSideBar(JComponent comp) {
		pCurScreen = getScreenByScreenType(curScreenType);
		
		MainFrame rootFrame = (MainFrame)comp.getTopLevelAncestor();
		rootFrame.revalidateScreenWithSideBar();
	}
	
	public void changeCurScreenWithBar(SCREEN_TYPE eType, JComponent comp) {
		curScreenType = eType;
		pCurScreen = getScreenByScreenType(eType);
		
		MainFrame rootFrame = (MainFrame)comp.getTopLevelAncestor();
		rootFrame.revalidateScreenWithSideBar();
	}
	
	public void changeCurScreen(SCREEN_TYPE eType, JComponent comp) {
		curScreenType = eType;
		pCurScreen = getScreenByScreenType(eType);
		
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