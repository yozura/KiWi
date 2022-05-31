package kiwi.gui.process;

import java.util.regex.Pattern;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import kiwi.dao.UserDAO;
import kiwi.dto.User;
import kiwi.header.Define.SCREEN_TYPE;
import kiwi.mgr.ScreenMgr;

public class SignUpProcess {
	public void createAccount(User user, JComponent comp) {
		// 새로운 계정 생성하기
		UserDAO uDAO = new UserDAO();
		uDAO.insert(user);

		JOptionPane.showMessageDialog(null, "계정 생성에 성공하였습니다. 로그인 창에서 로그인 해주세요.");
		ScreenMgr.getInstance().changeCurScreen(SCREEN_TYPE.LOG_IN, comp);
	}
	
	public boolean checkExistById(String id) {
		if (new UserDAO().checkExistById(id)) {
			JOptionPane.showMessageDialog(null, "이미 있는 ID입니다.", "아이디 중복 메시지", JOptionPane.PLAIN_MESSAGE);
			System.out.println("이미 있는 ID입니다.");
			return false;
		}
		return true;
	}
	
	public boolean checkExistByEmail(String email) {
		if (new UserDAO().checkExistByEmail(email)) {
			JOptionPane.showMessageDialog(null, "이미 있는 Email입니다.", "이메일 중복 메시지", JOptionPane.PLAIN_MESSAGE);
			System.out.println("이미 있는 Email입니다.");
			return false;
		}
		return true;
	}
	
	public boolean checkValidationUserInfo(String...strings) {
		// ID
		if (!Pattern.matches("^[a-zA-Z0-9]{6,20}$", strings[0])) {
			JOptionPane.showMessageDialog(null, "ID는 최소 6자 이상 20자 이내 영문, 숫자로 구성되어야 합니다.");
			System.out.println("ID는 최소 6자 이상 20자 이내 영문, 숫자로 구성되야 합니다.");
			return false;
		}

		// Password
		if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{10,20}$", strings[1])) {
			JOptionPane.showMessageDialog(null, "Password는 최소 10자 이상 20자 이내 하나 이상의 영문, 숫자 및 특수문자로 구성되야 합니다.");
			System.out.println("Password는 최소 10자 이상 20자 이내 하나 이상의 영문, 숫자 및 특수문자로 구성되야 합니다.");
			return false;
		}
		
		// Nickname
		if (!Pattern.matches("^[a-zA-Z0-9가-힣]{2,20}$", strings[2])) {
			JOptionPane.showMessageDialog(null, "Nickname은 최소 2자 이상 20자 이내 영문, 숫자, 한글로 구성되야 합니다.");
			System.out.println("Nickname은 최소 2자 이상 20자 이내 영문, 숫자, 한글로 구성되야 합니다.");
			return false;
		}
		
		// Birthday
		if (!Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", strings[3])) {
			JOptionPane.showMessageDialog(null, "Birthday는 yyyy-mm-dd의 형식으로 구성되어야 합니다.");
			System.out.println("Birthday는 yyyy-mm-dd의 형식으로 구성되어야 합니다.");
			return false;
		}
		
		// Email
		if (!Pattern.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", strings[4])) {
			JOptionPane.showMessageDialog(null, "Email을 올바르게 입력해주세요.");
			System.out.println("Email을 올바르게 입력해주세요.");
			return false;
		}
		
		// Tel
		if (!Pattern.matches("^\\d{2,3}-\\d{3,4}-\\d{4}$", strings[5])) {
			JOptionPane.showMessageDialog(null, "Tel은 '-'을 포함한 9자 이내의 숫자 형식이어야 합니다.");
			System.out.println("Tel은 '-'을 포함한 9자 이내의 숫자 형식이어야 합니다.");
			return false;
		}
		
		return true;
	}
}