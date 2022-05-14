package kiwi.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StartScreen extends JPanel {
	// 텍스트필드 2개 id pw
	// 이미지 넣을 라벨 1개
	// 버튼 3개 login, find, new
	BufferedImage background;
	JLabel lLogo;
	JButton btn; 
	
	public StartScreen() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
		
		// Background Image Setting
		try {
			background = ImageIO.read(new File("res/images/start_background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Logo Image Setting
		ImageIcon iconLogo = new ImageIcon("res/images/logo.png");
		Image img = iconLogo.getImage();
		Image changeImg = img.getScaledInstance(320, 240, Image.SCALE_SMOOTH);
		ImageIcon iconChangedLogo = new ImageIcon(changeImg);
		lLogo = new JLabel();
		lLogo.setIcon(iconChangedLogo);
		lLogo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		btn = new JButton("HI");
		btn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btn.setPreferredSize(new Dimension(100, 100));
		btn.setMaximumSize(new Dimension(100, 100));
		
		this.add(Box.createVerticalGlue());
		this.add(lLogo);
		this.add(btn);
		this.add(Box.createVerticalGlue());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// 백그라운드 그리기
		g.drawImage(background, 0, 0, this);
	}
}
