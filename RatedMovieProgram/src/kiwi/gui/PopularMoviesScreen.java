package kiwi.gui;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PopularMoviesScreen extends JFrame {
	private Point FRAME_SIZE = new Point(1280, 720);
	private JPanel backGround = new JPanel();
	private JPanel backTop = new JPanel();
	private JPanel backBottom = new JPanel();
	
	
	private Vector<JPanel> vecMovie;
	
	private JLabel head;
	private JLabel body;
	
	private JScrollBar scBar;
	private JScrollBar scBar1;
	private JScrollPane scPane;
	
	PopularMoviesScreen() {
		backTop.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0));
		backBottom.setLayout(new GridLayout(0, 4, 40, 30));
		backGround.setLayout(new BorderLayout());
		
		//위쪽 POPULAR MOVIES 부분   하단부분 그리드 레이아웃 사용시 에러발생
		head = new JLabel("POPULAR MOVIES");
		head.setHorizontalAlignment(NORMAL);
		head.setBorder(new EmptyBorder(15, 0, 15, 0));
		head.setForeground(new Color(189, 198, 208));
		head.setFont(new Font("Arial", Font.PLAIN, 32));
		
		ImageIcon iconLogo = new ImageIcon("res/images/logo.png");
		Image img = iconLogo.getImage();
		Image changeImg = img.getScaledInstance(210, 297, Image.SCALE_SMOOTH);
		ImageIcon iconChangedLogo = new ImageIcon(changeImg);
		
		
		
		vecMovie = new Vector<JPanel>();
		for (int i = 0; i < 25; ++i) {
			JPanel pMovie = new JPanel();
			pMovie.setLayout(new BoxLayout(pMovie, BoxLayout.Y_AXIS));
			pMovie.setBackground(new Color(12, 14, 18));
			pMovie.setPreferredSize(new Dimension(210, 397));
			pMovie.setMaximumSize(new Dimension(210, 397));
			
			JLabel lPoster = new JLabel();
			lPoster.setIcon(iconChangedLogo);
			
			JLabel lTitle = new JLabel("movieTitle");
			lTitle.setForeground(new Color(189, 198, 208));
			
			JLabel lGrade = new JLabel("Grade");
			lGrade.setForeground(new Color(189, 198, 208));
			
			pMovie.add(lPoster);
			pMovie.add(lTitle);
			pMovie.add(lGrade);
			
			backBottom.add(pMovie);
			vecMovie.add(pMovie);
		}  
		
		//스크롤 바
		JScrollPane scPane = new JScrollPane(backBottom, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		//패널 부착
		head.setBackground(new Color(12, 14, 18)); // 위쪽 패널 플로우레리아웃으로 초기화 
		backTop.add(head); // 위쪽 패널에 head레이블 부착
		// 전체 레이아웃은 보더 레이아웃으로 해야 위/아래로 부착 가능, 위 쪽 패널만 플로우 레이아웃 사 
		backTop.setBackground(new Color(120, 140, 180));		// TEST
		backTop.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		backBottom.setBackground(new Color(12, 14, 18));
		backBottom.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
		
		//전체 화면시 보더 레이아웃 오류 발새d, backTop패널(head 레이블)이 왼 쪽으로  
		backGround.add(backTop, BorderLayout.NORTH);
		backGround.add(scPane, BorderLayout.CENTER);
		
		//실행창 부분
		add(backGround);
		setTitle("KiWi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(12, 14, 18));
		setPreferredSize(new Dimension(1280, 800));
		setResizable(true);
		setLocationRelativeTo(null);
		setSize(FRAME_SIZE.x , FRAME_SIZE.y);
		setVisible(true);
	}



	public static void main (String[] args) {
		new PopularMoviesScreen();
	}
}