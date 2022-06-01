package kiwi.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kiwi.gui.process.RenewalUserInfoProcess;

public class RenewalUserInfoFrame extends JFrame {
	private static final long serialVersionUID = 2588496365029541095L;
	
	private JPanel pBoxScreen;
	private JLabel lGuide;
	
	public RenewalUserInfoFrame() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		JPanel pBoxContainer = new JPanel();
		pBoxContainer.setLayout(new BoxLayout(pBoxContainer, BoxLayout.Y_AXIS));
		pBoxContainer.setBackground(new Color(12, 14, 18));
		pBoxContainer.setBorder(BorderFactory.createLineBorder(new Color(189, 198, 208), 1));
	
		String actions[] = { "없음", "닉네임 변경", "이메일 변경", "연락처 변경" };
		JComboBox<String> cbSelect = new JComboBox<String>(actions);
		cbSelect.setBackground(new Color(189, 198, 208));
		cbSelect.setOpaque(true);
		cbSelect.setPreferredSize(new Dimension(480, 25));
		cbSelect.setMaximumSize(new Dimension(480, 25));
		cbSelect.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		cbSelect.setAlignmentY(JComponent.TOP_ALIGNMENT);
		cbSelect.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				pBoxScreen.setVisible(false);
				
				switch (e.getItem().toString()) {
				case "없음":
					lGuide.setText("원하는 행동을 선택해주세요.");
					pBoxScreen.removeAll();
					
					pBoxScreen.add(Box.createVerticalGlue());
					pBoxScreen.add(lGuide);
					pBoxScreen.add(Box.createVerticalGlue());
					
					pBoxScreen.setVisible(true);
					break;
				case "닉네임 변경":
					lGuide.setText("변경할 닉네임을 작성하세요.");
					pBoxScreen.removeAll();

					pBoxScreen.add(Box.createVerticalGlue());
					pBoxScreen.add(lGuide);
					pBoxScreen.add(getChangeNicknameBody());
					pBoxScreen.add(Box.createVerticalGlue());
					
					pBoxScreen.setVisible(true);
					break;
				case "이메일 변경":
					lGuide.setText("변경할 이메일을 작성하세요.");
					pBoxScreen.removeAll();

					pBoxScreen.add(Box.createVerticalGlue());
					pBoxScreen.add(lGuide);
					pBoxScreen.add(getChangeEmailBody());
					pBoxScreen.add(Box.createVerticalGlue());
					
					pBoxScreen.setVisible(true);
					break;
				case "연락처 변경":
					lGuide.setText("변경할 연락처를 작성하세요.");
					pBoxScreen.removeAll();

					pBoxScreen.add(Box.createVerticalGlue());
					pBoxScreen.add(lGuide);
					pBoxScreen.add(getChangeTelBody());
					pBoxScreen.add(Box.createVerticalGlue());
					
					pBoxScreen.setVisible(true);
					break;
				}
			}
		});
		
		lGuide = new JLabel("원하는 행동을 선택해주세요.");
		lGuide.setForeground(new Color(189, 198, 208));
		lGuide.setFont(new Font("Arial", Font.PLAIN, 32));
		lGuide.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		lGuide.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		pBoxScreen = new JPanel();
		pBoxScreen.setLayout(new BoxLayout(pBoxScreen, BoxLayout.Y_AXIS));
		pBoxScreen.setBackground(new Color(12, 14, 18));
		pBoxScreen.setPreferredSize(new Dimension(480, 600));
		pBoxScreen.setMaximumSize(new Dimension(480, 600));
		pBoxScreen.add(Box.createVerticalGlue());
		pBoxScreen.add(lGuide);
		pBoxScreen.add(Box.createVerticalGlue());
		
		pBoxContainer.add(cbSelect);
		pBoxContainer.add(Box.createVerticalStrut(10));
		pBoxContainer.add(pBoxScreen);
		pBoxContainer.add(Box.createVerticalGlue());
		
		this.add(pBoxContainer);
		
		this.setSize(480, 640);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public JPanel getChangeNicknameBody() {
		JTextField tfNickname = new JTextField();
		tfNickname.setPreferredSize(new Dimension(280, 30));
		tfNickname.setMaximumSize(new Dimension(280, 30));
		tfNickname.setBackground(new Color(189, 198, 208));
		tfNickname.setForeground(new Color(12, 14, 18));
		tfNickname.setFont(new Font("Arial", Font.PLAIN, 15));
		tfNickname.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		tfNickname.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tfNickname.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// 닉네임 입력 시 유효성 검사 + 업데이트
					RenewalUserInfoProcess ruip = new RenewalUserInfoProcess();
					boolean isGood = ruip.checkValidationNickname(tfNickname.getText());
					if (isGood) {
						ruip.changeNickname(tfNickname.getText());
						setVisible(false);
						dispose();
					}
				}
			}
		});
		
		JPanel pBoxNickName = new JPanel();
		pBoxNickName.setLayout(new BoxLayout(pBoxNickName, BoxLayout.Y_AXIS));
		pBoxNickName.setBackground(new Color(12, 14, 18));
		pBoxNickName.add(tfNickname);
		
		return pBoxNickName;
	}
	
	public JPanel getChangeEmailBody() {
		JTextField tfEmail = new JTextField();
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
					// 이메일 입력 시 유효성 검사 + 업데이트하기
					RenewalUserInfoProcess ruip = new RenewalUserInfoProcess();
					boolean isGood = ruip.checkValidationEmail(tfEmail.getText());
					if (isGood) {
						ruip.changeEmail(tfEmail.getText());
						setVisible(false);
						dispose();
					}
				}
			}
		});
		
		JPanel pBoxEmail = new JPanel();
		pBoxEmail.setLayout(new BoxLayout(pBoxEmail, BoxLayout.Y_AXIS));
		pBoxEmail.setBackground(new Color(12, 14, 18));
		pBoxEmail.add(tfEmail);
		
		return pBoxEmail;
	}
	
	public JPanel getChangeTelBody() {
		JTextField tfTel = new JTextField();
		tfTel.setPreferredSize(new Dimension(280, 30));
		tfTel.setMaximumSize(new Dimension(280, 30));
		tfTel.setBackground(new Color(189, 198, 208));
		tfTel.setForeground(new Color(12, 14, 18));
		tfTel.setFont(new Font("Arial", Font.PLAIN, 15));
		tfTel.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		tfTel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tfTel.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// 연락처 입력 시 유효성 검사 + 업데이트하기
					RenewalUserInfoProcess ruip = new RenewalUserInfoProcess();
					boolean isGood = ruip.checkValidationTel(tfTel.getText());
					if (isGood) {
						ruip.changeTel(tfTel.getText());
						setVisible(false);
						dispose();
					}
				}
			}
		});
		
		JPanel pBoxTel = new JPanel();
		pBoxTel.setLayout(new BoxLayout(pBoxTel, BoxLayout.Y_AXIS));
		pBoxTel.setBackground(new Color(12, 14, 18));
		pBoxTel.add(tfTel);
		
		return pBoxTel;
	}
}