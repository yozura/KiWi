package kiwi.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import kiwi.mgr.MovieMgr;
import kiwi.mgr.ReviewMgr;
import kiwi.mgr.ScreenMgr;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = -5319002609205461424L;

	private Point FRAME_SIZE = new Point(1280, 800);
	
	private JPanel pMainScreen = new JPanel(new BorderLayout());

	private Container con;
	
	public MainFrame() {
		this.setTitle("KiWi");
		this.setLayout(new BorderLayout(0, 0));
		
		// 전체 리뷰, 영화 미리 로딩
		load();
		
		con = this.getContentPane();
		pMainScreen.add(ScreenMgr.getInstance().getCurScreen(), BorderLayout.CENTER);
		
		con.add(pMainScreen, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_SIZE.x, FRAME_SIZE.y);
		
		Image icon = Toolkit.getDefaultToolkit().getImage("res/images/fresh.png");
		this.setIconImage(icon);
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
	
	public void load() {
		MovieMgr.getInstance().reloadMovie();
		ReviewMgr.getInstance().reloadReview();
	}
}