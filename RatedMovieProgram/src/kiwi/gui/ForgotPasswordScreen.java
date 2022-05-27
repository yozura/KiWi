package kiwi.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import kiwi.gui.process.ForgotPasswordProcess;

public class ForgotPasswordScreen extends JPanel {
	private static final long serialVersionUID = -4978154617607159571L;

	private JLabel lWelcome;
	
	private JTextField tfEmail;
	
	private JLabel lPassword;
	private JPasswordField pfPassword;
	
	public ForgotPasswordScreen() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createLineBorder(new Color(189, 198, 208), 1));
		this.setBackground(new Color(12, 14, 18));
		
		lWelcome = new JLabel("Please enter your E-mail!");
		lWelcome.setForeground(new Color(189, 198, 208));
		lWelcome.setFont(new Font("Arial", Font.PLAIN, 30));
		lWelcome.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		lWelcome.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		tfEmail = new JTextField();
		tfEmail.setPreferredSize(new Dimension(280, 30));
		tfEmail.setMaximumSize(new Dimension(280, 30));
		tfEmail.setBackground(new Color(189, 198, 208));
		tfEmail.setForeground(new Color(12, 14, 18));
		tfEmail.setFont(new Font("Arial", Font.PLAIN, 15));
		tfEmail.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		tfEmail.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tfEmail.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// TODO :: email 유효성 검사 + 이메일이 있으면 새로 만들 패스워드 창 열어주기.
					ForgotPasswordProcess fpp = new ForgotPasswordProcess();
					boolean isGood = fpp.checkValidationEmail(tfEmail.getText())
									 && fpp.checkExistByEmail(tfEmail.getText());
					if (isGood) {
						lPassword.setVisible(true);
						pfPassword.setVisible(true);
						tfEmail.setEnabled(false);
					}
				}
			}
		});
		
		lPassword = new JLabel("<html><hr>Enter your new Password!</html>");
		lPassword.setForeground(new Color(189, 198, 208));
		lPassword.setFont(new Font("Arial", Font.PLAIN, 30));
		lPassword.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		lPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lPassword.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		lPassword.setVisible(false);
		
		pfPassword = new JPasswordField();
		// limit of letter
		PlainDocument doc = (PlainDocument)pfPassword.getDocument();
		doc.setDocumentFilter(new DocumentFilter() {
		        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		        	String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
		            if(string.length() <= 20)
		            super.replace(fb, offset, length, text, attrs); 
		        }
		});
		pfPassword.setPreferredSize(new Dimension(280, 30));
		pfPassword.setMaximumSize(new Dimension(280, 30));
		pfPassword.setBackground(new Color(189, 198, 208));
		pfPassword.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		pfPassword.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// TODO :: 패스워드 유효성 검사 + 디비에 패스워드 restore
					ForgotPasswordProcess fpp = new ForgotPasswordProcess();
					boolean isGood = fpp.checkValidationPassword(String.valueOf(pfPassword.getPassword()));
					if (isGood) {
						fpp.changePassword(tfEmail.getText(), String.valueOf(pfPassword.getPassword()), pfPassword);
					}
				}
			}
		});
		pfPassword.setVisible(false);
		
		this.add(Box.createVerticalGlue());
		this.add(lWelcome);
		this.add(tfEmail);
		this.add(lPassword);
		this.add(pfPassword);
		this.add(Box.createVerticalGlue());
	}
}