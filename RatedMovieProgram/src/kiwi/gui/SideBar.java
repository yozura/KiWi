package kiwi.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kiwi.header.Define.SCREEN_TYPE;
import kiwi.header.Define.USER_TYPE;
import kiwi.mgr.ScreenMgr;
import kiwi.mgr.UserMgr;

public class SideBar extends JPanel {
	private static final long serialVersionUID = -4685581869908944837L;
	
	private JButton[] arrOfSideBtns = new JButton[4];
	
	public SideBar() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createLineBorder(new Color(189, 198, 208), 1));
		this.setBackground(new Color(210, 180, 140));
		for (int i = 0; i < 4; ++i) {
			JButton btn = new JButton();
			btn.setPreferredSize(new Dimension(60, 60));
			btn.setMaximumSize(new Dimension(60, 60));
			ImageIcon iconBtn;
			switch (i) {
			case 0:
				// 홈 스크린 이동 버튼
				iconBtn = new ImageIcon("res/icons/home.png");
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (UserMgr.getInstance().getCurUser().equals(null)) {
							JOptionPane.showMessageDialog(null, "로그인 후 사용 가능합니다.");
							return;
						}
						
						ScreenMgr.getInstance().changeCurScreenWithBar(SCREEN_TYPE.HOME, btn);
					}
				});
				break;
			case 1:
				// 유저 & 어드민 스크린 이동 버튼
				iconBtn = new ImageIcon("res/icons/account_circle.png");
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (UserMgr.getInstance().getCurUser().equals(null)) {
							JOptionPane.showMessageDialog(null, "로그인 후 사용 가능합니다.");
							return;
						}
						
						if (UserMgr.getInstance().getUserType().equals(USER_TYPE.ADMIN)) {
							ScreenMgr.getInstance().changeCurScreenWithBar(SCREEN_TYPE.ADMIN, btn);
						} else {
							ScreenMgr.getInstance().changeCurScreenWithBar(SCREEN_TYPE.USER, btn);
						}
					}
				});
				break;
			case 2:
				// 영화 목록 이동 버튼
				iconBtn = new ImageIcon("res/icons/collections.png");
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (UserMgr.getInstance().getCurUser().equals(null)) {
							JOptionPane.showMessageDialog(null, "로그인 후 사용 가능합니다.");
							return;
						}
						
						ScreenMgr.getInstance().changeCurScreenWithBar(SCREEN_TYPE.MOVIE_LIST, btn);
					}
				});
				break;
			case 3:
				// 로그아웃 버튼
				iconBtn = new ImageIcon("res/icons/logout.png");
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						UserMgr.getInstance().exit();
						JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.");
						ScreenMgr.getInstance().changeCurScreen(SCREEN_TYPE.LOG_IN, btn);
					}
				});
				break;
			case 4:
				// 디버깅용 유저 타입 변환 버튼
				iconBtn = new ImageIcon("res/icons/delete.png");
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (UserMgr.getInstance().getUserType().equals(USER_TYPE.ADMIN)) {
							UserMgr.getInstance().setUserType(USER_TYPE.NORMAL);
							JOptionPane.showMessageDialog(null, "유저 타입이 일반으로 변경되었습니다.");
						} else {
							UserMgr.getInstance().setUserType(USER_TYPE.ADMIN);
							JOptionPane.showMessageDialog(null, "유저 타입이 어드민으로 변경되었습니다.");
						}
					}
				});
				break;
			default:
				iconBtn = null;
				break;
			}
			btn.setBorder(BorderFactory.createEmptyBorder());
			btn.setOpaque(true);
			btn.setBackground(new Color(210, 180, 140));
			btn.setIcon(iconBtn);
			btn.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					// 마우스 들어오면 밝아지기
					JButton srcBtn = (JButton)e.getSource();
					srcBtn.setBackground(new Color(241, 249, 209));
					srcBtn.setOpaque(true);
				}
				
				public void mouseExited(MouseEvent e) {
					// 마우스 나가면 원래 상태로
					JButton srcBtn = (JButton)e.getSource();
					srcBtn.setBackground(new Color(210, 180, 140));
				}
			});
			arrOfSideBtns[i] = btn;
			this.add(btn);
		}
	}
	
	public JButton getSideBtn(int idx) {
		if (idx < 0 && idx >= arrOfSideBtns.length) 
			return null;
		
		return arrOfSideBtns[idx];
	}
}
