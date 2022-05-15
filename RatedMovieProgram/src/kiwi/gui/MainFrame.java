package kiwi.gui;

import java.awt.*;

import javax.swing.*;

// 유저 인터페이스의 공통적 부분을 총괄함.
public class MainFrame extends JFrame {
	private Point FRAME_SIZE = new Point(1280, 800);
	private JPanel pSideBar = new SideBar();
	
	public MainFrame() {
		this.setTitle("KiWi");
		this.setLayout(new BorderLayout(0, 0));
		Container con = this.getContentPane();
		
		JPanel pStart = new SignUpScreen();
		
		con.add(pSideBar, BorderLayout.WEST);
		con.add(pStart, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_SIZE.x, FRAME_SIZE.y);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
}