package kiwi.gui.process;

import javax.swing.JComponent;

import kiwi.dao.UserDAO;
import kiwi.header.Pair;
import kiwi.mgr.ScreenMgr;
import kiwi.mgr.UserMgr;

public class BookmarkProcess {
	public void DeleteBookmark(int movieId, JComponent comp) {
		UserDAO uDAO = new UserDAO();
		uDAO.deleteBookmarkByMovieId(new Pair<String, Integer>(
						UserMgr.getInstance().getCurUser().getId()
						, movieId));
		
					
		UserMgr.getInstance().reloadBookmark();
		ScreenMgr.getInstance().redirectWithSideBar(comp);
	}
}