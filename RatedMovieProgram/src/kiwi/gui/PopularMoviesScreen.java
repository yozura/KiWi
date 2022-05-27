package kiwi.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import kiwi.dto.Movie;
import kiwi.header.Define.SCREEN_TYPE;
import kiwi.mgr.MovieMgr;
import kiwi.mgr.ScreenMgr;

public class PopularMoviesScreen extends JPanel {
	private static final long serialVersionUID = 5476977243502241593L;

	public PopularMoviesScreen() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(12, 14, 18));
		
		JLabel lWelcome = new JLabel("Popular Movies");
		lWelcome.setForeground(new Color(189, 198, 208));
		lWelcome.setFont(new Font("Arial", Font.PLAIN, 32));
		lWelcome.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		lWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lWelcome.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JSeparator hr = new JSeparator(JSeparator.HORIZONTAL);
		hr.setBackground(new Color(189, 198, 208));
		hr.setForeground(new Color(189, 198, 208));
		hr.setPreferredSize(new Dimension(1080, 7));
		hr.setMaximumSize(new Dimension(1080, 7));
		
		JPanel pGridBody = new JPanel(new GridLayout(0, 4, 30, 30));
		pGridBody.setBackground(new Color(12, 14, 18));
		
		int movieCount = MovieMgr.getInstance().getMoviesCount();
		for (int i = 0; i < movieCount; ++i) {
			Movie movie = MovieMgr.getInstance().getMovies().get(i);
			
			JPanel pMovie = new JPanel();
			pMovie.setLayout(new BoxLayout(pMovie, BoxLayout.Y_AXIS));
			pMovie.setBackground(new Color(12, 14, 18));
			pMovie.setPreferredSize(new Dimension(210, 397));
			pMovie.setMaximumSize(new Dimension(210, 397));
			
			ImageIcon iconLogo = new ImageIcon(movie.getPoster());
			Image img = iconLogo.getImage();
			Image changeImg = img.getScaledInstance(210, 297, Image.SCALE_SMOOTH);
			ImageIcon iconChangedLogo = new ImageIcon(changeImg);
			
			JButton lPoster = new JButton();
			lPoster.setText(String.valueOf(i));
			lPoster.setIcon(iconChangedLogo);
			lPoster.setBorder(BorderFactory.createEmptyBorder());
			lPoster.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			lPoster.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// 무비 스크린으로 이동
					JButton btn = (JButton)e.getSource();
					
					MovieMgr.getInstance().setCurMovie(Integer.parseInt(btn.getText()));
					ScreenMgr.getInstance().changeCurScreenWithBar(SCREEN_TYPE.MOVIE, btn);
				}				
			});
			
			JLabel lTitle = new JLabel(movie.getTitle());
			lTitle.setForeground(new Color(189, 198, 208));
			lTitle.setFont(new Font("Arial", Font.PLAIN, 15));
			lTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			
			JLabel lGrade = new JLabel(String.valueOf(movie.getRate()));
			lGrade.setForeground(new Color(189, 198, 208));
			lGrade.setFont(new Font("Arial", Font.PLAIN, 15));
			lGrade.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			
			pMovie.add(lPoster);
			pMovie.add(lTitle);
			pMovie.add(lGrade);
			
			pGridBody.add(pMovie);
		}  
		
		JScrollPane scPane = new JScrollPane(pGridBody, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scPane.setBackground(new Color(12, 14, 18));
		
		JPanel pBoxPane = new JPanel();
		pBoxPane.setLayout(new BoxLayout(pBoxPane, BoxLayout.X_AXIS));
		pBoxPane.setBackground(new Color(12, 14, 18));
		pBoxPane.add(Box.createHorizontalGlue());
		pBoxPane.add(scPane);
		pBoxPane.add(Box.createHorizontalGlue());

		
		//실행창 부분
		this.add(lWelcome);
		this.add(hr);
		this.add(Box.createVerticalStrut(40));
		this.add(pBoxPane);
	}
}