package kiwi.gui;

import java.awt.*;

import javax.swing.*;

import kiwi.mgr.ScreenMgr;

public class MainFrame extends JFrame {
	private Point FRAME_SIZE = new Point(1280, 800);
	
	private Container con;
	
	private JPanel pMainScreen = new JPanel(new BorderLayout());
	
	public MainFrame() {
		this.setTitle("KiWi");
		this.setLayout(new BorderLayout(0, 0));
		
		con = this.getContentPane();
		pMainScreen.add(ScreenMgr.getInstance().getCurScreen(), BorderLayout.CENTER);
		
		con.add(ScreenMgr.getInstance().getSideBar(), BorderLayout.WEST);
		con.add(pMainScreen, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_SIZE.x, FRAME_SIZE.y);
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