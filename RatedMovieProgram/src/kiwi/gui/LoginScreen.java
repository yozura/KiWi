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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import kiwi.gui.process.LoginProcess;
import kiwi.header.Define.SCREEN_TYPE;
import kiwi.mgr.ScreenMgr;

public class LoginScreen extends JPanel {
	private static final long serialVersionUID = -8282274135637243392L;
	
	private JPanel pPaddingBox;
	private JPanel pBox;
	
	private JPasswordField pfPassword;
	private JTextField tfID;
	
	private JPanel pFlowBtns;
	private JButton btnForgotPassword;
	private JButton btnSignUp;
	
	public LoginScreen() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(12, 14, 18));
		this.setBorder(BorderFactory.createLineBorder(new Color(189, 198, 208), 1));
		
		// Logo Image Setting
		ImageIcon iconLogo = new ImageIcon("res/images/logo.png");
		Image img = iconLogo.getImage();
		Image changeImg = img.getScaledInstance(160, 120, Image.SCALE_SMOOTH);
		ImageIcon iconChangedLogo = new ImageIcon(changeImg);
		JLabel lLogoIcon = new JLabel();
		lLogoIcon.setIcon(iconChangedLogo);
		lLogoIcon.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JLabel lWelcome = new JLabel("Login to KiWi");
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
		
		JLabel lID = new JLabel("ID");
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
		
		JLabel lPassword = new JLabel("Password");
		lPassword.setPreferredSize(new Dimension(280, 25));
		lPassword.setMaximumSize(new Dimension(280, 25));
		lPassword.setForeground(new Color(189, 198, 208));
		lPassword.setFont(new Font("Arial", Font.PLAIN, 15));
		lPassword.setBorder(new EmptyBorder(15, 5, 10, 0));
		lPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lPassword.setAlignmentX(JComponent.CENTER_ALIGNMENT);
				
		pfPassword = new JPasswordField();
		// limit of letter
		PlainDocument doc = (PlainDocument)pfPassword.getDocument();
		doc.setDocumentFilter(new DocumentFilter() {
		        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		            String string =fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
		            if(string.length() <= 20)
		            super.replace(fb, offset, length, text, attrs); 
		        }
		    });
		pfPassword.setPreferredSize(new Dimension(280, 25));
		pfPassword.setMaximumSize(new Dimension(280, 25));
		pfPassword.setBackground(new Color(12, 14, 18));
		pfPassword.setForeground(new Color(189, 198, 208));
		pfPassword.setCaretColor(new Color(189, 198, 208));
		pfPassword.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		pfPassword.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		pfPassword.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					new LoginProcess().loginUser(tfID.getText(), String.valueOf(pfPassword.getPassword()), pfPassword);
				}
			}
		});
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setPreferredSize(new Dimension(280, 40));
		btnLogin.setMaximumSize(new Dimension(280, 40));
		btnLogin.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO :: ID 대조해서 있으면 접속.
				new LoginProcess().loginUser(tfID.getText(), String.valueOf(pfPassword.getPassword()), btnLogin);
			}
		});
		
		pBox.add(lID);
		pBox.add(tfID);
		pBox.add(lPassword);
		pBox.add(pfPassword);
		pBox.add(Box.createVerticalStrut(10));
		pBox.add(btnLogin);
		
		pPaddingBox.add(pBox);
		
		pFlowBtns = new JPanel();
		pFlowBtns.setLayout(new FlowLayout(FlowLayout.CENTER, 140, 10));
		pFlowBtns.setBackground(new Color(12, 14, 18));

		btnForgotPassword = new JButton("Forgot Password?");
		btnForgotPassword.setForeground(new Color(71, 143, 250));
		btnForgotPassword.setBorder(BorderFactory.createEmptyBorder());
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RenewalPasswordFrame();
			}
		});
		btnForgotPassword.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				// Brighting in focus
				Color color = btnSignUp.getForeground();
				btnForgotPassword.setForeground(color.brighter());
			}
			
			public void mouseExited(MouseEvent e) {
				// Return default color out focus
				btnForgotPassword.setForeground(new Color(71, 143, 250));
			}
		});
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.setForeground(new Color(71, 143, 250));
		btnSignUp.setBorder(BorderFactory.createEmptyBorder());
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenMgr.getInstance().changeCurScreen(SCREEN_TYPE.SIGN_UP, btnSignUp);
			}
		});
		btnSignUp.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				// Brighting in focus
				Color color = btnSignUp.getForeground();
				btnSignUp.setForeground(color.brighter());
			}
			
			public void mouseExited(MouseEvent e) {
				// Return default color out focus
				btnSignUp.setForeground(new Color(71, 143, 250));
			}
		});
		
		pFlowBtns.add(btnForgotPassword);
		pFlowBtns.add(btnSignUp);
		
		this.add(Box.createVerticalGlue());
		this.add(lLogoIcon);
		this.add(lWelcome);
		this.add(pPaddingBox);
		this.add(pFlowBtns);
		this.add(Box.createVerticalGlue());
	}
}
