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
import javax.swing.JPanel;

import kiwi.header.Define.SCREEN_TYPE;
import kiwi.mgr.ScreenMgr;

// btn func
// 1. home
// 2. my account
// 3. bookmark (favorite movies)
// 4. review (my review)
// 5. movie list (pop movie)
// 5. logout

public class SideBar extends JPanel {	
	private JButton[] arrOfSideBtns = new JButton[6];
	
	public SideBar() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createLineBorder(new Color(189, 198, 208), 1));
		this.setBackground(new Color(210, 180, 140));
		for (int i = 0; i < 6; ++i) {
			JButton btn = new JButton();
			btn.setPreferredSize(new Dimension(60, 60));
			btn.setMaximumSize(new Dimension(60, 60));
			ImageIcon iconBtn;
			switch (i) {
			case 0:
				iconBtn = new ImageIcon("res/icons/home.png");
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ScreenMgr.getInstance().changeCurScreenWithBar(SCREEN_TYPE.HOME, btn);
					}
				});
				break;
			case 1:
				iconBtn = new ImageIcon("res/icons/account_box.png");
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ScreenMgr.getInstance().changeCurScreenWithBar(SCREEN_TYPE.ADMIN, btn);
					}
				});
				break;
			case 2:
				iconBtn = new ImageIcon("res/icons/bookmarks.png");
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ScreenMgr.getInstance().changeCurScreenWithBar(SCREEN_TYPE.BOOKMARK, btn);
					}
				});
				break;
			case 3:
				iconBtn = new ImageIcon("res/icons/review2.png");
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ScreenMgr.getInstance().changeCurScreenWithBar(SCREEN_TYPE.REVIEW, btn);
					}
				});
				break;
			case 4:
				iconBtn = new ImageIcon("res/icons/collections.png");
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ScreenMgr.getInstance().changeCurScreenWithBar(SCREEN_TYPE.POP_MOVIES, btn);
					}
				});
				break;
			case 5:
				iconBtn = new ImageIcon("res/icons/logout.png");
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ScreenMgr.getInstance().changeCurScreen(SCREEN_TYPE.LOG_IN, btn);
					}
				});
				break;
			default:
				iconBtn = null;
				break;
			}
			btn.setBorder(BorderFactory.createEmptyBorder());
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
