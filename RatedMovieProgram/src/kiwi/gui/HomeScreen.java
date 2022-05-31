package kiwi.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kiwi.gui.process.HomeProcess;
import kiwi.header.Pair;
import kiwi.header.Define.SCREEN_TYPE;
import kiwi.mgr.MovieMgr;
import kiwi.mgr.ScreenMgr;
import kiwi.mgr.UserMgr;

public class HomeScreen extends JPanel {
	private static final long serialVersionUID = -6747425612297717735L;

	private JPanel pBoxSearch;
	private JLabel lLogoIcon;
	private JTextField tfSearch;
	private JLabel lDonate;
	
	public HomeScreen() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(12, 14, 18));
		this.setBorder(BorderFactory.createLineBorder(new Color(189, 198, 208), 1));
		
		pBoxSearch = new JPanel();
		pBoxSearch.setLayout(new BoxLayout(pBoxSearch, BoxLayout.Y_AXIS));
		pBoxSearch.setBackground(new Color(12, 14, 18));
		pBoxSearch.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		pBoxSearch.setVisible(false);
		
		ImageIcon iconLogo = new ImageIcon("res/images/logo.png");
		Image img = iconLogo.getImage();
		Image changeImg = img.getScaledInstance(160, 120, Image.SCALE_SMOOTH);
		ImageIcon iconChangedLogo = new ImageIcon(changeImg);
		lLogoIcon = new JLabel();
		lLogoIcon.setIcon(iconChangedLogo);
		lLogoIcon.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		tfSearch = new JTextField();
		tfSearch.setPreferredSize(new Dimension(280, 30));
		tfSearch.setMaximumSize(new Dimension(280, 30));
		tfSearch.setBackground(new Color(189, 198, 208));
		tfSearch.setForeground(new Color(12, 14, 18));
		tfSearch.setFont(new Font("Arial", Font.PLAIN, 15));
		tfSearch.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		tfSearch.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tfSearch.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					HomeProcess hp = new HomeProcess();
					JTextField tf = (JTextField)e.getSource();
					String str = tf.getText();
					
					hp.moveToMovieScreen(str, tf);
				}
			}
			
			public void keyReleased(KeyEvent e) {
				HomeProcess hp = new HomeProcess();
				JTextField tf = (JTextField)e.getSource();
				String str = tf.getText();
				pBoxSearch.removeAll();
				
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
					btnSearch.setOpaque(true);
					btnSearch.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							MovieMgr.getInstance().setCurMovie(pair.second);
							ScreenMgr.getInstance().changeCurScreenWithBar(SCREEN_TYPE.MOVIE, btnSearch);
						}
					});
					
					pBoxSearch.add(btnSearch);
				}
				
				pBoxSearch.setVisible(true);
			}
		});
		
		lDonate = new JLabel("<html><hr>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Please Donate for developer!<br>Account : TossBank 1000-1133-5282</html>");
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