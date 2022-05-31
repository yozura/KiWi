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
	// 유저 개인 북마크 창에서 북마크 삭제
	public void deleteBookmark(Bookmark bookmark, JComponent comp) {
		BookmarkDAO bDAO = new BookmarkDAO();
		bDAO.delete(bookmark);
					
		UserMgr.getInstance().reloadBookmark();
		ScreenMgr.getInstance().redirectWithSideBar(comp);
	}
	
	// 유저 개인 리뷰 창에서 리뷰 삭제
	public void deleteReview(Review review, JComponent comp) {
		ReviewDAO rDAO = new ReviewDAO();
		rDAO.delete(review);
		
		MovieMgr.getInstance().updateCalculateFresh(review.getMovieId());
		MovieMgr.getInstance().reloadMovie();
		UserMgr.getInstance().reloadReview();
		ReviewMgr.getInstance().reloadReview();
		ScreenMgr.getInstance().redirectWithSideBar(comp);
	}
}
