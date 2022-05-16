package kiwi.gui.process;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import kiwi.dao.UserDAO;
import kiwi.dto.User;

public class SignUpProcess {
	public void createAccount(User user) {
		// 새로운 계정 생성하기
		UserDAO dao = new UserDAO();
		dao.insert(user);
	}
	
	public boolean checkDuplicateId(String id) {
		return new UserDAO().findDuplicateById(id);
	}
	
	public boolean checkValidationUserInfo(boolean isDuplicated, String...strings) {
		if (isDuplicated) {
			JOptionPane.showMessageDialog(null, "이미 있는 ID입니다.", "아이디 중복 메시지", JOptionPane.PLAIN_MESSAGE);
			System.out.println("이미 있는 ID입니다.");
			return false;
		}
		
		if (!Pattern.matches("^[a-zA-Z0-9]{6,20}$", strings[0])) {
			JOptionPane.showMessageDialog(null, "ID는 최소 6자 이상 20자 이내 영문, 숫자로 구성되어야 합니다.");
			System.out.println("ID는 최소 6자 이상 20자 이내 영문, 숫자로 구성되야 합니다.");
			return false;
		}

		if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{10,20}$", strings[1])) {
			JOptionPane.showMessageDialog(null, "Password는 최소 10자 이상 20자 이내 하나 이상의 영문, 숫자 및 특수문자로 구성되야 합니다.");
			System.out.println("Password는 최소 10자 이상 20자 이내 하나 이상의 영문, 숫자 및 특수문자로 구성되야 합니다.");
			return false;
		}
		
		if (!Pattern.matches("^[a-zA-Z0-9가-힣]{2,20}$", strings[2])) {
			// alert
			System.out.println("Nickname은 최소 2자 이상 20자 이내 영문, 숫자, 한글로 구성되야 합니다.");
			return false;
		}
		
		if (!Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", strings[3])) {
			// alert
			System.out.println("Birthdate는 yyyy-mm-dd의 형식으로 구성되어야 합니다.");
			return false;
		}
		
		if (!Pattern.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", strings[4])) {
			// alert
			System.out.println("Email을 올바르게 입력해주세요.");
			return false;
		}
		
		if (!Pattern.matches("^\\d{2,3}-\\d{3,4}-\\d{4}$", strings[5])) {
			// alert
			System.out.println("Tel은 '-'을 포함한 9자 이내의 숫자 형식이어야 합니다.");
			return false;
		}
		
		return true;
	}
}