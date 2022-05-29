package kiwi.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import kiwi.dto.User;
import kiwi.mgr.UserMgr;

public class UserScreen extends JPanel {
	private static final long serialVersionUID = 6670267929674394820L;
	
	private JComboBox<String> cbSelect;
	private String actions[] = { "없음", "내 정보", "내가 추가한 북마크", "내가 작성한 리뷰" };
	
	private JPanel pFlowForm;
	private JLabel lGuide;
	
	public UserScreen() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(12, 14, 18));
		this.setBorder(BorderFactory.createLineBorder(new Color(189, 198, 208), 1));
		
		cbSelect = new JComboBox<String>(actions);
		cbSelect.setBackground(new Color(189, 198, 208));
		cbSelect.setOpaque(true);
		cbSelect.setPreferredSize(new Dimension(1280, 35));
		cbSelect.setMaximumSize(new Dimension(1280, 35));
		cbSelect.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		cbSelect.setAlignmentY(JComponent.TOP_ALIGNMENT);
		cbSelect.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				switch (e.getItem().toString()) {
				case "없음":
					lGuide.setText("원하는 행동을 선택해주세요.");
					pFlowForm.setVisible(false);
					break;
				case "내 정보":
					lGuide.setText("내 정보");
					pFlowForm.removeAll();
					
					User user = UserMgr.getInstance().getCurUser();
					pFlowForm.add(getUserInfoBody(user));
					
					pFlowForm.setVisible(true);
					break;
				case "내가 추가한 북마크":
					lGuide.setText("내 북마크");
					pFlowForm.removeAll();
					
					pFlowForm.setVisible(true);
					break;
				case "내가 작성한 리뷰":
					lGuide.setText("내가 작성한 리뷰");
					pFlowForm.removeAll();
					
					pFlowForm.setVisible(true);
					break;
				}
			}
		});
		
		lGuide = new JLabel("원하는 행동을 선택해주세요.");
		lGuide.setForeground(new Color(189, 198, 208));
		lGuide.setFont(new Font("Arial", Font.PLAIN, 32));
		lGuide.setHorizontalAlignment(SwingConstants.CENTER);
		lGuide.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		// Merge Boxes
		pFlowForm = new JPanel();
		pFlowForm.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
		pFlowForm.setPreferredSize(new Dimension(600, 465));
		pFlowForm.setMaximumSize(new Dimension(600, 465));
		pFlowForm.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		pFlowForm.setBackground(new Color(18, 21, 26));
		pFlowForm.setVisible(false);
				
		this.add(cbSelect);
		this.add(Box.createVerticalStrut(10));
		this.add(Box.createVerticalGlue());
		this.add(lGuide);
		this.add(Box.createVerticalStrut(10));
		this.add(pFlowForm);
		this.add(Box.createVerticalGlue());
	}
	
	
	public JPanel getUserInfoBody(User user) {		
		String contents[][] = {
				{ "아이디", user.getId() },
				{ "닉네임", user.getNickname() },
				{ "생년월일", user.getBirthDate().toString() },
				{ "이메일", user.getEmail() },
				{ "전화번호", user.getTel() }
		};
		
		JTable tblUser = new JTable(contents, new String[] { "분류", "정보" }) {
			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {
				if (columnIndex == 1) {
					setFont(new Font("Arial", Font.PLAIN, 13));
				} else {
					setFont(new Font("Arial", Font.BOLD, 13));
				}
				
				return super.prepareRenderer(renderer, rowIndex, columnIndex);
			}
		};
		tblUser.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		tblUser.setShowGrid(false);
		tblUser.setEnabled(false);
		tblUser.setTableHeader(null);
		tblUser.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JScrollPane spUser = new JScrollPane(tblUser);
		spUser.setBackground(new Color(12, 14, 18));
		spUser.setPreferredSize(new Dimension(400, 85));
		spUser.setMaximumSize(new Dimension(400, 85));
		spUser.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JButton btnChangePassword = new JButton("비밀번호 변경하기");
		btnChangePassword.setPreferredSize(new Dimension(410, 25));
		btnChangePassword.setMaximumSize(new Dimension(410, 25));
		btnChangePassword.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RenewalPasswordFrame();
			}
		});
		
		JButton btnChangeUserInfo = new JButton("유저 정보 변경하기");
		btnChangeUserInfo.setPreferredSize(new Dimension(410, 25));
		btnChangeUserInfo.setMaximumSize(new Dimension(410, 25));
		btnChangeUserInfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnChangeUserInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RenewalUserInfoFrame();
			}
		});
		
		JPanel pBoxUser = new JPanel();
		pBoxUser.setLayout(new BoxLayout(pBoxUser, BoxLayout.Y_AXIS));
		pBoxUser.setBackground(new Color(18, 21, 26));
		pBoxUser.add(Box.createVerticalStrut(150));
		pBoxUser.add(spUser);
		pBoxUser.add(btnChangePassword);
		pBoxUser.add(btnChangeUserInfo);
		pBoxUser.add(Box.createVerticalGlue());
		
		return pBoxUser;
	}
}