package kiwi.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kiwi.dto.User;
import kiwi.gui.process.SignUpProcess;
import kiwi.header.Define.SCREEN_TYPE;
import kiwi.mgr.ScreenMgr;

public class SignUpScreen extends JPanel {
	private JPanel pBoxWelcome;
	private JPanel pBoxLabel;
	private JPanel pBoxTextField;
	private JPanel pFlowForm;
	
	private JLabel lWelcome;
	private JLabel lID;
	private JLabel lPassword;
	private JLabel lNickName;
	private JLabel lBirthDay;
	private JLabel lEmail;
	private JLabel lTel;
	
	private JTextField tfID;
	private JTextField tfPassword;
	private JTextField tfNickName;
	private JTextField tfBirthDay;
	private JTextField tfEmail;
	private JTextField tfTel;
	
	private JButton btnConfirm;
	
	private String realPassword;
	
	public SignUpScreen() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createLineBorder(new Color(189, 198, 208), 1));
		this.setBackground(new Color(12, 14, 18));
		
		lWelcome = new JLabel("Introduce yourself!");
		lWelcome.setForeground(new Color(189, 198, 208));
		lWelcome.setFont(new Font("Arial", Font.PLAIN, 30));
		lWelcome.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
		lWelcome.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		pBoxWelcome = new JPanel();
		pBoxWelcome.setLayout(new BoxLayout(pBoxWelcome, BoxLayout.Y_AXIS));
		pBoxWelcome.setBackground(new Color(12, 14, 18));
		pBoxWelcome.add(lWelcome);
		
		lID = new JLabel("ID");
		lID.setForeground(new Color(189, 198, 208));
		lID.setFont(new Font("Arial", Font.PLAIN, 15));
		lID.setToolTipText("아이디는 최소 6자 이상 20자 이내 영문, 숫자로 구성되야 합니다.");
		lID.setAlignmentX(JComponent.RIGHT_ALIGNMENT);

		lPassword = new JLabel("Password");
		lPassword.setForeground(new Color(189, 198, 208));
		lPassword.setFont(new Font("Arial", Font.PLAIN, 15));
		lPassword.setToolTipText("패스워드는 최소 6자 이상 20자 이내 하나 이상의 영문, 숫자 및 특수문자로 구성되야 합니다.");
		lPassword.setAlignmentX(JComponent.RIGHT_ALIGNMENT);

		lNickName = new JLabel("NickName");
		lNickName.setForeground(new Color(189, 198, 208));
		lNickName.setFont(new Font("Arial", Font.PLAIN, 15));
		lNickName.setToolTipText("닉네임은 최소 6자 이상 20자 이내 영문, 숫자로 구성되야 합니다.");
		lNickName.setAlignmentX(JComponent.RIGHT_ALIGNMENT);

		lBirthDay = new JLabel("BirthDay");
		lBirthDay.setForeground(new Color(189, 198, 208));
		lBirthDay.setFont(new Font("Arial", Font.PLAIN, 15));
		lBirthDay.setToolTipText("생일은 yyyy-mm-dd 형식으로 구성되야 합니다.");
		lBirthDay.setAlignmentX(JComponent.RIGHT_ALIGNMENT);

		lEmail = new JLabel("Email");
		lEmail.setForeground(new Color(189, 198, 208));
		lEmail.setFont(new Font("Arial", Font.PLAIN, 15));
		lEmail.setToolTipText("이메일은 abcde@kiwi.com 등의 형식으로 구성되야 합니다.");
		lEmail.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
		
		lTel = new JLabel("Tel.");
		lTel.setForeground(new Color(189, 198, 208));
		lTel.setFont(new Font("Arial", Font.PLAIN, 15));
		lTel.setToolTipText("전화번호는 '-'를 포함한 형식으로 구성되야 합니다.");
		lTel.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
		
		// Add Labels
		pBoxLabel = new JPanel();
		pBoxLabel.setLayout(new BoxLayout(pBoxLabel, BoxLayout.Y_AXIS));
		pBoxLabel.setBackground(new Color(18, 21, 26));
		pBoxLabel.add(Box.createVerticalStrut(10));
		pBoxLabel.add(lID);
		pBoxLabel.add(Box.createVerticalStrut(12));
		pBoxLabel.add(lPassword);
		pBoxLabel.add(Box.createVerticalStrut(12));
		pBoxLabel.add(lNickName);
		pBoxLabel.add(Box.createVerticalStrut(12));
		pBoxLabel.add(lBirthDay);
		pBoxLabel.add(Box.createVerticalStrut(12));
		pBoxLabel.add(lEmail);
		pBoxLabel.add(Box.createVerticalStrut(12));
		pBoxLabel.add(lTel);
		pBoxLabel.add(Box.createVerticalStrut(10));
		
		tfID = new JTextField();
		tfID.setPreferredSize(new Dimension(280, 25));
		tfID.setMaximumSize(new Dimension(280, 25));
		tfID.setBackground(new Color(12, 14, 18));
		tfID.setForeground(new Color(189, 198, 208));
		tfID.setCaretColor(new Color(189, 198, 209));
		tfID.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		tfID.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField tfSrc = (JTextField)e.getSource();
				if (tfSrc.getText().length() >= 20) e.consume();
			}
		});
		
		tfPassword = new JTextField();
		tfPassword.setPreferredSize(new Dimension(280, 25));
		tfPassword.setMaximumSize(new Dimension(280, 25));
		tfPassword.setBackground(new Color(12, 14, 18));
		tfPassword.setForeground(new Color(189, 198, 208));
		tfPassword.setCaretColor(new Color(189, 198, 209));
		tfPassword.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		tfPassword.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField tfSrc = (JTextField)e.getSource();
				if (tfSrc.getText().length() >= 20) e.consume();
				
				// TODO :: 입력받은 값 따로 저장하고 출력 글자는 숨기기
				
			}
		});

		tfNickName = new JTextField();
		tfNickName.setPreferredSize(new Dimension(280, 25));
		tfNickName.setMaximumSize(new Dimension(280, 25));
		tfNickName.setBackground(new Color(12, 14, 18));
		tfNickName.setForeground(new Color(189, 198, 208));
		tfNickName.setCaretColor(new Color(189, 198, 209));
		tfNickName.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		tfNickName.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField tfSrc = (JTextField)e.getSource();
				if (tfSrc.getText().length() >= 20) e.consume();
			}
		});
		
		tfBirthDay = new JTextField();
		tfBirthDay.setPreferredSize(new Dimension(280, 25));
		tfBirthDay.setMaximumSize(new Dimension(280, 25));
		tfBirthDay.setBackground(new Color(12, 14, 18));
		tfBirthDay.setForeground(new Color(189, 198, 208));
		tfBirthDay.setCaretColor(new Color(189, 198, 209));
		tfBirthDay.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		tfBirthDay.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField tfSrc = (JTextField)e.getSource();
				if (tfSrc.getText().length() >= 10) e.consume();
			}
		});
		
		tfEmail = new JTextField();
		tfEmail.setPreferredSize(new Dimension(280, 25));
		tfEmail.setMaximumSize(new Dimension(280, 25));
		tfEmail.setBackground(new Color(12, 14, 18));
		tfEmail.setForeground(new Color(189, 198, 208));
		tfEmail.setCaretColor(new Color(189, 198, 209));
		tfEmail.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		tfEmail.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField tfSrc = (JTextField)e.getSource();
				if (tfSrc.getText().length() >= 45) e.consume();
			}
		});
		
		tfTel = new JTextField();
		tfTel.setPreferredSize(new Dimension(280, 25));
		tfTel.setMaximumSize(new Dimension(280, 25));
		tfTel.setBackground(new Color(12, 14, 18));
		tfTel.setForeground(new Color(189, 198, 208));
		tfTel.setCaretColor(new Color(189, 198, 209));
		tfTel.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		tfTel.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField tfSrc = (JTextField)e.getSource();
				if (tfSrc.getText().length() >= 20) e.consume();
			}
		});
		
		// Add TextFields
		pBoxTextField = new JPanel();
		pBoxTextField.setLayout(new BoxLayout(pBoxTextField, BoxLayout.Y_AXIS));
		pBoxTextField.setBackground(new Color(18, 21, 26));
		pBoxTextField.add(Box.createVerticalStrut(10));
		pBoxTextField.add(tfID);
		pBoxTextField.add(Box.createVerticalStrut(5));
		pBoxTextField.add(tfPassword);
		pBoxTextField.add(Box.createVerticalStrut(5));
		pBoxTextField.add(tfNickName);
		pBoxTextField.add(Box.createVerticalStrut(5));
		pBoxTextField.add(tfBirthDay);
		pBoxTextField.add(Box.createVerticalStrut(5));
		pBoxTextField.add(tfEmail);
		pBoxTextField.add(Box.createVerticalStrut(5));
		pBoxTextField.add(tfTel);
		pBoxTextField.add(Box.createVerticalStrut(10));

		// Confirm Button
		btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Arial", Font.PLAIN, 15));
		btnConfirm.setPreferredSize(new Dimension(350, 40));
		btnConfirm.setMaximumSize(new Dimension(350, 40));
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpProcess sup = new SignUpProcess();
				String id = tfID.getText();
				String password = tfPassword.getText();
				String nickname = tfNickName.getText();
				String originBirth = tfBirthDay.getText();
				String email = tfEmail.getText();
				String tel = tfTel.getText();
				
				boolean isGood = sup.checkValidationUserInfo(
						sup.checkDuplicateId(id)
						, new String[] { id, password, nickname, originBirth, email, tel });
			
				if (isGood) {
					Date birthDate = Date.valueOf(originBirth);			
					User newbie = new User(id, password, nickname, birthDate, email, tel);
					sup.createAccount(newbie);
					
					ScreenMgr.getInstance().changeCurrentScreen(SCREEN_TYPE.HOME);
					MainFrame rootFrame = (MainFrame)btnConfirm.getTopLevelAncestor();
					rootFrame.revalidateScreen(true);
				}
			}
		});
		
		// Merge Boxes
		pFlowForm = new JPanel();
		pFlowForm.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
		pFlowForm.setPreferredSize(new Dimension(420, 250));
		pFlowForm.setMaximumSize(new Dimension(420, 250));
		pFlowForm.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		pFlowForm.setBackground(new Color(18, 21, 26));
		pFlowForm.add(pBoxLabel);
		pFlowForm.add(pBoxTextField);
		pFlowForm.add(btnConfirm);
		
		this.add(Box.createVerticalGlue());
		this.add(pBoxWelcome);
		this.add(pFlowForm);
		this.add(Box.createVerticalGlue());
	}
}