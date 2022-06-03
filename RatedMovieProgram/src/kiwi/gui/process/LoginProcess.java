package kiwi.gui.process;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import kiwi.dao.ReviewDAO;
import kiwi.dao.UserDAO;
import kiwi.dto.Movie;
import kiwi.dto.Review;
import kiwi.dto.User;
import kiwi.header.Define.SCREEN_TYPE;
import kiwi.header.Define.USER_TYPE;
import kiwi.mgr.MovieMgr;
import kiwi.mgr.ReviewMgr;
import kiwi.mgr.ScreenMgr;
import kiwi.mgr.UserMgr;

public class LoginProcess {
	// 유저 로그인
	public void loginUser(String id, String password, JComponent comp) {
		UserDAO uDAO = new UserDAO();
		ReviewDAO rDAO = new ReviewDAO();
		
		if (!checkValidationUser(new String[] { id, password })) {
			return;
		}
		
		User user = uDAO.findUser(id, password);
		if (user == null) {
			JOptionPane.showMessageDialog(null, "ID, Password의 정보가 잘못되었습니다. 다시 입력해주세요.");
			return;
		}
		
		MovieMgr.getInstance().reloadMovie();
		ReviewMgr.getInstance().reloadReview();
		LinkedHashMap<Integer, Movie> mapBookmark = uDAO.selectMapBookmarkByUserId(id);
		LinkedHashMap<Integer, Review> mapReview = rDAO.selectMapReviewByUserId(id);
		UserMgr.getInstance().enter(user, mapBookmark, mapReview, (id.equals("administrator")) ? USER_TYPE.ADMIN : USER_TYPE.NORMAL);
		ScreenMgr.getInstance().changeCurScreenWithBar(SCREEN_TYPE.HOME, comp);
	}
	
	// 아이디, 비밀번호 유효성 검사
	public boolean checkValidationUser(String...strings) {
		if (!Pattern.matches("^[a-zA-Z0-9]{6,20}$", strings[0])) {
			JOptionPane.showMessageDialog(null, "ID는 최소 6자 이상 20자 이내 영문, 숫자로 구성되어야 합니다.");
			return false;
		}
		
		if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{10,20}$", strings[1])) {
			JOptionPane.showMessageDialog(null, "Password는 최소 10자 이상 20자 이내 하나 이상의 영문, 숫자 및 특수문자로 구성되야 합니다.");
			return false;
		}
		
		return true;	
	}
}