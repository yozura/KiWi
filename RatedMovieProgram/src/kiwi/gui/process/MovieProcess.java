package kiwi.gui.process;

import java.util.regex.Pattern;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import kiwi.dao.ReviewDAO;
import kiwi.dto.Review;
import kiwi.mgr.MovieMgr;
import kiwi.mgr.ReviewMgr;
import kiwi.mgr.ScreenMgr;
import kiwi.mgr.UserMgr;

public class MovieProcess {
	public void AddReview(Review review, JComponent comp) {
		ReviewDAO rDAO = new ReviewDAO();
		rDAO.insert(review);
		
		// TODO :: freshRate 재계산
		MovieMgr.getInstance().updateCalculateFresh(review.getMovieId());
		
		MovieMgr.getInstance().load();
		ReviewMgr.getInstance().load();
		ScreenMgr.getInstance().redirectWithSideBar(comp);
	}
	
	public boolean checkValidationReview(String string) {
		 // Review
		 if (!Pattern.matches("^[a-zA-Z가-힣0-9\\s,!@#$:?.]{1,100}$", string)) {
			JOptionPane.showMessageDialog(null, "리뷰는 100자 이내로 입력해야 합니다.");
			System.out.println("리뷰는 100자 이내로 입력해야 합니다.");
			return false;
		 }
		 
		 return true;
	}
	
	public boolean checkCurrentUser() {
		if (UserMgr.getInstance().getCurUser() == null) {
			JOptionPane.showMessageDialog(null, "로그인 후 이용 가능합니다.");
			System.out.println("로그인 후 이용 가능합니다.");
			return false;
		}
		
		return true;
	}
}