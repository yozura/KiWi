package kiwi.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import kiwi.header.Define.SCREEN_TYPE;
import kiwi.mgr.ScreenMgr;

import kiwi.gui.process.LoginProcess;

public class LoginScreen extends JPanel {
	private JLabel lLogoIcon;
	private JLabel lWelcome;
	
	private JPanel pPaddingBox;
	private JPanel pBox;
	private JLabel lID;
	private JLabel lPassword;
	
	private JButton btnLogin;

	private JTextField tfPassword;
	private JTextField tfID;
	
	private JPanel pFlowBtns;
	private JButton btnFindPassword;
	private JButton btnSignUp;
	
	private String realPassword;
	
	public LoginScreen() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createLineBorder(new Color(189, 198, 208), 1));
		this.setBackground(new Color(12, 14, 18));
		
		// Logo Image Setting
		ImageIcon iconLogo = new ImageIcon("res/images/logo.png");
		Image img = iconLogo.getImage();
		Image changeImg = img.getScaledInstance(160, 120, Image.SCALE_SMOOTH);
		ImageIcon iconChangedLogo = new ImageIcon(changeImg);
		lLogoIcon = new JLabel();
		lLogoIcon.setIcon(iconChangedLogo);
		lLogoIcon.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		lWelcome = new JLabel("Login to KiWi");
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
			public void keyPressed(KeyEvent e) {
				// TODO :: 입력받은 값 따로 저장하고 출력 글자는 숨기기
				// 입력 받고나서 여분의 스트링에 넣고 tfpassword는 *로 치환
				JTextField tfSrc = (JTextField)e.getSource();
				if (tfSrc.getText().length() >= 20) {
					e.consume();
					return;
				}
				
				if (tfSrc.getText().length() == 0) {
					realPassword = "";
					System.out.println(realPassword);
					return;
				}
			
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					realPassword = realPassword.substring(0, realPassword.length() - 1);
					System.out.println(realPassword);
				} else {
					realPassword +=	e.getKeyChar();
					System.out.println(realPassword);
				}
			}
		});
		
		tfPassword.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		btnLogin = new JButton("Login");
		btnLogin.setPreferredSize(new Dimension(280, 40));
		btnLogin.setMaximumSize(new Dimension(280, 40));
		btnLogin.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO :: ID 대조해서 있으면 접속.
				LoginProcess lp = new LoginProcess();
				lp.loginUser(tfID.getText(), tfPassword.getText(), btnLogin);
				
			}
		});
		
		pBox.add(lID);
		pBox.add(tfID);
		pBox.add(lPassword);
		pBox.add(tfPassword);
		pBox.add(Box.createVerticalStrut(10));
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
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenMgr.getInstance().changeCurrentScreen(SCREEN_TYPE.SIGN_UP, false, btnSignUp);
			}
		});
		
		pFlowBtns.add(btnFindPassword);
		pFlowBtns.add(btnSignUp);
		
		this.add(Box.createVerticalGlue());
		this.add(lLogoIcon);
		this.add(lWelcome);
		this.add(pPaddingBox);
		this.add(pFlowBtns);
		this.add(Box.createVerticalGlue());
	}
}
