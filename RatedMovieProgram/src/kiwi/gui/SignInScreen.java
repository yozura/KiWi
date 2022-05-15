package kiwi.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class SignInScreen extends JPanel {
	// Login Screen blueprint
	// 라벨 흰색, 버튼 녹색, 배경 검은색
	// ------------------------
	// 				로고 이미지(라벨)
	//   		Sign in to KiWi(라벨)
	// ===================================(패널 보더)
	// = Your ID(라벨)				     =
	// =[   		ID입력(텍스트필드)		]=
	// = Your Password(라벨)				 =
	// =[			Password입력(텍스트필드)]=
	// ={			(로그인버튼)			}=
	// ===================================
	// {아이디 찾기} 	{비밀번호 찾기}   {회원가입}
	
	// Color Table
	// Background = new Color(12, 14, 18)
	// Panel = new Color(18, 21, 26)
	// Text = new Color(189, 198, 208)
	
	private JLabel lLogoImage;
	private JLabel lWelcome;
	
	private JPanel pPaddingBox;
	private JPanel pBox;
	private JLabel lID;
	private JTextField tfID;
	private JLabel lPassword;
	private JTextField tfPassword;
	private JButton btnLogin;
	
	private JPanel pFlowBtns;
	private JButton btnFindPassword;
	private JButton btnSignUp;
	
	public SignInScreen() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
		this.setBackground(new Color(12, 14, 18));
		
		// Logo Image Setting
		ImageIcon iconLogo = new ImageIcon("res/images/login_logo.png");
		Image img = iconLogo.getImage();
		Image changeImg = img.getScaledInstance(80, 60, Image.SCALE_SMOOTH);
		ImageIcon iconChangedLogo = new ImageIcon(changeImg);
		lLogoImage = new JLabel();
		lLogoImage.setIcon(iconChangedLogo);
		lLogoImage.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		lWelcome = new JLabel("Sign in to KiWi");
		lWelcome.setBorder(new EmptyBorder(15, 0, 15, 0));
		lWelcome.setForeground(new Color(189, 198, 208));
		lWelcome.setFont(new Font("Arial", Font.PLAIN, 20));
		lWelcome.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		pPaddingBox = new JPanel();
		pPaddingBox.setLayout(new BoxLayout(pPaddingBox, BoxLayout.Y_AXIS));
		pPaddingBox.setPreferredSize(new Dimension(300, 160));
		pPaddingBox.setMaximumSize(new Dimension(300, 160));
		pPaddingBox.setBackground(new Color(18, 21, 26));
		pPaddingBox.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		
		pBox = new JPanel();
		pBox.setLayout(new BoxLayout(pBox, BoxLayout.Y_AXIS));
		pBox.setPreferredSize(new Dimension(280, 150));
		pBox.setBackground(new Color(18, 21, 26));
		
		lID = new JLabel("ID");
		lID.setPreferredSize(new Dimension(280, 25));
		lID.setMaximumSize(new Dimension(280, 25));
		lID.setForeground(new Color(189, 198, 208));
		lID.setFont(new Font("Arial", Font.PLAIN, 15));
		lID.setBorder(new EmptyBorder(15, 5, 10, 0));
		lID.setHorizontalAlignment(SwingConstants.LEFT);
		lID.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		tfID = new JTextField();
		tfID.setPreferredSize(new Dimension(280, 25));
		tfID.setMaximumSize(new Dimension(280, 25));
		tfID.setBackground(new Color(12, 14, 18));
		tfID.setForeground(new Color(189, 198, 208));
		tfID.setCaretColor(new Color(189, 198, 208));
		tfID.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		tfID.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField tfSrc = (JTextField)e.getSource();
				if (tfSrc.getText().length() >= 20) e.consume();
			}
		});
		tfID.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		lPassword = new JLabel("Password");
		lPassword.setPreferredSize(new Dimension(280, 25));
		lPassword.setMaximumSize(new Dimension(280, 25));
		lPassword.setForeground(new Color(189, 198, 208));
		lPassword.setFont(new Font("Arial", Font.PLAIN, 15));
		lPassword.setBorder(new EmptyBorder(15, 5, 10, 0));
		lPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lPassword.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		tfPassword = new JTextField();
		tfPassword.setPreferredSize(new Dimension(280, 25));
		tfPassword.setMaximumSize(new Dimension(280, 25));
		tfPassword.setBackground(new Color(12, 14, 18));
		tfPassword.setForeground(new Color(189, 198, 208));
		tfPassword.setCaretColor(new Color(189, 198, 208));
		tfPassword.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		tfPassword.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField tfSrc = (JTextField)e.getSource();
				if (tfSrc.getText().length() >= 20) e.consume();
				
				// TODO :: 입력받은 값 따로 저장하고 출력 글자는 숨기기
			}
		});
		tfPassword.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JLabel lBlank = new JLabel();
		lBlank.setPreferredSize(new Dimension(280, 10));
		lBlank.setMaximumSize(new Dimension(280, 10));
		lBlank.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		btnLogin = new JButton("Sign in");
		btnLogin.setPreferredSize(new Dimension(280, 40));
		btnLogin.setMaximumSize(new Dimension(280, 40));
		btnLogin.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		pBox.add(lID);
		pBox.add(tfID);
		pBox.add(lPassword);
		pBox.add(tfPassword);
		pBox.add(lBlank);
		pBox.add(btnLogin);
		
		pPaddingBox.add(pBox);
		
		pFlowBtns = new JPanel();
		pFlowBtns.setLayout(new FlowLayout(FlowLayout.CENTER, 140, 10));
		pFlowBtns.setBackground(new Color(12, 14, 18));

		btnFindPassword = new JButton("Forgot Password?");
		btnFindPassword.setForeground(new Color(71, 143, 250));
		btnFindPassword.setBorder(BorderFactory.createEmptyBorder());
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.setForeground(new Color(71, 143, 250));
		btnSignUp.setBorder(BorderFactory.createEmptyBorder());
		
		pFlowBtns.add(btnFindPassword);
		pFlowBtns.add(btnSignUp);
		
		this.add(Box.createVerticalGlue());
		this.add(lLogoImage);
		this.add(lWelcome);
		this.add(pPaddingBox);
		this.add(pFlowBtns);
		this.add(Box.createVerticalGlue());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// 백그라운드 그리기
		// g.drawImage(background, 0, 0, this);
	}
}
