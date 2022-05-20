package kiwi.gui.process;

import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import kiwi.dao.UserDAO;
import kiwi.dto.Movie;
import kiwi.dto.User;
import kiwi.header.Define.SCREEN_TYPE;
import kiwi.mgr.ScreenMgr;
import kiwi.mgr.UserMgr;

public class LoginProcess {
	public void loginUser(String id, String password, JComponent comp) {
		// TODO :: password, id 대조해서 맞는 아이디의 정보 가져오기.
		UserDAO uDAO = new UserDAO();
		
		if (!checkValidationUser(new String[] { id, password })) {
			return;
		}
		
		User user = uDAO.selectId(id, password);
		if (user == null) {
			// SQLError
			return;
		}
		
		Vector<Movie> vecBookmark = uDAO.findBookmarkByUserId(id);
		
		UserMgr.getInstance().enter(user, vecBookmark);
		ScreenMgr.getInstance().changeCurrentScreen(SCREEN_TYPE.HOME, true, comp);
	}
	
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