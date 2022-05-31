package kiwi.gui.process;

import javax.swing.JComponent;

import kiwi.dao.BookmarkDAO;
import kiwi.dao.ReviewDAO;
import kiwi.dto.Bookmark;
import kiwi.dto.Review;
import kiwi.mgr.MovieMgr;
import kiwi.mgr.ReviewMgr;
import kiwi.mgr.ScreenMgr;
import kiwi.mgr.UserMgr;

public class UserProcess {
	public void deleteBookmark(Bookmark bookmark, JComponent comp) {
		BookmarkDAO bDAO = new BookmarkDAO();
		bDAO.delete(bookmark);
		
					
		UserMgr.getInstance().reloadBookmark();
		ScreenMgr.getInstance().redirectWithSideBar(comp);
	}
	
	public void deleteReview(Review review, JComponent comp) {
		ReviewDAO rDAO = new ReviewDAO();
		rDAO.delete(review);
		
		
		UserMgr.getInstance().reloadReview();
		ReviewMgr.getInstance().reloadReview();
		ScreenMgr.getInstance().redirectWithSideBar(comp);
	}
}
