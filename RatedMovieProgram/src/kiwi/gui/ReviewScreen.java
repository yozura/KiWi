package kiwi.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import kiwi.mgr.UserMgr;

// 사용자 개인의 리뷰를 가져와 보여줌.
public class ReviewScreen extends JPanel {
	private JLabel lWelcome;

	private JPanel pFlowBody;
	
	private Vector<JPanel> vecReview;
	
	public ReviewScreen() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(12, 14, 18));
		
		lWelcome = new JLabel(String.format("%s's Review", "Nickname"));
		lWelcome.setForeground(new Color(189, 198, 208));
		lWelcome.setFont(new Font("Arial", Font.PLAIN, 32));
		lWelcome.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		lWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lWelcome.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JSeparator hr = new JSeparator(JSeparator.HORIZONTAL);
		hr.setForeground(new Color(189, 198, 208));
		hr.setPreferredSize(new Dimension(1080, 5));
		hr.setMaximumSize(new Dimension(1080, 5));
		hr.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		// -----------------------------------------------------------

		// 석영씨 일감.
		// 1. 포스터 크기는 가로 148, 세로 210
		// 2. 바디 전체 패널 크기는 가로 1040, 세로 680
		// 3. 리뷰 패널에 라인 보더로 핑크색깔 일단 넣기
		


		pFlowBody = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JScrollPane spBody = new JScrollPane(pFlowBody, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		vecReview = new Vector<JPanel>();
		for (int i = 0; i < 30; ++i) {
			
		}
		
		// -----------------------------------------------------------
		
		this.add(Box.createVerticalGlue());
		this.add(lWelcome);
		this.add(hr);
		this.add(spBody);
		this.add(Box.createVerticalGlue());
	}
}