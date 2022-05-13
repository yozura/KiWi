package kiwi.gui;

import java.awt.*;

import javax.swing.*;

// 유저 인터페이스의 공통적 부분을 총괄함.
public class MainFrame extends JFrame {
	private Point FRAME_SIZE = new Point(1280, 800);
	
	public MainFrame() {
		this.setTitle("KiWi");
		this.setLayout(new BorderLayout(0, 0));
		Container con = this.getContentPane();
		
		JPanel pWestBar = new JPanel();
		pWestBar.setLayout(new BoxLayout(pWestBar, BoxLayout.PAGE_AXIS));
		pWestBar.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
		for (int i = 0; i < 4; ++i) {
			JButton btn = new JButton("btn" + i);
			btn.setPreferredSize(new Dimension(100, 100));
			btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
			pWestBar.add(btn);
		}
		
		JPanel pStart = new StartScreen();
		
		con.add(pWestBar, BorderLayout.WEST);
		con.add(pStart, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_SIZE.x, FRAME_SIZE.y);
		this.setResizable(false);
		this.setVisible(true);
	}
}