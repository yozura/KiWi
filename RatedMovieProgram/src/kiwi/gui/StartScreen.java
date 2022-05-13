package kiwi.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartScreen extends JPanel {
	// 텍스트필드 2개 id pw
	// 이미지 넣을 라벨 1개
	// 버튼 3개 login, find, new
	BufferedImage background;
	JLabel lLogo;
	
	public StartScreen() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Background Image Setting
		try {
			background = ImageIO.read(new File("res/images/start_background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Logo Image Setting
		ImageIcon iconLogo = new ImageIcon("res/images/logo.png");
		Image img = iconLogo.getImage();
		Image changeImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon iconChangedLogo = new ImageIcon(changeImg);
		lLogo = new JLabel();
		lLogo.setIcon(iconChangedLogo);
		
		this.add(lLogo);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// 백그라운드 그리기
		g.drawImage(background, 0, 0, null);
	}
}
