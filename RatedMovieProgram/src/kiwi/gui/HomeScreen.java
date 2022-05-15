package kiwi.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeScreen extends JPanel {
	// 로그인 했을 경우엔 계정 정보
	// 비로그인 시에는 로그인 화면
	public HomeScreen() {
		this.add(new JLabel("Hello? this is home screen"));
	}
}