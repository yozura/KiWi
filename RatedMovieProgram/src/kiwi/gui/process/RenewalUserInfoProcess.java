package kiwi.gui.process;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import kiwi.dao.UserDAO;
import kiwi.mgr.UserMgr;

public class RenewalUserInfoProcess {
	public void changeNickname(String nickname) {
		UserDAO uDAO = new UserDAO();
		if (uDAO.updateNickname(nickname, UserMgr.getInstance().getCurUser().getId())) {
			JOptionPane.showMessageDialog(null, "닉네임이 성공적으로 변경되었습니다.");
		} else {
			JOptionPane.showMessageDialog(null, "닉네임 변경에 실패했습니다.");
		}
	}
	
	public void changeEmail(String email) {
		UserDAO uDAO = new UserDAO();
		if (uDAO.updateEmail(email, UserMgr.getInstance().getCurUser().getId())) {
			JOptionPane.showMessageDialog(null, "이메일이 성공적으로 변경되었습니다.");
		} else {
			JOptionPane.showMessageDialog(null, "이메일 변경에 실패했습니다.");
		}
	}
	
	public void changeTel(String tel) {
		UserDAO uDAO = new UserDAO();
		if (uDAO.updateTel(tel, UserMgr.getInstance().getCurUser().getId())) {
			JOptionPane.showMessageDialog(null, "휴대폰 번호가 성공적으로 변경되었습니다.");
		} else {
			JOptionPane.showMessageDialog(null, "휴대폰 번호 변경에 실패했습니다.");
		}
	}
	
	public boolean checkValidationNickname(String nickname) {
		// Nickname
		if (!Pattern.matches("^[a-zA-Z0-9가-힣]{2,20}$", nickname)) {
			JOptionPane.showMessageDialog(null, "Nickname은 최소 2자 이상 20자 이내 영문, 숫자, 한글로 구성되야 합니다.");
			System.out.println("Nickname은 최소 2자 이상 20자 이내 영문, 숫자, 한글로 구성되야 합니다.");
			return false;
		}
		
		return true;
	}
	
	public boolean checkValidationEmail(String email) {
		// Email
		if (!Pattern.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", email)) {
			JOptionPane.showMessageDialog(null, "Email을 올바르게 입력해주세요.");
			System.out.println("Email을 올바르게 입력해주세요.");
			return false;
		}
				
		return true;		
	}
	
	public boolean checkValidationTel(String tel) {
		// Tel
		if (!Pattern.matches("^\\d{2,3}-\\d{3,4}-\\d{4}$", tel)) {
			JOptionPane.showMessageDialog(null, "Tel은 '-'을 포함한 9자 이내의 숫자 형식이어야 합니다.");
			System.out.println("Tel은 '-'을 포함한 9자 이내의 숫자 형식이어야 합니다.");
			return false;
		}	
		
		return true;
	}
}