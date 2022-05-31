package kiwi.gui.process;

import java.util.regex.Pattern;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import kiwi.dao.BookmarkDAO;
import kiwi.dao.ReviewDAO;
import kiwi.dto.Bookmark;
import kiwi.dto.Review;
import kiwi.mgr.MovieMgr;
import kiwi.mgr.ReviewMgr;
import kiwi.mgr.ScreenMgr;
import kiwi.mgr.UserMgr;

public class MovieProcess {
	// 리뷰 저장 및 점수 반영
	public void addReview(Review review, JComponent comp) {
		ReviewDAO rDAO = new ReviewDAO();
		rDAO.insert(review);

		MovieMgr.getInstance().updateCalculateFresh(review.getMovieId());

		ReviewMgr.getInstance().reloadReview();
		MovieMgr.getInstance().reloadMovie();
		UserMgr.getInstance().reloadReview();
		MovieMgr.getInstance().setCurMovie(review.getMovieId());
		
		ScreenMgr.getInstance().redirectWithSideBar(comp);
	}
	
	// 유저 북마크 추가
	public void addBookmark(Bookmark bookmark, JComponent comp) {
		BookmarkDAO bDAO = new BookmarkDAO();
		bDAO.insert(bookmark);
		
		UserMgr.getInstance().reloadBookmark();
		ScreenMgr.getInstance().redirectWithSideBar(comp);
	}
	
	// 유저 북마크 삭제
	public void deleteBookmark(Bookmark bookmark, JComponent comp) {
		BookmarkDAO bDAO = new BookmarkDAO();
		bDAO.delete(bookmark);
		
		UserMgr.getInstance().reloadBookmark();
		ScreenMgr.getInstance().redirectWithSideBar(comp);
	}
	
	// 리뷰 유효성 검사
	public boolean checkValidationReview(String string) {
		 // Review
		 if (!Pattern.matches("^[a-zA-Z가-힣0-9\\s,!@#$:?.]{1,100}$", string)) {
			JOptionPane.showMessageDialog(null, "리뷰는 100자 이내로 입력해야 합니다.");
			System.out.println("리뷰는 100자 이내로 입력해야 합니다.");
			return false;
		 }
		 
		 return true;
	}
	
	// 로그인 확인
	public boolean checkCurrentUser() {
		if (UserMgr.getInstance().getCurUser() == null) {
			JOptionPane.showMessageDialog(null, "로그인 후 이용 가능합니다.");
			System.out.println("로그인 후 이용 가능합니다.");
			return false;
		}
		
		return true;
	}
}