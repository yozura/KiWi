package kiwi.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import kiwi.dto.User;
import kiwi.mgr.UserMgr;

public class UserScreen extends JPanel {
	private static final long serialVersionUID = 6670267929674394820L;
	
	private String headers[] = { "아이디", "닉네임", "생년월일", "이메일", "전화번호" };
	
	public UserScreen() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(12, 14, 18));
		this.setBorder(BorderFactory.createLineBorder(new Color(189, 198, 208), 1));
		
		User user = UserMgr.getInstance().getCurUser();
		if (user != null) {
			this.add(getHeader(user.getNickname()));
			this.add(getBody(user));
		} else {
			this.add(getHeader());
			this.add(getBody());
		}
	}
	
	public JPanel getBody(User user) {
		String contents[][] = { 
				{user.getId(), user.getNickname(), 
				user.getBirthDate().toString(), user.getEmail(),
				user.getTel()}};
		
		JTable tblUser = new JTable(contents, headers);
		
		return null;
	}
	
	public JPanel getBody() {
		String contents[][] = { 
				{
					"myilano123", "우주최강민식이",
					"2021-10-19", "myl@kiwi.com",
					"010-2131-9923"
				}};
		
		TableModel dataModel = new AbstractTableModel() {
			@Override
			public int getRowCount() {
				return 5;
			}

			@Override
			public int getColumnCount() {
				return 2;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				return rowIndex * columnIndex;
			}
		};
		
		JTable tblUser = new JTable(dataModel);
		tblUser.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		tblUser.setTableHeader(null);
		JScrollPane spUser = new JScrollPane(tblUser);
		
		JPanel pBoxUser = new JPanel();
		pBoxUser.setLayout(new BoxLayout(pBoxUser, BoxLayout.Y_AXIS));
		pBoxUser.add(spUser);
		
		return pBoxUser;
	}
	
	public JPanel getHeader(String nickname) {
		JLabel lTitle = new JLabel(String.format("%s's Information", nickname));
		lTitle.setForeground(new Color(189, 198, 208));
		lTitle.setFont(new Font("Arial", Font.PLAIN, 32));
		lTitle.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 0));
		
		JPanel pBoxHeader = new JPanel();
		pBoxHeader.setLayout(new BoxLayout(pBoxHeader, BoxLayout.X_AXIS));
		pBoxHeader.add(Box.createHorizontalStrut(10));
		pBoxHeader.add(lTitle);
		pBoxHeader.add(Box.createHorizontalGlue());
		
		return pBoxHeader;
	}
	
	public JPanel getHeader() {
		JLabel lTitle = new JLabel(String.format("로그인이 필요합니다."));
		lTitle.setForeground(new Color(189, 198, 208));
		lTitle.setFont(new Font("Arial", Font.PLAIN, 32));
		lTitle.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 0));
		
		JPanel pBoxHeader = new JPanel();
		pBoxHeader.setLayout(new BoxLayout(pBoxHeader, BoxLayout.X_AXIS));
		pBoxHeader.add(Box.createHorizontalStrut(10));
		pBoxHeader.add(lTitle);
		pBoxHeader.add(Box.createHorizontalGlue());
		
		return pBoxHeader;
	}
}