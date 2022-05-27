package kiwi.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class BookmarkScreen extends JPanel {
	private static final long serialVersionUID = -807717172091339569L;

	private JLabel lWelcome;
	
	private JPanel pBoxReview;
	
	public BookmarkScreen() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(12, 14, 18));
		
		lWelcome = new JLabel(String.format("%s's Favorite Movies", "Nickname"));
		lWelcome.setForeground(new Color(189, 198, 208));
		lWelcome.setFont(new Font("Arial", Font.PLAIN, 32));
		lWelcome.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		lWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lWelcome.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JSeparator hr = new JSeparator(JSeparator.HORIZONTAL);
		hr.setForeground(new Color(189, 198, 208));
		hr.setPreferredSize(new Dimension(1080, 5));
		hr.setMaximumSize(new Dimension(1080, 5));
		
		
		
		this.add(Box.createVerticalGlue());
		this.add(lWelcome);
		this.add(hr);
		this.add(Box.createVerticalGlue());
	}
}
