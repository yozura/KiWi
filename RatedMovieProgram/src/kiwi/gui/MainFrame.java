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
		pWestBar.setLayout(new BoxLayout(pWestBar, BoxLayout.Y_AXIS));
		pWestBar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
		pWestBar.setBackground(new Color(190, 148, 228));
		for (int i = 0; i < 4; ++i) {
			JButton btn = new JButton();
			btn.setPreferredSize(new Dimension(75, 75));
			btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
			ImageIcon iconBtn;
			switch (i) {
			case 0 -> iconBtn = new ImageIcon("res/icons/home.png");
			case 1 -> iconBtn = new ImageIcon("res/icons/menu.png");
			case 2 -> iconBtn = new ImageIcon("res/icons/expand_more.png");
			case 3 -> iconBtn = new ImageIcon("res/icons/settings.png");
			default -> iconBtn = null;
			}
			btn.setBorder(BorderFactory.createEmptyBorder());
			btn.setIcon(iconBtn);
			pWestBar.add(btn);
		}
		
		JPanel pStart = new StartScreen();
		
		con.add(pWestBar, BorderLayout.WEST);
		con.add(pStart, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_SIZE.x, FRAME_SIZE.y);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
}