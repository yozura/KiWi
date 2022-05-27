package kiwi.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

// 사용자 개인의 리뷰를 가져와 보여줌.
public class ReviewScreen extends JPanel {
	// 초기화는 멤버변수 쓰는 곳에서 하기
	private JPanel poster;		// 포스터 바디에 붙는 포스터 패널
	private JPanel review;		// 포스터 패널에 붙는 리뷰 패널
	
	private JLabel lWelcome;
	
	private Vector<JPanel> vecReview;
	
	public ReviewScreen() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(12, 14, 18));
		
		//상단 헤더 라벨
		lWelcome = new JLabel(String.format("%s's Review", "Nickname"));
		lWelcome.setForeground(new Color(189, 198, 208));
		lWelcome.setFont(new Font("Arial", Font.PLAIN, 32));
		lWelcome.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		lWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lWelcome.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		//헤더 아래 수평선
		JSeparator hr = new JSeparator(JSeparator.HORIZONTAL);
		hr.setForeground(new Color(189, 198, 208));
		hr.setPreferredSize(new Dimension(1080, 5));
		hr.setMaximumSize(new Dimension(1080, 5));
		hr.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		//포스터 라벨 이미지
		ImageIcon iconLogo = new ImageIcon("res/images/logo.png");
		Image img = iconLogo.getImage();
		Image changeImg = img.getScaledInstance(210, 297, Image.SCALE_SMOOTH);
		ImageIcon iconChangedLogo = new ImageIcon(changeImg);
		
		// 센터 바디 -----------------------------------------------------------
		// 석영씨 일감.
		// 1. 포스터 크기는 가로 148, 세로 210 (v)
		// 2. 바디 전체 패널 크기는 가로 1040, 세로 680 (v)
		// 3. 리뷰 패널에 라인 보더로 핑크색깔 일단 넣기 (v)
		// 4. 리뷰 패널에 줄넘김 기능 추가
		// 5. 바디 패널에 줄넘김 기능 추가
		
		//중앙분리선 보류
		//JSeparator vr = new JSeparator(JSeparator.VERTICAL);
		//vr.setForeground(new Color(255, 131, 205)); 
		//vr.setPreferredSize(new Dimension(1920, 5));
		//vr.setMinimumSize(new Dimension());
		
		//포스터 붙는 바디
		JPanel pBorderBack = new JPanel();
		pBorderBack.setLayout(new FlowLayout());
		pBorderBack.setForeground(new Color(160, 160, 160));
		pBorderBack.setPreferredSize(new Dimension(1040, 680));
		
		//포스터,타이틀,리뷰 붙는 패널
		JPanel pBorderBody = new JPanel(new BorderLayout()); // 중앙 정렬
		pBorderBody.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		pBorderBody.setBackground(new Color(200, 200, 30));
		pBorderBody.setPreferredSize(new Dimension(400, 200));
		pBorderBody.setMaximumSize(new Dimension(400, 200));
		
		//포스터 패널
		JPanel pBorderPost = new JPanel(new BorderLayout());
		pBorderPost.setPreferredSize(new Dimension(180, 90));
		pBorderPost.setMaximumSize(new Dimension(180, 90));
		
		//포스터 라벨
		JLabel lPoster = new JLabel();
		lPoster.setIcon(iconChangedLogo);
		lPoster.setPreferredSize(new Dimension(140, 210));
		
		//영화 제목 라벨
		JLabel lMovieTitle = new JLabel("Movie Title");
		lMovieTitle.setForeground(new Color(30, 255, 30));
		
		//텍스트 영역 패널
		JPanel pFlowReview = new JPanel(new FlowLayout());
		LineBorder LineBorder = new LineBorder(Color.pink, 5, true);
		pFlowReview.setForeground(new Color(255, 255, 255));
		pFlowReview.setBorder(LineBorder);
		//pFlowReview;
		
		//텍스트 영역
		JLabel lReview = new JLabel("Hi! I`m Review!");
		lReview.setForeground(new Color(189, 198, 208));
		
		
		
		
		JScrollPane spBody = new JScrollPane(pBorderBody, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		//리뷰 갯수 증가, 플로우 레이아웃으로 2개 마다 아랫 줄으로 이동
		vecReview = new Vector<JPanel>();
		for (int i = 0; i < 30; ++i) {
			
		}
		
		// 패널 부착 -----------------------------------------------------------
		
		this.add(Box.createVerticalGlue());
		this.add(lWelcome);
		this.add(hr);
		this.add(spBody);
		this.add(Box.createVerticalGlue());
	}
}