package kiwi.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class HomeScreen extends JPanel {
	private static final long serialVersionUID = -6747425612297717735L;

	private JLabel lLogoIcon;
	
	private JTextField tfSearch;
	
	private JLabel lDonate;
	
	public HomeScreen() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(12, 14, 18));
		this.setBorder(BorderFactory.createLineBorder(new Color(189, 198, 208), 1));
		
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
			public void keyTyped(KeyEvent e) {
				JTextField tf = (JTextField)e.getSource();
				String str = tf.getText();
				if (str.length() == 0) {
					return;
				}
				
				// TODO :: 검색..
				
			}
		});
		
		lDonate = new JLabel("<html><hr>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Please Donate for developer!<br>Account : TossBank 1000-1133-5282</html>");
		lDonate.setForeground(new Color(189, 198, 208));
		lDonate.setHorizontalAlignment(SwingConstants.CENTER);
		lDonate.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		this.add(Box.createVerticalGlue());
		this.add(lLogoIcon);
		this.add(tfSearch);
		this.add(lDonate);
		this.add(Box.createVerticalGlue());
	}
}