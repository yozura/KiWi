package kiwi.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import kiwi.gui.process.RenewalPasswordProcess;

public class RenewalPasswordFrame extends JFrame {
	private static final long serialVersionUID = -4978154617607159571L;

	private JTextField tfEmail;
	
	private JLabel lPassword;
	private JPasswordField pfPassword;
	
	private Container con;
	private JPanel pBoxScreen;
	
	public RenewalPasswordFrame() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		con = this.getContentPane();
		
		pBoxScreen = new JPanel();
		pBoxScreen.setLayout(new BoxLayout(pBoxScreen, BoxLayout.Y_AXIS));
		pBoxScreen.setBackground(new Color(12, 14, 18));
		pBoxScreen.setBorder(BorderFactory.createLineBorder(new Color(189, 198, 208), 1));
		
		JLabel lWelcome = new JLabel("이메일을 입력하세요.");
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
					RenewalPasswordProcess fpp = new RenewalPasswordProcess();
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
		
		lPassword = new JLabel("<html><hr>변경할 비밀번호를 입력하세요.</html>");
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
					RenewalPasswordProcess fpp = new RenewalPasswordProcess();
					boolean isGood = fpp.checkValidationPassword(String.valueOf(pfPassword.getPassword()));
					if (isGood) {
						fpp.changePassword(tfEmail.getText(), String.valueOf(pfPassword.getPassword()), pfPassword);
						dispose();
					}
				}
			}
		});
		pfPassword.setVisible(false);
		
		pBoxScreen.add(Box.createVerticalGlue());
		pBoxScreen.add(lWelcome);
		pBoxScreen.add(tfEmail);
		pBoxScreen.add(lPassword);
		pBoxScreen.add(pfPassword);
		pBoxScreen.add(Box.createVerticalGlue());
		
		con.add(pBoxScreen);		
		
		this.setSize(480, 640);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
}