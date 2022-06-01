package kiwi.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kiwi.gui.process.HomeProcess;
import kiwi.header.Define.SCREEN_TYPE;
import kiwi.header.Pair;
import kiwi.mgr.MovieMgr;
import kiwi.mgr.ResourceMgr;
import kiwi.mgr.ScreenMgr;

public class HomeScreen extends JPanel {
	private static final long serialVersionUID = -6747425612297717735L;

	public HomeScreen() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(12, 14, 18));
		this.setBorder(BorderFactory.createLineBorder(new Color(189, 198, 208), 1));
		
		JPanel pBoxSearch = new JPanel();
		pBoxSearch.setLayout(new BoxLayout(pBoxSearch, BoxLayout.Y_AXIS));
		pBoxSearch.setBackground(new Color(189, 198, 208));
		pBoxSearch.setVisible(false);
		
		ImageIcon logoIcon = ResourceMgr.getInstance().resizeImageIcon("res/images/logo.png", 160, 120);
		JLabel lLogoIcon = new JLabel();
		lLogoIcon.setIcon(logoIcon);
		lLogoIcon.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JTextField tfSearch = new JTextField();
		tfSearch.setPreferredSize(new Dimension(280, 30));
		tfSearch.setMaximumSize(new Dimension(280, 30));
		tfSearch.setBackground(new Color(189, 198, 208));
		tfSearch.setForeground(new Color(12, 14, 18));
		tfSearch.setFont(new Font("Arial", Font.PLAIN, 15));
		tfSearch.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		tfSearch.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tfSearch.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				pBoxSearch.removeAll();
				pBoxSearch.setVisible(false);
			}
			
			// 영화 타이틀 입력시 이동
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					HomeProcess hp = new HomeProcess();
					JTextField tf = (JTextField)e.getSource();
					String str = tf.getText();
					
					hp.moveToMovieScreen(str, tf);
				}
			}
			
			// 추천 검색어 보여주기
			public void keyReleased(KeyEvent e) {
				HomeProcess hp = new HomeProcess();
				JTextField tf = (JTextField)e.getSource();
				String str = tf.getText();
				
				Vector<Pair<String, Integer>> vecSearch = hp.searchMovie(str);
				if (vecSearch == null || !(str.length() > 0)) {
					pBoxSearch.setVisible(false);
					return;
				}
				
				for (Pair<String, Integer> pair : vecSearch) {
					JButton btnSearch = new JButton(pair.first);
					btnSearch.setBorder(BorderFactory.createEmptyBorder());
					btnSearch.setAlignmentX(JComponent.CENTER_ALIGNMENT);
					btnSearch.setForeground(new Color(12, 14, 18));
					btnSearch.setBackground(new Color(189, 198, 208));
					btnSearch.setOpaque(true);
					btnSearch.setFont(new Font("Arial", Font.PLAIN, 15));
					btnSearch.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							MovieMgr.getInstance().setCurMovie(pair.second);
							ScreenMgr.getInstance().changeCurScreenWithBar(SCREEN_TYPE.MOVIE, btnSearch);
						}
					});
					btnSearch.addMouseListener(new MouseAdapter() {
						public void mouseEntered(MouseEvent e) {
							btnSearch.setFont(new Font("Arial", Font.BOLD, 15));
						}
						
						public void mouseExited(MouseEvent e) {
							btnSearch.setFont(new Font("Arial", Font.PLAIN, 15));
						}
					});
					
					pBoxSearch.add(btnSearch);
				}
				
				pBoxSearch.setVisible(true);
			}
		});
		
		JLabel lDonate = new JLabel("<html><hr>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Please Donate for developer!<br>Account : TossBank 1000-1133-5282</html>");
		lDonate.setForeground(new Color(189, 198, 208));
		lDonate.setHorizontalAlignment(SwingConstants.CENTER);
		lDonate.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		this.add(Box.createVerticalGlue());
		this.add(lLogoIcon);
		this.add(tfSearch);
		this.add(pBoxSearch);
		this.add(lDonate);
		this.add(Box.createVerticalGlue());
	}
}