package kiwi.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SideBar extends JPanel {	
	private JButton[] arrOfSideBtns = new JButton[4];
	
	public SideBar() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
		this.setBackground(new Color(189, 198, 208));
		for (int i = 0; i < 4; ++i) {
			JButton btn = new JButton();
			btn.setPreferredSize(new Dimension(60, 60));
			btn.setMaximumSize(new Dimension(60, 60));
			ImageIcon iconBtn;
			switch (i) {
			case 0:
				iconBtn = new ImageIcon("res/icons/home.png");
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ScreenMgr.getInstance().changeCurrentScreen(Screen_Type.HOME);
					}
				});
				break;
			case 1:
				iconBtn = new ImageIcon("res/icons/menu.png");
				break;
			case 2:
				iconBtn = new ImageIcon("res/icons/expand_more.png");
				break;
			case 3:
				iconBtn = new ImageIcon("res/icons/settings.png");
				break;
			default:
				iconBtn = null;
				break;
			}
			btn.setBorder(BorderFactory.createEmptyBorder());
			btn.setIcon(iconBtn);
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