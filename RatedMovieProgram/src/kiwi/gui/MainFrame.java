package kiwi.gui;

import java.awt.*;

import javax.swing.*;

public class MainFrame extends JFrame {
	private Point FRAME_SIZE = new Point(1280, 800);
	private Container con;
	private JPanel pSideBar = new SideBar();
	
	public MainFrame() {
		this.setTitle("KiWi");
		this.setLayout(new BorderLayout(0, 0));
		
		con = this.getContentPane();
		revalidateScreen();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_SIZE.x, FRAME_SIZE.y);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void revalidateScreen() {
		con.removeAll();
		
		con.add(pSideBar, BorderLayout.WEST);
		con.add(ScreenMgr.getInstance().getCurrentScreen(), BorderLayout.CENTER);
	
		con.revalidate();
	}
}