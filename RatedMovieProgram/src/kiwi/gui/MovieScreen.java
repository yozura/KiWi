package kiwi.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import kiwi.dto.Movie;
import kiwi.mgr.MovieMgr;

public class MovieScreen extends JPanel {
	public MovieScreen() {
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(12, 14, 18));
		
		this.add(getHeader("영화 타이틀"), BorderLayout.NORTH);
		this.add(getBody(MovieMgr.getInstance().getCurMovie()), BorderLayout.CENTER);
	}

	public JScrollPane getBody(Movie movie) {
		JLabel lPoster = new JLabel();
		try {
			lPoster.setIcon(new ImageIcon(MovieMgr.getInstance().resizePoster(movie.getPoster(), 210, 297)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		lPoster.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		lPoster.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));
		
		JLabel lRatingIcon = new JLabel();
		lRatingIcon.setIcon(new ImageIcon("res/icons/star_fill.png"));
		lRatingIcon.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JPanel pBoxRating = new JPanel();
		pBoxRating.setLayout(new BoxLayout(pBoxRating, BoxLayout.X_AXIS));
		pBoxRating.setPreferredSize(new Dimension(841, 297));
		pBoxRating.setMaximumSize(new Dimension(841, 297));
		pBoxRating.setBackground(Color.PINK);
		pBoxRating.add(Box.createHorizontalGlue());
		pBoxRating.add(lRatingIcon);
		pBoxRating.add(Box.createHorizontalGlue());

		JPanel pFlowBody = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pFlowBody.setBackground(new Color(12, 14, 18));
		pFlowBody.setBorder(BorderFactory.createEmptyBorder(50, 80, 0, 80));
		pFlowBody.add(lPoster);
		pFlowBody.add(pBoxRating);
		
		JPanel pBoxBody = new JPanel();
		pBoxBody.setLayout(new BoxLayout(pBoxBody, BoxLayout.Y_AXIS));
		pBoxBody.setBackground(new Color(12, 14, 18));
		pBoxBody.add(pFlowBody);

		JScrollPane spBody = new JScrollPane(pBoxBody, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		
		return spBody;
	}
	
	public JPanel getHeader(String title) {
		JLabel lTitle = new JLabel(title);
		lTitle.setForeground(new Color(189, 198, 208));
		lTitle.setFont(new Font("Arial", Font.PLAIN, 32));
		lTitle.setBorder(BorderFactory.createEmptyBorder(30, 150, 30, 0));
				
		JPanel pFlowHeader = new JPanel(new FlowLayout(FlowLayout.LEADING));
		pFlowHeader.setBackground(new Color(12, 14, 18));
		pFlowHeader.add(lTitle);
		
		return pFlowHeader;
	}
}
