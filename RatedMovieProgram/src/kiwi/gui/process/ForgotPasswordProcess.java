package kiwi.gui.process;

import java.util.regex.Pattern;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import kiwi.dao.UserDAO;
import kiwi.header.Define.SCREEN_TYPE;
import kiwi.mgr.ScreenMgr;

public class ForgotPasswordProcess {
	public void changePassword(String email, String Password, JComponent comp) {
		// db update
		UserDAO uDAO = new UserDAO();
		if (uDAO.updatePassword(email, Password)) {
			JOptionPane.showMessageDialog(null, "새로운 비밀번호가 성공적으로 변경되었습니다. 로그인 창에 가셔서 다시 로그인해주세요.");
			ScreenMgr.getInstance().changeCurScreen(SCREEN_TYPE.LOG_IN, comp);
		} else {
			JOptionPane.showMessageDialog(null, "새로운 비밀번호 저장에 실패했습니다.");
		}
	}
	
	public boolean checkExistByEmail(String email) {
		if (!(new UserDAO().checkExistByEmail(email))) {
			JOptionPane.showMessageDialog(null, "입력한 이메일이 존재하지 않습니다. 다시 입력해주세요.");
			System.out.println("입력한 이메일이 존재하지 않습니다. 다시 입력해주세요.");
			return false;
		}
		return true;
	}
	
	public boolean checkValidationEmail(String email) {
		if (!Pattern.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", email)) {
			JOptionPane.showMessageDialog(null, "Email을 올바르게 입력해주세요.");
			System.out.println("Email을 올바르게 입력해주세요.");
			return false;
		}
		return true;
	}
	
	public boolean checkValidationPassword(String password) {
		if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{10,20}$", password)) {
			JOptionPane.showMessageDialog(null, "Password는 최소 10자 이상 20자 이내 하나 이상의 영문, 숫자 및 특수문자로 구성되야 합니다.");
			System.out.println("Password는 최소 10자 이상 20자 이내 하나 이상의 영문, 숫자 및 특수문자로 구성되야 합니다.");
			return false;
		}
		return true;
	}
}
