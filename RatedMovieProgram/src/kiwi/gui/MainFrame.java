package kiwi.gui;

import java.awt.*;

import javax.swing.*;

import kiwi.mgr.ScreenMgr;

public class MainFrame extends JFrame {
	private Point FRAME_SIZE = new Point(1280, 800);
	
	private JPanel pMainScreen = new JPanel(new BorderLayout());

	private Container con;
	
	public MainFrame() {
		this.setTitle("KiWi");
		this.setLayout(new BorderLayout(0, 0));
		
		con = this.getContentPane();
		pMainScreen.add(ScreenMgr.getInstance().getCurScreen(), BorderLayout.CENTER);
		
		//con.add(ScreenMgr.getInstance().getSideBar(), BorderLayout.WEST);
		con.add(pMainScreen, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_SIZE.x, FRAME_SIZE.y);
		// 아이콘 변경
		//this.setIconImage(new ImageIcon("res/icons/close.png").getImage());	
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void revalidateScreenWithSideBar() {
		pMainScreen.removeAll();
		con.removeAll();

		pMainScreen.add(ScreenMgr.getInstance().getCurScreen(), BorderLayout.CENTER);
		
		con.add(ScreenMgr.getInstance().getSideBar(), BorderLayout.WEST);
		con.add(pMainScreen, BorderLayout.CENTER);
		
		con.revalidate();
	}
	
	public void revalidateScreen() {
		pMainScreen.removeAll();
		con.removeAll();
		
		pMainScreen.add(ScreenMgr.getInstance().getCurScreen(), BorderLayout.CENTER);
		
		con.add(pMainScreen, BorderLayout.CENTER);
	
		con.revalidate();
	}
}