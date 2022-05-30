package kiwi.gui.process;

import javax.swing.JComponent;

import kiwi.dao.BookmarkDAO;
import kiwi.dto.Bookmark;
import kiwi.mgr.ScreenMgr;
import kiwi.mgr.UserMgr;

public class BookmarkProcess {
	public void DeleteBookmark(Bookmark bookmark, JComponent comp) {
		BookmarkDAO bDAO = new BookmarkDAO();
		bDAO.delete(bookmark);
		
					
		UserMgr.getInstance().reloadBookmark();
		ScreenMgr.getInstance().redirectWithSideBar(comp);
	}
}